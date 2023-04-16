package io.algorithm.leetcode;

import java.util.HashSet;

//Given a string s, return the maximum number of unique substrings that the given string can be split into.
//
//    You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms
//    the original string. However, you must split the substrings such that all of them are unique.
//
//    A substring is a contiguous sequence of characters within a string.
//    Example 1:
//
//    Input: s = "ababccc"
//    Output: 5
//    Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b',
//    'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
//    Example 2:
//
//    Input: s = "aba"
//    Output: 2
//    Explanation: One way to split maximally is ['a', 'ba'].
//    Example 3:
//
//    Input: s = "aa"
//    Output: 1
//    Explanation: It is impossible to split the string any further.
//
//    Constraints:
//
//    1 <= s.length <= 16
//
//    s contains only lower case English letters.
public class Q1593_Split_a_String_into_the_max_number_of_unique_substrings {
  public int maxUniqueSplit(String s) {
    if(s.length()<1) return s.length();
    return helper(s, new HashSet<String>());
  }
  private int helper(String s, HashSet<String> seen){
    int max=0;
    for(int i=1; i<=s.length();i++){
      String cand=s.substring(0, i);
      if(!seen.contains(cand)){
        seen.add(cand);
        max=Math.max(max, 1+helper(s.substring(i), seen));
        seen.remove(cand);
      }
    }
    return max;
  }


  public static void main(String[] args) {
    int[] nums = {3,7,4,2,1,8,8,9};
    int  p = 6;
    Q1593_Split_a_String_into_the_max_number_of_unique_substrings solution = new Q1593_Split_a_String_into_the_max_number_of_unique_substrings();
    String s = "84532abacddaddaddddefgedd", t = "34852";
    int res = solution.maxUniqueSplit(s);
    System.out.println(res);
  }
}
