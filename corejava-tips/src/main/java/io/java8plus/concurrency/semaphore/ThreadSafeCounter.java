package io.java8plus.concurrency.semaphore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author by ruisu
 * @package - io.java8plus.concurrency.semaphore
 * @name - ThreadSafeCounter
 * @created on 16/10/2021
 */

public class ThreadSafeCounter {
      AtomicInteger counter;
      public int incrementAndGet(){
        while (true) {
          int value = counter.get();
          if (counter.compareAndSet(value, value + 1)) {
            return value;
          }
        }
      }
}
