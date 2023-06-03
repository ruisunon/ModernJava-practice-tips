package net.modernjava.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockQueue {
    private ReentrantLock lock;
    private static final AtomicInteger counter = new AtomicInteger(0);

    public ReentrantLockQueue() {
        lock = new ReentrantLock();
    }
    public void printDocument(String document) {
        lock.lock(); // Acquire the lock

        try {
            System.out.println("Printing document: " + document);
            Thread.sleep(1000); // Simulate the printing process
            System.out.println("Document printed: " + document);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Release the lock in the finally block
        }
    }
    public static void main(String[] args) {
        ReentrantLockQueue printingQueue = new ReentrantLockQueue();

        // Create multiple threads to simulate printing documents
        Thread thread1 = new Thread(() -> printingQueue.printDocument("Document 1"));
        Thread thread2 = new Thread(() -> printingQueue.printDocument("Document 2"));
        Thread thread3 = new Thread(() -> printingQueue.printDocument("Document 3"));

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        atomicMain();

    }
    public static void atomicMain() {
        // Create and start multiple threads
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // Atomically increment the counter
                    counter.incrementAndGet();
                }
            });
            thread.start();
        }

        // Wait for all threads to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of the counter
        System.out.println("Counter value: " + counter.get());
    }
}
