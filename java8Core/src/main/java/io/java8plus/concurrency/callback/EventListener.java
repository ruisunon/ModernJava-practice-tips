package io.java8plus.concurrency.callback;

public interface EventListener {
  // this can be any type of method
  void onSuccessEvent();
  void onFailEvent();

}
