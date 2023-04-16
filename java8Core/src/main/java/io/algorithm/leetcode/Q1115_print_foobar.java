package io.algorithm.leetcode;

import java.util.concurrent.*;

class Q1115_print_foobar {
  private int n;
  Semaphore runFoo, runBar;

  public Q1115_print_foobar(int n) {
    this.n = n;
    runFoo = new Semaphore(0);
    runBar = new Semaphore(0);
    runFoo.release();
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      runFoo.acquire();
      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
      runBar.release();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      runBar.acquire();
      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
      runFoo.release();
    }
  }
}