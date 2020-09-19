package io.java8plus.concurrency.callback;

public interface MyCallback {
  void onSuccess();
  void onError(String err);
}