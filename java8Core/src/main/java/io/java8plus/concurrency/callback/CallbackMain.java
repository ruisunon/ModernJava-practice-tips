package io.java8plus.concurrency.callback;

public class CallbackMain {

  public interface Visitor{
    int doJob(int a, int b);
  }


  public static void main(String[] args) {
    Visitor adder = new Visitor(){
      public int doJob(int a, int b) {
        return a + b;
      }
    };

    Visitor multiplier = new Visitor(){
      public int doJob(int a, int b) {
        return a*b;
      }
    };
    System.out.println(functionThatTakesDelegate((a, b) -> {return a*b;} , 10, 20));
    System.out.println(adder.doJob(10, 20));
    System.out.println(multiplier.doJob(10, 20));

  }

  @FunctionalInterface
  public interface NotDotNetDelegate {
    int doSomething(int a, int b);
  }

  public static int functionThatTakesDelegate(NotDotNetDelegate del, int a, int b) {
    // ...
    return del.doSomething(a, b);
  }
}