package net.modernjava.concurrency;
import java.util.Random;

public class DeadlockThreads {

  public static Object lock1 = new Object();
  public static Object lock2 = new Object();

  public static void main(String[] args) {

    new Thread1().start();
    new Thread2().start();

//    Message message = new Message();
//    (new Thread (new Writer(message))).start();
//    (new Thread (new Reader(message))).start();
  } // end of main methods

  private static class Thread1 extends Thread {
    public void run() {
      synchronized (lock1) {
        System.out.println("Thread 1:  Has lock1");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        System.out.println("Thread 1: Waiting for lock 2");
        synchronized (lock2) {
          System.out.println("Thread 1: Has lock1 and lock2");
        }
        System.out.println("Thread 1: Released lock2");
      }
      System.out.println("Thread 1: Realeased lock1. Exiting...");
    }
  }

  private static class Thread2 extends Thread{
    public void run() {
      synchronized (lock2) {
        System.out.println("Thread 2:  Has lock2");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        System.out.println("Thread 2: Waiting for lock1");
        synchronized (lock1) {
          System.out.println("Thread 2: Has lock1 and lock2");
        }
        System.out.println("Thread 2: Released lock1");
      }
      System.out.println("Thread 2: Realeased lock2. Exiting...");
    }
  }
} //end of class DeadlockThreads
class Message{
  private String message;
  private boolean empty = true;

  public synchronized String read() {
    while (empty) {
      try{
        wait();
      }catch(InterruptedException e){

      }
    }
    empty = true;
    notifyAll();
    return message;
  }

  public synchronized void write(String message){
    while(!empty){
      try{
        wait();
      }catch (InterruptedException e){

      }
    }
    empty = false;
    this.message = message;
    notifyAll();
  }

}

class Writer implements Runnable{
  private Message message;

  public Writer(Message message){
    this.message = message;
  }

  public void run(){
    String messages[] = {
        "Humpty dumpty sat on a wall",
        "Humpty dumpty had a great fall",
        "All the king's horses and all the king's sons",
        "Couldn't put humpty together again"
    };

    Random random = new Random();
    for(int i = 0; i < messages.length; i++){
      message.write(messages[i]);
      try{
        Thread.sleep(random.nextInt(2000));
      }catch(InterruptedException e){

      }
    }

    message.write("Finished");
  }

}

class Reader implements Runnable{
  private Message message;

  public Reader(Message message) {
    this.message = message;
  }

  public void run(){
    Random random = new Random();
    for(String latestMessage = message.read(); !latestMessage.equals("Finished");
        latestMessage = message.read()){
      System.out.println(latestMessage);
      try{
        Thread.sleep(random.nextInt(2000));
      }catch(InterruptedException e){

      }
    }
  }

}
