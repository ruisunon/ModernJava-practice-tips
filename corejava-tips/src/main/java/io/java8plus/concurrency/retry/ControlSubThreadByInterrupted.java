package io.java8plus.concurrency.retry;

import java.util.concurrent.atomic.AtomicBoolean;

public class ControlSubThreadByInterrupted implements Runnable {

  private Thread worker;
  private AtomicBoolean running = new AtomicBoolean(false);
  private int interval;

  public ControlSubThreadByInterrupted(int sleepInterval) {
    interval = sleepInterval;
  }


  public void interrupt() {
    running.set(false);
    worker.interrupt();
  }

  boolean isRunning() {
    return running.get();
  }


  public void run() {
    running.set(true);

    while (running.get()) {
      try {
        Thread.sleep(interval);
      } catch (InterruptedException e){
        Thread.currentThread().interrupt();
        System.out.println(
            "Thread was interrupted, Failed to complete operation");
      }
      // do something
    }
    running.set(false);
  }
}