package net.modernjava.concurrency.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CountDownLatch;

public class DeadlockDetector implements Runnable{
  private final CountDownLatch startSignal;
  private final CountDownLatch endSignal;
  private Task task=null;

  public DeadlockDetector(Task task, CountDownLatch startSignal, CountDownLatch endSignal){
    this.task=task;
    this.endSignal=endSignal;
    this.startSignal=startSignal;

  }
  // deadlock that could not be found by visualVM or jstack
  @Override
  public void run(){
    try {
      startSignal.await();
      // perform 10 iterations with 2 seconds elapsed time
      for(int i=0; i<10; i++) {
        //1. flat & reentrant WRITE lock deadlock detection
        ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
        long[] threadIds= threadMXBean.findDeadlockedThreads();
        int deadlockedThreads=threadIds!=null? threadIds.length: 0;
        System.out.println("\n** Deadlock detecting in progress...");
        System.out.println("Deadlocked threads found by the HotSpot JVM" + deadlockedThreads);

        //2. Reentrant read lock tracking
        System.out.println("Read lock count: " + task.getReentrantReadWriteLock().getReadLockCount());
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) { }
      }
    } catch(InterruptedException e){

    }
    finally {
      endSignal.countDown();
    }
  }
}
