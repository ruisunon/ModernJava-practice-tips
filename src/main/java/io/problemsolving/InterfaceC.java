package io.problemsolving;

public interface InterfaceC extends InterfaceA {

  default void display() {
    System.out.println("C");
  }
}
