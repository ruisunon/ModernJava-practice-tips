package io.algorithm.leetcode;

import java.util.Arrays;
import java.util.List;

public class Q1595_minimum_cost_to_connect_2_groups_of_points {

    //    You are given two groups of points where the first group has size1 points, the second group has size2 points,
    //    and size1 >= size2.
    //
    //        The cost of the connection between any two points are given in an size1 x size2 matrix where cost[i][j]
    //        is the cost of connecting point i of the first group and point j of the second group. The groups are
    //        connected if each point in both groups is connected to one or more points in the opposite group. In
    //        other words, each point in the first group must be connected to at least one point in the second group,
    //        and each point in the second group must be connected to at least one point in the first group.
    //
    //    Return the minimum cost it takes to connect the two groups.
    //    Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
    //    Output: 4
    //    Explanation: The optimal way of connecting the groups is:
    //    1--A
    //    2--B
    //    2--C
    //    3--A
    //    This results in a total cost of 4.
    //    Note that there are multiple points connected to point 2 in the first group and point A in the second group
    //    . This does not matter as there is no limit to the number of points that can be connected. We only care
    //    about the minimum total cost.
    //    Example 3:
    //
    //    Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
    //    Output: 10
    //    Constraints:
    //
    //    size1 == cost.length
    //    size2 == cost[i].length
    //    1 <= size1, size2 <= 12
    //    size1 >= size2
    //    0 <= cost[i][j] <= 100

    // use bitmask dp:
    // i: 0001, 0010, 0011, 0100, 0101, 0110, 0111,1000, 1001, 1010, 1011, 1100, 1101, 1110, 1111,
    // dp[i][state]: considering the first i points on the left, and the visited points on the right ,
    // minimum cost
    // 0000 ~ 1111 totally : 2^n
    // dp[i][j] : = min cost to cover first i points in group1 and first j points in group2
    // dp[i][s] : = min cost to cover first i points in group1 and a set of points s in group2. s is represented as a bitmask
    // dp[i-1][...] + somthing i does => dp[i][state]

    // dp[i-1][...] +  i visits a subset of state => dp[i][state]
    // dp[i-1][state] + i visits an already visited point with minimum edge => dp[i][state]

  public int maxProductPath(int[][] grid) {
    Long[][] max=new Long[grid.length][grid[0].length];
    Long[][] min=new Long[grid.length][grid[0].length];
    help(grid, 0, 0, max, min);
    if(max[0][0]<0) return -1;
    return (int)(max[0][0]%1000000007);

  }
  void help (int[][] grid, int r, int c, Long[][] max,  Long[][] min ){
    if(r==grid.length-1 && c==grid[0].length-1){
      max[r][c]=(long)grid[r][c];
      min[r][c]=(long)grid[r][c];
      return;
    }
    if(max[r][c]!=null) return;
    max[r][c]=Long.MIN_VALUE;
    min[r][c]=Long.MAX_VALUE;
    if(r+1<grid.length){
      help(grid, r+1, c, max, min);

      max[r][c]=Math.max(max[r+1][c]*grid[r][c],max[r][c]);

      max[r][c]=Math.max(min[r+1][c]*grid[r][c],max[r][c]);

      min[r][c]=Math.min(max[r+1][c]*grid[r][c],min[r][c]);

      min[r][c]=Math.min(min[r+1][c]*grid[r][c],min[r][c]);
    }
    if(c+1<grid[0].length){
      help(grid, r, c+1, max, min);

      max[r][c]=Math.max(max[r][c+1]*grid[r][c],max[r][c]);

      max[r][c]=Math.max(min[r][c+1]*grid[r][c],max[r][c]);

      min[r][c]=Math.min(max[r][c+1]*grid[r][c],min[r][c]);

      min[r][c]=Math.min(min[r][c+1]*grid[r][c],min[r][c]);
    }
  }
  public static void main(String[] args) {

    Q1595_minimum_cost_to_connect_2_groups_of_points solution = new Q1595_minimum_cost_to_connect_2_groups_of_points();
    int[][] lists={{2, 5, 1}, {3, 4, 7}, {8, 1, 2}, {6, 2, 4}, {3, 8, 8}};
    int res = solution.maxProductPath(lists);

    System.out.println(res);
  }
}
