package io.datastructure.expressionTree;

public class EvaluateTreeArray {
  int toInt(String str){
    int num=0;
    // check if the integral value is negative or not.
    // if it is not negative, generate the number normally
    char[] array=str.toCharArray();
    if(array[0]!='-'){
     num=Integer.parseInt(str);
    } else {
      // if it is negative, calculate the +ve number first ignoring the sign
      // and invert the sign at the end
//      for(int i=1; i<str.length(); i++){
//        num=num*10+ (array[i]-'0');
//      }1
      num=Integer.parseInt(str.substring(1));
      num=-num;
    }
    return num;
  }
  //This function receives a node of the syntax tree and recursively evaluate it
  int eval(Node root){
    // empty tree;
    if(root==null) return 0;
    // leaf node
    if(root.left==null && root.right==null){
      return toInt(root.value);
    }
    int leftVal=eval(root.left);
    int rightVal=eval(root.right);
    if(root.value.equals("+")){
      return leftVal+rightVal;
    }
    if(root.value.equals("-")){
      return leftVal-rightVal;
    }
    if(root.value.equals("*")){
      return leftVal*rightVal;
    }
    if(root.value.equals("/")){
      return leftVal/rightVal;
    }
    return 0;
  }
  public static void main(String args[]) {

    EvaluateTreeArray et = new EvaluateTreeArray();

    // create a syntax tree
    Node root = new Node("+");
    root.left = new Node("*");
    root.left.left = new Node("5");
    root.left.right = new Node("-4");
    root.right = new Node("-");
    root.right.left = new Node("100");
    root.right.right = new Node("20");
    System.out.println(et.eval(root));
  }
  }
