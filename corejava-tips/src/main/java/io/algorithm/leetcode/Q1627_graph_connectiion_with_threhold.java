package io.algorithm.leetcode;
//
//We have n cities labeled from 1 to n. Two different cities with labels x and y are directly connected by a
// bidirectional road if and only if x and y share a common divisor strictly greater than some threshold. More
// formally, cities with labels x and y have a road between them if there exists an integer z such that all of the
// following are true:
//
//    x % z == 0,
//    y % z == 0, and
//    z > threshold.
//
//    Given the two integers, n and threshold, and an array of queries, you must determine for each queries[i] = [ai,
//    bi] if cities ai and bi are connected (i.e. there is some path between them).
//
//    Return an array answer, where answer.length == queries.length and answer[i] is true if for the ith query, there
//    is a path between ai and bi, or answer[i] is false if there is no path.
//Constraints:
//
//    2 <= n <= 104
//    0 <= threshold <= n
//    1 <= queries.length <= 105
//    queries[i].length == 2
//    1 <= ai, bi <= cities
//    ai != bi
//  算法分析
//并查集
//
//    x与y之间存在大于t的约数 等价于 x 与 y 之间有边
//
//    如何高效建图?（直接两重循环枚举求gcd时间复杂度是O(n2logn)
//
//    ，会超时）
//
//    所有满足题意的约数有[t + 1, n]，假设d是区间中其中一个约数，则公约数是d的序列有d, 2d, 3d, 4d, ... kd，将该序列的元素形成一个集合，
//    共有 n/d 个，则[t + 1, n]区间的所有约数个数有 n/(t+1)+n/(t+2)+…+n/n<=n(1+12+13+…+1n)=O(nlogn)
//
//    步骤
//
//    1、建图，从d = t + 1开始到n，枚举出所有不大于n的d的倍数，将该序列合并成一个集合
//    2、遍历所有的访问，返回a元素的根结点和b元素的根结点是否一样即可，将结果加入到链表中


import java.util.ArrayList;
import java.util.List;

public class Q1627_graph_connectiion_with_threhold {

  static int N = 10000 + 10;
  static int[] p = new int[N];

  static int find(int x) {
    if (p[x] != x) {
      p[x] = find(p[x]);
    }
    return p[x];
  }

  public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
    for (int i = 1; i <= n; i++) {
      p[i] = i;
    }

    for (int i = threshold + 1; i <= n; i++) {
      for (int d = i * 2; d <= n; d += i) {
        p[find(d)] = find(i);
      }
    }

    List<Boolean> res = new ArrayList<Boolean>();
    for (int i = 0; i < queries.length; i++) {
      int a = queries[i][0];
      int b = queries[i][1];
      res.add(find(a) == find(b));
    }
    return res;
  }
}

// www.acwing.com/solution/content/22880/
