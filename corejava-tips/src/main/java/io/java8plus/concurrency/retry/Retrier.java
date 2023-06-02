package io.java8plus.concurrency.retry;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Retrier<T> {
  private final Supplier<T> supplier; // Supplier to retry
  private final long maxTries;   // Maximum number of tries
  private final long delay;   //Delay between attempts
  private final TimeUnit timeUnit;

  private long currentTry=0;
  private CompletableFuture<T> result;

  public Retrier(Supplier<T> supplier, long maxTries, long delay, TimeUnit timeUnit){
    this.supplier=supplier;
    this.maxTries=maxTries;
    this.delay=delay;
    this.timeUnit=timeUnit;
  }

  public CompletableFuture<T> retry(){
    result=new CompletableFuture<>();
    tryOnce();
    return result;
  }
  private void tryOnce(){
       CompletableFuture.supplyAsync(supplier).handle(this::handle);
  }
  private T handle( T res, Throwable t){
    if(t!=null){
      if(this.currentTry++ < maxTries || maxTries<=0){
        CompletableFuture.delayedExecutor(delay, timeUnit).execute(this::tryOnce);
      }else {
        result.completeExceptionally(t);
      }

    }
    return res;
  }
}
