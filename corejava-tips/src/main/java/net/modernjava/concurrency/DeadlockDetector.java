package net.modernjava.concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockDetector implements Runnable{
  private static Logger logger = LoggerFactory.getLogger(DeadlockDetector.class);

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
  public static void monitorDeadlocks(){
    while(true){
      ThreadMXBean mxBean=ManagementFactory.getThreadMXBean();
      long[] threadIds=mxBean.findDeadlockedThreads();
      if(threadIds!=null){
        logDeadlockAndQuit(mxBean, threadIds);
      }
      waitUninterruptedlyForMs(500);
    }
  }
  private static void logDeadlockAndQuit(ThreadMXBean bean, long[] threadIds) {
    logger.error("Threads in deadlocks: {}", Arrays.toString(threadIds));

    ThreadInfo[] info = bean.getThreadInfo(threadIds);
    for (ThreadInfo threadInfo : info) {
      logger.error("Thread \"{}\" is waiting on lock \"{}\" taken by thread \"{}\"",
          threadInfo.getThreadName(), threadInfo.getLockInfo(), threadInfo.getLockOwnerName());

      // Attempt to log the stack trace, when available
      for (StackTraceElement stackTraceElement : threadInfo.getStackTrace()) {
        logger.error("{}::{} @ {}:{}",
            stackTraceElement.getClassName(), stackTraceElement.getMethodName(),
            stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
      }
    }

    System.exit(0);
  }

  private static void waitUninterruptedlyForMs(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      // Ignore it
    }
  }
}
