package net.modernjava.concurrency;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class DeadlockServiceTest {

  private ExecutorService executor = Executors.newFixedThreadPool(2);
  private CountDownLatch startLatch = new CountDownLatch(1);
  private CountDownLatch endLatch = new CountDownLatch(2);
  private DeadlockService service;

  @Test
  public void testDeadlockExample() throws InterruptedException {
    service = new DeadlockService();
    // submit two threads doing work that can cause a deadlock
    executor.execute(() -> doWork("Thread 1"));
    executor.execute(() -> doWork("Thread 2"));

    // notify the threads so that both threads start at the same time
    startLatch.countDown();

    // Wait for both threads to finish or timeout because a deadlock occurred
    boolean noDeadlock = endLatch.await(100, TimeUnit.MILLISECONDS);

    assertFalse(noDeadlock,"expected a deadlock to occur during the test");
  }

  private void doWork(String name) {
    try {
      // wait for the start latch to count to zero
      startLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    service.doWork(name);
    // notify the end latch that the work is done
    endLatch.countDown();
  }

}