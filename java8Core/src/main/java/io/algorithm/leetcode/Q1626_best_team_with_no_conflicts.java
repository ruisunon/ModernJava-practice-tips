package io.algorithm.leetcode;
//You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest
// overall score. The score of the team is the sum of scores of all the players in the team.
//
//    However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a
//    strictly higher score than an older player. A conflict does not occur between players of the same age.
//
//    Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith
//    player, respectively, return the highest overall score of all possible basketball teams.
//
//    Example 1:
//
//    Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
//    Output: 34
//    Explanation: You can choose all the players.
//
//    Example 2:
//
//    Input: scores = [4,5,6,5], ages = [2,1,2,1]
//    Output: 16
//    Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of
//    the same age.
//
//    Constraints:
//
//    1 <= scores.length, ages.length <= 1000
//    scores.length == ages.length
//    1 <= scores[i] <= 106
//    1 <= ages[i] <= 1000


import java.util.Arrays;

public class Q1626_best_team_with_no_conflicts {
  static int N=1010;
  static Pair[] pair=new Pair[N];
  public int bestTeamScore(int[] scores, int[] ages) {
    int n=scores.length;
    for(int i=0; i<n; i++){
      pair[i] = new Pair(scores[i], ages[i]);
    }
    Arrays.sort(pair, 0, n, (x, y)-> {
      return x.a==y.a? x.s-y.s: x.a-y.a ;
    });
    int[] f=new int[n];
    int res=0;
    for(int i=0; i<n; i++){
      f[i]=pair[i].s;

      for(int j=0; j<i; j++){
        if(pair[j].s <= pair[i].s){
          f[i]=Math.max(f[i], f[j]+pair[i].s);
        }
      }
      res=Math.max(res, f[i]);
    }
    return res;
  }
}
class Pair
{
  int a, s;
  Pair(int s, int a)
  {
    this.s = s;
    this.a = a;
  }
}
