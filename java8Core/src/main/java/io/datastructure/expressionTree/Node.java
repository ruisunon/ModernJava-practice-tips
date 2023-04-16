package io.datastructure.expressionTree;

public class Node {
  String value;
  Node left, right;

  Node(String item) {
    value = item;
    left = right = null;
  }
}
