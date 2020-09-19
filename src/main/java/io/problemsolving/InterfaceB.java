package io.problemsolving;
public interface InterfaceB extends InterfaceA {
    default void display() {
      System.out.println("B");
    }
}
