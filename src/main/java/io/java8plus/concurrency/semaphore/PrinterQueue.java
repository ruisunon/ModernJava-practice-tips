package io.java8plus.concurrency.semaphore;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// This class represent the printer queue/ printer. This class has 3 main attributes which control the logic of
// selecting a free printer out of 3 printers and lock it for printing a job. After printing the document, printer is
// released so that it is again free and available for printing a new job from print queue.
//
//    This class has two methods getPrinter() and releasePrinter() which are responsible for acquiring a free printer
//    and putting it back in free printers pool.
//
//    Another method printJob() actually does the core job i.e. acquiring a printer, execute print job and then
//    release the printer.
//
//    It uses below two variables for doing the job:
//
//    semaphore : This variable keep track of no. of printers used at any point of time.
//    printerLock : Used for locking the printer pool before checking/acquiring a free printer out of three available
//    printers.

class PrinterQueue {

  //This Semaphore will keep track of no. of printers used at any point of time.
  private final Semaphore semaphore;

  //While checking/acquiring a free printer out of three available printers, we will use this lock.
  private final Lock printerLock;

  //This array represents the pool of free printers.
  private boolean freePrinters[];

  public PrinterQueue() {
    semaphore = new Semaphore(3);
    freePrinters = new boolean[3];
    Arrays.fill(freePrinters, true);
    printerLock = new ReentrantLock();
  }

  public void printJob(Object document) {
    try {
      //Decrease the semaphore counter to mark a printer busy
      semaphore.acquire();

      //Get the free printer
      int assignedPrinter = getPrinter();

      //Print the job
      Long duration = (long) (Math.random() * 10000);
      System.out.println(
          Thread.currentThread().getName() + ": Printer " + assignedPrinter + " : Printing a Job during " + (duration
              / 1000) + " seconds :: Time - " + new Date());
      Thread.sleep(duration);

      //Printing is done; Free the printer to be used by other threads.
      releasePrinter(assignedPrinter);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());

      //Increase the semaphore counter back
      semaphore.release();
    }
  }

  //Acquire a free printer for printing a job
  private int getPrinter() {
    int foundPrinter = -1;
    try {
      //Get a lock here so that only one thread can go beyond this at a time
      printerLock.lock();

      //Check which printer is free
      for (int i = 0; i < freePrinters.length; i++) {
        //If free printer found then mark it busy
        if (freePrinters[i]) {
          foundPrinter = i;
          freePrinters[i] = false;
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      //Allow other threads to check for free priniter
      printerLock.unlock();
    }
    return foundPrinter;
  }

  //Release the printer
  private void releasePrinter(int i) {
    printerLock.lock();
    //Mark the printer free
    freePrinters[i] = true;
    printerLock.unlock();
  }
}