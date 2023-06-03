package net.modernjava.concurrency;

import java.util.concurrent.CountDownLatch;

public class WorkThread2  implements Runnable{
  private final CountDownLatch startSignal;
  private final CountDownLatch endSignal;
  private Task task=null;

  public WorkThread2(Task task, CountDownLatch startSignal, CountDownLatch endSignal){
    this.task=task;
    this.endSignal=endSignal;
    this.startSignal=startSignal;

  }

  @Override
  public void run(){
    try {
      startSignal.await();;
      task.executeTask2();
    } catch(InterruptedException e){

    }
    finally {
      endSignal.countDown();
    }
  }

}
