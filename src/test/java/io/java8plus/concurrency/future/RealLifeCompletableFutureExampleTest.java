package io.java8plus.concurrency.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class RealLifeCompletableFutureExampleTest {

  public static void main(String... args) throws InterruptedException, ExecutionException {
    Executor ex = Executors.newCachedThreadPool();
    CompletionService<Long> cs = new ExecutorCompletionService<Long>(ex);
    cs.submit(new Worker());
    cs.submit(new Worker());
    cs.submit(new Worker());
    for (int i = 0; i < 3; i++) {
      long l = cs.take().get();
      //utilize the result
      System.out.println(l);
    }
  }

}

class Worker implements Callable {

  @Override
  public Long call() throws Exception {
    //do some task and return back
    return System.currentTimeMillis();
  }

}