package io.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Q1625_smallest_string {

  public String findLexSmallestString(String s, int a, int b) {
    Set<String> seen=new HashSet<>();
    seen.add(s);
    int len=s.length();
    String ans=s;
    Deque<String> stack = new ArrayDeque<String>();
    stack.add(s);
    while(!stack.isEmpty()){
      String curs=stack.pop();
      char[] charArray=curs.toCharArray();

      ans=isSmaller(ans, curs);
      // operation a
      for(int i=1; i<len-1; i+=2){
        int k=charArray[i]-'0';
        int n=(k+a)%10;
        charArray[i]=(char)(n+'0');
      }
      if(len%2==0) {
        int k=charArray[len-1]-'0';
        int n=(k+a)%10;
        charArray[len-1]=(char)(n+'0');
      }
      String news=String.valueOf(charArray);
      if(!seen.contains(news)){
        stack.add(news);
        seen.add(news);
      }
      // operation b
      charArray=curs.toCharArray();
      char[] part1= Arrays.copyOf(charArray, len-b);
      char[] part2=Arrays.copyOfRange(charArray, len-b, len);
      news=String.valueOf(part2) + String.valueOf(part1);
      if(!seen.contains(news)){
        stack.add(news);
        seen.add(news);
      }
    }
    return ans;
  }

  private String isSmaller(String str1, String str2){
    for(int i=0; i<str1.length(); i++){
      if(str1.charAt(i)<str2.charAt(i)) return str1;
      if(str1.charAt(i)>str2.charAt(i)) return str2;
    }
    return str1;
  }
  public static void main(String[] args) {
    String test="74";
    int a=5;
    int b=1;
    Q1625_smallest_string solution = new Q1625_smallest_string();
    String res = solution.findLexSmallestString(test, a,b);
    System.out.println(res);
  }
}