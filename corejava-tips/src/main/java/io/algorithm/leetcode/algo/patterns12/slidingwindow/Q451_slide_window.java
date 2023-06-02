package io.algorithm.leetcode.algo.patterns12.slidingwindow;

import java.util.*;

public class Q451_slide_window {

    public String frequencySort3(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        StringBuilder sortedString = new StringBuilder();

        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((c1, c2) -> map.get(c2) - map.get(c1));
        for(char key: map.keySet()) {
            maxHeap.offer(key);
        }

        while (!maxHeap.isEmpty()) {
            char key = maxHeap.poll();
            for(int i = 0; i < map.get(key); i++) {
                sortedString.append(key);
            }
        }

        return sortedString.toString();
    }
//        1 Construct a map to count the frequency of each char
//        2 Create a bucket to hold lists of characters based on the frequency on corresponding index. For example, [{},{a}, {b,c}] means ‘a’ appears once, b and c appear twice.
//        3 Build the result string from the end of the bucket to the start.

    public String frequencySort1(String s) {
        StringBuilder res = new StringBuilder();
        if (s == null || s.length() == 0) {
            return res.toString();
        }
        // A map of each char and its frequency
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        // An array of lists of chars, at the index (frequency)
        List<Character> [] bucket = new List[s.length() + 1];
        // An short way to iterate map
        for (char key : map.keySet()) {
            // build a frequency list of each char
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<Character>();
            }
            bucket[freq].add(key);
        }
        // Iterate the frequency list from the end to start (index high to low)
        // build the result string
        for (int i = bucket.length-1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    // append 'frequnecy' times
                    for (int j = 0; j < i; j++) {
                        res.append(c);
                    }
                }
            }
        }
        return res.toString();
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> output = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((e1, e2) -> e2.getValue()-e1.getValue());

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        while(k > 0) {
            output.add(maxHeap.remove().getKey());
            k--;
        }

        return output;
    }

    public int[] frequencySort(int[] nums)
    {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i : nums)
            map.put(i,map.getOrDefault(i,0)+1);

        PriorityQueue<Pair2> pq=new PriorityQueue<>(Collections.reverseOrder());

        for(Map.Entry<Integer,Integer> m:map.entrySet())
        {
            Pair2 p=new Pair2();
            p.freq=m.getValue();
            p.key=m.getKey();

            pq.add(p);
        }
        int[] output=new int[nums.length];
        int y=0;
        while(!pq.isEmpty())
        {
            Pair2 x=pq.poll();
            int val=x.key;
            int count=x.freq;

            for(int i=1;i<=count;i++)
                output[y++]=val;
        }
        return output;
    }
    public String frequencySort(String s) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int[] freqs = new int[128];
        StringBuilder ans = new StringBuilder("");
        for(int i = 0; i < s.length(); i++) {
            freqs[(int) s.charAt(i)]++;
        }

        for(int i = 0; i < freqs.length; i++) {
            if(freqs[i] > 0) {
                maxHeap.add(new Pair(i, freqs[i]));
            }
        }
        while(!maxHeap.isEmpty()) {
            for(int i = 0; i < maxHeap.peek().frequency; i++) {
                ans.append((char) maxHeap.peek().letterIndex);
            }
            maxHeap.poll();
        }
        return ans.toString();
    }
    class Pair implements Comparable<Pair> {
        int letterIndex;
        int frequency;
        public Pair(int letterIndex, int frequency) {
            this.letterIndex = letterIndex;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Pair other) {
            return this.frequency - other.frequency;
        }
    }
    class Pair2 implements Comparable<Pair2>
    {
        int freq;
        int key;

        Pair2()
        {
            this.freq=freq;
            this.key=key;
        }

        public int compareTo(Pair2 p)
        {
            if(this.freq!=p.freq)
                return -(this.freq-p.freq);
            else
                return this.key-p.key;
        }
    }
}
