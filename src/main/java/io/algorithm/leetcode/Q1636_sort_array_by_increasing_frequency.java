package io.algorithm.leetcode;
//Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If
// multiple values have the same frequency, sort them in decreasing order.
//
//    Return the sorted array.
//    Example 1:
//
//    Input: nums = [1,1,2,2,2,3]
//    Output: [3,1,1,2,2,2]
//    Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
//
//    Example 2:
//
//    Input: nums = [2,3,1,3,2]
//    Output: [1,3,3,2,2]
//    Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
//
//    Example 3:
//
//    Input: nums = [-1,1,-6,4,5,-6,1,4,1]
//    Output: [5,-1,4,4,-6,-6,1,1,1]
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    -100 <= nums[i] <= 100

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Q1636_sort_array_by_increasing_frequency {
  private static final Logger logger = LoggerFactory.getLogger(Q1636_sort_array_by_increasing_frequency.class);
  public int[] frequencySort(int[] nums) {
    Map<Integer, Integer> map=new HashMap<>();
    Integer[] inputs = Arrays.stream(nums).boxed().toArray(Integer[]::new);
    for(int num: nums) map.merge(num, 1, (old, def)-> old+1);
    Comparator<Integer> byFreq= (Integer a, Integer b) -> {
        if(map.get(a)!=map.get(b)){
          return map.get(b).compareTo(map.get(a));
        } else {
          return b.compareTo(a);
        }
    };
    Arrays.sort(inputs, byFreq);
    for(Integer n: inputs) {
      logger.info("map:=" + n);
    }
    return nums;
   }
  public int[] frequencySort1(int[] nums) {
    Map<Integer, Integer> map=new HashMap<>();
    for(int num: nums) map.merge(num, 1, (old, def)-> old+1);
    Comparator<Integer> byFreq= (Integer a, Integer b) -> {
      if(map.get(a)!=map.get(b)){
        return map.get(b).compareTo(map.get(a));
      } else {
        return b.compareTo(a);
      }
    };
    int[] inputs = Arrays.stream(nums).boxed().sorted(byFreq).mapToInt(Integer::intValue).toArray();
    for(Integer n: inputs) {
      logger.info("map:=" + n);
    }
    return nums;
  }
  public static void main(String[] args) {
    int[] nums = {-1,1,-6,4,5,-6,1,4,1};
    Q1636_sort_array_by_increasing_frequency solution = new Q1636_sort_array_by_increasing_frequency();
    int[] res = solution.frequencySort1(nums);
    System.out.println(res);
  }
}
