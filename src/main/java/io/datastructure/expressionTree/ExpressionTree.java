package io.datastructure.expressionTree;

import java.util.Stack;

public class ExpressionTree {
  // A utility function to check if 'c' is an operator

  boolean isOperator(char c) {
    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
      return true;
    }
    return false;
  }
  void inorder(Node t){
    if(t!=null){
      inorder(t.left);
      System.out.println(t.value+ " "); // left, root, right -> inorder
      inorder(t.right);
    }
  }
  // Return root of constructed tree for given postfix expression
  Node buildTree(char postfix[]){
    Stack<Node> stack=new Stack<Node>();// we can use Deque or ArrayDeque for stack
    Node t1, t2, t;
    // traverse through every character of input expression
    for(int i=0; i<postfix.length; i++){
      // if operand, simply push into stack
      if(!isOperator(postfix[i])){
        t=new Node(String.valueOf(postfix[i]));
        stack.push(t);
      } else {
        // operator
        t=new Node(String.valueOf(postfix[i]));
        // pop 2 top nodes, store to top of stack
        t1=stack.pop();
        t2=stack.pop();
        // make them children of this operator
        t.right=t1;
        t.left=t2;
        // System.out.println(t1 + " " + t2);
        // add this subexpression to stack
        stack.push(t);
      }
    }
    // only  element will be root of expression tree
    t=stack.peek();
    stack.pop();
    return t;
  }
  public static void main(String args[]) {

    ExpressionTree et = new ExpressionTree();
    String postfix = "ab+ef*g*-";
    char[] charArray = postfix.toCharArray();
    Node root = et.buildTree(charArray);
    System.out.println("infix expression is");
    et.inorder(root);

  }
}
