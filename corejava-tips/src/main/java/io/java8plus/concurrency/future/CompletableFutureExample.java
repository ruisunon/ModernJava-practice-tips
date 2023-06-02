package io.java8plus.concurrency.future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class CompletableFutureExample {

  static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
    int count = 1;

    @Override
    public Thread newThread(Runnable runnable) {
      return new Thread(runnable, "custom-executor-" + count++);
    }
  });

  static Random random = new Random();

  public static void main(String[] args) {
    try {
      completedFutureExample();
    } finally {
      executor.shutdown();
    }
  }

  static void completedFutureExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
    assertTrue(cf.isDone());
    assertEquals("message", cf.getNow(null));
  }

  static void completeExceptionallyExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
    CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> { return (th != null) ? "message upon cancel" : ""; });
    cf.completeExceptionally(new RuntimeException("completed exceptionally"));
    assertTrue( cf.isCompletedExceptionally(), "Was not completed exceptionally");
    try {
      cf.join();
      fail("Should have thrown an exception");
    } catch (CompletionException ex) { // just for testing
      assertEquals(ex.getCause().getMessage(), "completed exceptionally");
    }

    assertEquals( exceptionHandler.join(), "message upon cancel");
  }

  static void runAsyncExample() {
    CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
      assertTrue(Thread.currentThread().isDaemon());
      randomSleep();
    });
    assertFalse(cf.isDone());
    sleepEnough();
    assertTrue(cf.isDone());
  }

  static void thenApplyExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
      assertFalse(Thread.currentThread().isDaemon());
      return s.toUpperCase();
    });
    assertEquals("MESSAGE", cf.getNow(null));
  }

  static void thenApplyAsyncExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
      assertTrue(Thread.currentThread().isDaemon());
      randomSleep();
      return s.toUpperCase();
    });
    assertNull(cf.getNow(null), "null");
    assertEquals( cf.join(), "MESSAGE");
  }

  static void thenApplyAsyncWithExecutorExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
      assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
      assertFalse(Thread.currentThread().isDaemon(), "daemon");
      randomSleep();
      return s.toUpperCase();
    }, executor);

    assertEquals( cf.join(), "MESSAGE");
  }

  static void thenAcceptExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture("thenAccept message")
        .thenAccept(s -> result.append(s));
    assertTrue( result.length() > 0, "Result was empty");
  }

  static void thenAcceptAsyncExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
        .thenAcceptAsync(s -> result.append(s));
    cf.join();
    assertTrue( result.length() > 0, "Result was empty");
  }

  static void cancelExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
    CompletableFuture<String> cf2 = cf.exceptionally(throwable -> "canceled message");
    assertTrue( cf.cancel(true), "Was not canceled");
    assertTrue( cf.isCompletedExceptionally(), "Was not completed exceptionally");
    assertEquals( cf2.join(), "canceled message");
  }

  static void applyToEitherExample() {
    String original = "Message";
    CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
        .thenApplyAsync(s -> delayedUpperCase(s));
    CompletableFuture<String> cf2 = cf1.applyToEither(
        CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
        s -> s + " from applyToEither");
    assertTrue(cf2.join().endsWith(" from applyToEither"));
  }

  static void acceptEitherExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture<Void> cf = CompletableFuture.completedFuture(original)
        .thenApplyAsync(s -> delayedUpperCase(s))
        .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
            s -> result.append(s).append("acceptEither"));
    cf.join();
    assertTrue(result.toString().endsWith("acceptEither"), "Result was empty");
  }

  static void runAfterBothExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
        CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
        () -> result.append("done"));
    assertTrue( result.length() > 0, "Result was empty");
  }

  static void thenAcceptBothExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
        CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
        (s1, s2) -> result.append(s1 + s2));
    assertEquals( result.toString(), "MESSAGEmessage");
  }

  static void thenCombineExample() {
    String original = "Message";
    CompletableFuture<String> cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
        .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
            (s1, s2) -> s1 + s2);
    assertEquals( cf.getNow(null), "MESSAGEmessage");
  }

  static void thenCombineAsyncExample() {
    String original = "Message";
    CompletableFuture<String> cf = CompletableFuture.completedFuture(original)
        .thenApplyAsync(s -> delayedUpperCase(s))
        .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
            (s1, s2) -> s1 + s2);
    assertEquals( cf.join(), "MESSAGEmessage");
  }

  static void thenComposeExample() {
    String original = "Message";
    CompletableFuture<String> cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
        .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
            .thenApply(s -> upper + s));
    assertEquals( cf.join(), "MESSAGEmessage");
  }

  static void anyOfExample() {
    StringBuilder result = new StringBuilder();
    List<String> messages = Arrays.asList("a", "b", "c");
    List<CompletableFuture<String>> futures = messages.stream()
        .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
        .collect(Collectors.toList());
    CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
      if(th == null) {
        assertTrue(isUpperCase((String) res));
        result.append(res);
      }
    });
    assertTrue(result.length() > 0, "Result was empty" );
  }

  static void allOfExample() {
    StringBuilder result = new StringBuilder();
    List<String> messages = Arrays.asList("a", "b", "c");
    List<CompletableFuture<String>> futures = messages.stream()
        .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
        .collect(Collectors.toList());
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
      futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
      result.append("done");
    });
    assertTrue( result.length() > 0, "Result was empty");
  }

  static void allOfAsyncExample() {
    StringBuilder result = new StringBuilder();
    List<String> messages = Arrays.asList("a", "b", "c");
    List<CompletableFuture<String>> futures = messages.stream()
        .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
        .collect(Collectors.toList());
    CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
        .whenComplete((v, th) -> {
          futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
          result.append("done");
        });
    allOf.join();
    assertTrue( result.length() > 0, "Result was empty");
  }

  private static boolean isUpperCase(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (Character.isLowerCase(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private static String delayedUpperCase(String s) {
    randomSleep();
    return s.toUpperCase();
  }

  private static String delayedLowerCase(String s) {
    randomSleep();
    return s.toLowerCase();
  }

  private static void randomSleep() {
    try {
      Thread.sleep(random.nextInt(1000));
    } catch (InterruptedException e) {
      // ...
    }
  }

  private static void sleepEnough() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // ...
    }
  }

}