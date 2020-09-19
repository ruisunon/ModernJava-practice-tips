package io.algorithm.leetcode;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Queue;
    import java.util.Vector;

public class Q1585_solution {

    public boolean isTransformable(String s, String t) {
      int len=s.length();
      List<Queue<Integer>> queues = new ArrayList<Queue<Integer>>();
      for(int i = 0; i <10; i++){
        queues.add(i, new LinkedList<Integer>());
      }
      for(int i=0; i<len; i++){
        queues.get(s.charAt(i)-'0').add(i);
      }
      for(int k=0; k<len; k++) {
        int d = t.charAt(k) - '0';
        if (queues.get(d).size()<1) return false;
        Integer target = (Integer) queues.get(d).peek();
        for (int j = 0; j < d; j++) {
          if (queues.get(j).size() > 0) {
            Integer val = (Integer) queues.get(j).peek();
            if (val < target)
              return false;
          }
        }
        queues.get(d).poll();
      }

      return true;
  }

  public static void main(String[] args) {

    Q1585_solution solution = new Q1585_solution();
    String s = "1", t = "2";
    boolean res = solution.isTransformable(s, t);
    System.out.println(res);
  }
}

