package io.java8plus.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class ThreadNumber {
	int count=0;
	ReentrantLock lock =new ReentrantLock();
	
	void increment() {
		count =count +1;
	}
	
	void incrementSync() {
		synchronized(this) {
			count=count +1;
		}
	}
	void incrementReentrantLock() {
		lock.lock();
		try {
			count++;
		}finally {
			lock.unlock();
		}
	}
	
	void test() {
		ExecutorService executor= Executors.newFixedThreadPool(3);
		IntStream.range(0, 10000).forEach(i-> executor.submit(this::incrementReentrantLock)); // method reference
		executor.submit(() -> {
			String threadName=Thread.currentThread().getName();
			System.out.println("hello " + threadName);
		});
		
		executor.submit(() -> {
		    System.out.println("Locked: " + lock.isLocked());
		    System.out.println("Held by me: " + lock.isHeldByCurrentThread());
		    boolean locked = lock.tryLock();
		    System.out.println("Lock acquired: " + locked);
		});
		
		this.shutDown(executor);
	}
	
	void shutDown(ExecutorService executor) {
		try {
			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			System.out.println("after attempt to shutdown executor");
			executor.awaitTermination(5,  TimeUnit.SECONDS);
		} catch(InterruptedException e) {
			System.err.println("tasks interrupte");
		}
		finally {
			if(!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("...shutdown finished");
		}
		
	}
	public static void main(String[] args) {
		ThreadNumber tn=new ThreadNumber();
		tn.test();
		tn.stampedLock();
		System.out.println("count=" + tn.count);
	}
	//stampedLock
	// Java 8 ships with a new kind of lock called StampedLock which also support read and write locks 
	// just like in the example above. In contrast to ReadWriteLock the locking methods of a StampedLock 
	// return a stamp represented by a long value. You can use these stamps to either release a lock 
	// or to check if the lock is still valid. Additionally stamped locks support another lock mode called optimistic locking.
	
	void stampedLock() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
		    //long stamp = lock.writeLock();
		    long stamp1=lock.tryOptimisticRead();
		    try {
		    	System.out.print("Optimistic lock valid " + lock.validate(stamp1));
		    	TimeUnit.SECONDS.sleep(1);
		    	System.out.print("Optimistic lock valid " + lock.validate(stamp1));
		    	TimeUnit.SECONDS.sleep(2);
		    	System.out.print("Optimistic lock valid " + lock.validate(stamp1));
		        map.put("foo", "bar");
		    } catch (InterruptedException e) {
			
				e.printStackTrace();
			} finally {
		        //lock.unlockWrite(stamp);
				lock.unlock(stamp1);
		    }
		});

		executor.submit(() -> {
		    long stamp = lock.writeLock();
		    try {
		        System.out.println("Write Lock acquired");
		        TimeUnit.SECONDS.sleep(4);
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		        lock.unlock(stamp);
		        System.out.println("...Write done");
		    }
		});

		this.shutDown(executor);
	}
}
