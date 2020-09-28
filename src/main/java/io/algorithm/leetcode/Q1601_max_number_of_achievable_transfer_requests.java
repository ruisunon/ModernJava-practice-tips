package io.algorithm.leetcode;
//We have n buildings numbered from 0 to n - 1. Each building has a number of employees. It's transfer season, and
// some employees want to change the building they reside in.
//
//    You are given an array requests where requests[i] = [fromi, toi] represents an employee's request to transfer
//    from building fromi to building toi.
//
//    All buildings are full, so a list of requests is achievable only if for each building, the net change in
//    employee transfers is zero. This means the number of employees leaving is equal to the number of employees
//    moving in. For example if n = 3 and two employees are leaving building 0, one is leaving building 1, and one is
//    leaving building 2, there should be two employees moving to building 0, one employee moving to building 1, and
//    one employee moving to building 2.
//
//    Return the maximum number of achievable requests.
//    Example 3:
//
//    Input: n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
//    Output: 4
//
//
//    Constraints:
//
//    1 <= n <= 20
//    1 <= requests.length <= 16
//    requests[i].length == 2
//    0 <= fromi, toi < n

public class Q1601_max_number_of_achievable_transfer_requests {
  public int maximumRequests(int n, int[][] requests) {
    int len=requests.length;
    int ans=0;
    for (int mask=0; mask<1<<len; mask++){
      int[] buildings=new int[n];
      int count=0;
      for(int i=0; i<len; i++){
        if((mask & (1<<i))>0){
          buildings[requests[i][0]] -=1;
          buildings[requests[i][1]] +=1;
          count ++;
        }
      }
      for(int i=0; i<n; i++){
        if(buildings[i]==0){
          ans =Math.max(ans, count);
        }
      }

    }
    return ans;

  }
  public static void main(String[] args) {
    int n=4;
    int [][] prefs={{0,3},{3,1},{1,2},{2,0}};
    Q1601_max_number_of_achievable_transfer_requests solution = new Q1601_max_number_of_achievable_transfer_requests();
    int res = solution.maximumRequests(n, prefs);
    System.out.println(res);
  }
}
