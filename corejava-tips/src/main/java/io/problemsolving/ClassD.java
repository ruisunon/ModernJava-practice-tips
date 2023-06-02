package io.problemsolving;

public class ClassD implements InterfaceB, InterfaceC {

  @Override
  public void display() {
    InterfaceB.super.display();
  }

}
