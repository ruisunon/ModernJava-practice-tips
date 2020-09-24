package io.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Q1591_Stringe_Printer_II {
  //  There is a strange printer with the following two special requirements:
  //
  //  On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover
  //  up the existing colors in the rectangle.
  //  Once the printer has used a color for the above operation, the same color cannot be used again.
  //  You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of
  //  the grid.
  //
  //  Return true if it is possible to print the matrix targetGrid, otherwise, return false.
  //  Example 1:
  //
  //  Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
  //  Output: true
  //  Example 2:
  //
  //  Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
  //  Output: true
  //  Example 3:
  //
  //  Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
  //  Output: false
  //  Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different
  //  turns.
  //      Example 4:
  //
  //  Input: targetGrid = [[1,1,1],[3,1,3]]
  //  Output: false
  //
  //  Constraints:
  //
  //  m == targetGrid.length
  //  n == targetGrid[i].length
  //1 <= m, n <= 60
  //      1 <= targetGrid[row][col] <= 60
  //
  //
  // Solution: Dependency graph
  //  For each color C find the maximum rectangle to cover it. Any other color C’ in this rectangle is a dependency
  //  of C,
  //  e.g. C’ must be print first in order to print C.
  //
  //      Then this problem reduced to check if there is any cycle in the dependency graph.
  //
  //  e.g.
  //    1 2 1
  //    2 1 2
  //    1 2 1
  //  The maximum rectangle for 1 and 2 are both [0, 0] ~ [2, 2]. 1 depends on 2, and 2 depends on 1. This is a
  //  circular reference and no way to print.
  //
  //  Time complexity: O(C*M*N)
  //  Space complexity: O(C*C)

  public boolean isPrintable(int[][] targetGrid) {
    int kMax = 60;
    HashMap<Integer, List<Integer>> colors = new HashMap<>();

    int m = targetGrid.length;
    int n = targetGrid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int color = targetGrid[i][j];
        List<Integer> edges = colors.getOrDefault(color, new ArrayList<>(
            List.of(m-1,n-1, 0, 0))); // top most, left most, bottom most, right most
        edges.set(0, Math.min(i, edges.get(0)));
        edges.set(1, Math.min(j, edges.get(1)));
        edges.set(2, Math.max(i, edges.get(2)));
        edges.set(3, Math.max(j, edges.get(3)));
        colors.put(color, edges);
      }
    }
    HashMap<Integer, HashSet<Integer>> overlapRects =new HashMap<>();
    for(int color: colors.keySet()){
      List<Integer> edges=colors.get(color);
      for(int row=edges.get(0); row<=edges.get(2); row++){
        for (int column=edges.get(1); column<=edges.get(3); column++){
          if(targetGrid[row][column]!=color){
            HashSet<Integer> overlaps=overlapRects.getOrDefault(color, new HashSet<>());
            overlaps.add(targetGrid[row][column]);
            overlapRects.put(color, overlaps);
          }
        }
      }
    }
    for(int color: colors.keySet()){
      if(!canRemove(color, overlapRects, new int[61])) return false;
    }
    return true;
  }
  public boolean canRemove(int color, HashMap<Integer, HashSet<Integer>> overlappingRects, int[] visited){
   if(visited[color]==2) return true; // visited =2;
    if(visited[color]==1) return false; // visiting=1;
    if(!overlappingRects.containsKey(color)) return true;
    visited[color]=1;
    for(int overlapColor: overlappingRects.get(color)){
      if (!canRemove(overlapColor, overlappingRects, visited)) {
        return false;
      }
      overlappingRects.get(color).remove(color);
    }
    visited[color]=2;
    return true;
  }

  public static void main(String[] args) {

    Q1591_Stringe_Printer_II solution = new Q1591_Stringe_Printer_II();
    int[][] targetGrid= {{1,1,1,1},{1,1,3,3},{1,1,3,4},{5,5,1,4}};
    int[][] targetGrid1= {{ 1,1,1,1},{1,2,2,1},{1,2,2,1},{1,1,1,1}};
    //boolean res = solution.isPrintable(targetGrid);
    boolean res1 = solution.isPrintable(targetGrid1);
    System.out.println(res1);
  }
}

