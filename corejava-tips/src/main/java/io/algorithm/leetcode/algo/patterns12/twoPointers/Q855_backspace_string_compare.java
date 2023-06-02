package io.algorithm.leetcode.algo.patterns12.twoPointers;

//Problem Statement
//        You have given two strings S and T, write a program to check if they are equal when they are types into empty text editors.
//        Note: # means a backspace character.
//        Solution
//        The Stack data structure can be used to solve this problem, following are the steps :-
//
//        Create the character array from the input Strings S and T.
//        Iterate through the character array of S and at each iteration, check if the character is #.
//        If yes, then pop the top element of the stack.
//        If no, then push the current element on the top of the stack.
//        Create string Stemp from the remaining elements of the stack and clear it.
//        Repeat steps 2, 3, 4 and 5 for character array of T.
//        Compare string Stemp and Ttemp, if they are equal return true otherwise return false.

import java.util.Stack;

public class Q855_backspace_string_compare {
    public static void main(String[] args) {

        boolean result =backspaceCompare("ab#v#f","aa#c#f");
        System.out.print(result);
    }

    /* Solution */
    static public boolean backspaceCompare(String S, String T) {

        Stack<Character> stack = new Stack<>();
        char[] Sarray = S.toCharArray();
        for(int i=0; i<Sarray.length; i++){
            if(Sarray[i] == '#'){
                if(stack.size() >0){
                    stack.pop();
                }
            }else {
                stack.push(Sarray[i]);
            }
        }
        String first = String.valueOf(stack);
        System.out.print("first=" + first);
        stack.clear();

        char[] Tarray = T.toCharArray();
        for(int i=0; i<Tarray.length; i++){
            if(Tarray[i] == '#'){
                if(stack.size() >0){
                    stack.pop();
                }
            }else {
                stack.push(Tarray[i]);
            }
        }
        String second = String.valueOf(stack);
        System.out.print("second=" + second);
        return first.equals(second);
    }
}
