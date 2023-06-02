package io.algorithm.leetcode;

import java.util.Stack;

//    Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that
//    represents this expression.
//
//    Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before
//    their operators. For example, the postfix tokens of the expression 4*(5-(2+7)) are represented in the array
//    postfix = ["4","5","7","2","+","-","*"].
//
//    The class Node is an interface you should use to implement the binary expression tree. The returned tree will
//    be tested using the evaluate function, which is supposed to evaluate the tree's value. You should not remove
//    the Node class; however, you can modify it as you wish, and you can define other classes to implement it if
//    needed.
//
//    A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a
//    binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to
//    operands (numbers), and internal nodes (nodes with two children) correspond to the operators '+' (addition),
//    '-' (subtraction), '*' (multiplication), and '/' (division).
//
//    It's guaranteed that no subtree will yield a value that exceeds 109 in absolute value, and all the operations
//    are valid (i.e., no division by zero).
//    Follow up: Could you design the expression tree such that it is more modular? For example, is your design able
//    to support additional operators without making changes to your existing evaluate implementation?
//
// Constraints:
//
//    1 <= s.length < 100
//    s.length is odd.
//    s consists of numbers and the characters '+', '-', '*', and '/'.
//    If s[i] is a number, its integer representation is no more than 105.
//    It is guaranteed that s is a valid expression.
//    The absolute value of the result and intermediate values will not exceed 109.
//    It is guaranteed that no expression will include division by zero.
public class Q1628_design_an_expression_tree_with_evaluate_function {
  public Node buildTree(String[] postfix) {
    Stack<Node> stack=new Stack<>();
    Node t=null;
    Node t1=null;
    Node t2=null;
    for(int i=0; i<postfix.length; i++){
      if(!"+-*/".contains(postfix[i])){
        t=new TreeNode(postfix[i]);
        stack.push(t);
      }else {
        t=new TreeNode(postfix[i]);
        t1=stack.pop();
        t2=stack.pop();
        t.right=t1;
        t.left=t2;
        stack.push(t);
      }
    }
    t=stack.peek();
    stack.pop();
    return t;
  }
}
/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
  public abstract int evaluate();
  // define your fields here
  String value;
  Node left, right;
  public Node(){
    this.value=null;
    this.right=null;
    this.left=null;
  }

};

class TreeNode extends Node {

  public TreeNode(String value){
    this.value=value;
    this.left=null;
    this.right=null;
  }
  public int evaluate(){
    if(value==null) return 0;
    if(left==null && right==null) {
      if(value.charAt(0)=='-') return (-Integer.valueOf(value.substring(1)));
      if (value.charAt(0)=='+') return Integer.valueOf(value.substring(1));
      return Integer.valueOf(value);
    }
    int leftVal=left.evaluate();
    int rightVal=right.evaluate();
    if(this.value.equals("+")){
      return leftVal+rightVal;
    }
    if(this.value.equals("-")) return leftVal-rightVal;
    if(this.value.equals("*")) return leftVal * rightVal;
    return leftVal/rightVal;
  }
}


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */