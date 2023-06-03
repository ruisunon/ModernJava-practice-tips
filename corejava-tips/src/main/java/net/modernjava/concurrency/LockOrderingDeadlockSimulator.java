package net.modernjava.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockOrderingDeadlockSimulator {
  public static void main(String[] args){
    System.out.println("Hidden lock-ordering deadlock simulator");
    int startInt=1;
    int endInt=3;
    CountDownLatch startSignal =new CountDownLatch(startInt);
    CountDownLatch endSignal=new CountDownLatch(endInt);
    for (int i=0; i<startInt; i++){
      Task newTask=new Task();
      ExecutorService executorService= Executors.newFixedThreadPool(endInt);
      Runnable deadlockDetector=new DeadlockDetector(newTask, startSignal, endSignal);
      Runnable worker1= new WorkThread1(newTask, startSignal, endSignal);
      Runnable worker2= new WorkThread2(newTask, startSignal, endSignal);

      executorService.execute(worker1);
      executorService.execute(worker2);
      executorService.execute(deadlockDetector);
      executorService.shutdown();
      startSignal.countDown();
      // wait until all threads are finished
      while(!executorService.isTerminated()){
        try{
          endSignal.await();
        } catch(InterruptedException e){

        }
      }
      endSignal.countDown();

      System.out.println("Hidden lock-ordering deadlock simulator Done");
    }

  }

}
