package net.modernjava.concurrency.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockService {

  private static final Object LOCK = new Object();
  private static final Lock DATABASE_LOCK = new ReentrantLock();

  public void doWork(String name) {
    // 1. in a synchronized block, acquire a database lock, e.g. by updating a row
    synchronized (LOCK) {
      DATABASE_LOCK.lock();
    }

    // 2. do time consuming work and try to acquire the lock again
    doTimeConsumingWork();
    synchronized (LOCK) {
      // deadlock occurs if the other thread cannot get the database lock at 1.
    }

    // 3. database lock would be released after transaction is committed
    DATABASE_LOCK.unlock();
  }

  private void doTimeConsumingWork() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}