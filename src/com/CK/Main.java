package com.CK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] a = new int[]{0, 4};
        int[] b = new int[]{3, 5};
        int[] c = new int[]{6, 10};
        int[] d = new int[]{10, 18};

        int[][] intervals = new int[2][2];
        intervals[0] = a;
        intervals[1] = b;
//        intervals[2] = c;
//        intervals[3] = d;

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.merge(intervals)));
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals[0].length == 0) return new int[0][0];
        if (intervals.length == 1) return intervals;
        int length = intervals.length;
        int[] begins = new int[length];
        int[] ends = new int[length];
        for (int i = 0; i < length; i++) {
            begins[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(begins);
        Arrays.sort(ends);
        ArrayList<int[]> resIntervals = new ArrayList<>();
        int i = 1, j = 0, left = begins[0], right = 0;

        while (j < length - 1) {
            if (begins[i] <= ends[j]) {
                if (i != length - 1) i++;
                if (j != length - 1) j++;
            } else {
                right = ends[j];
                resIntervals.add(new int[]{left, right});
                left = begins[i];
                if (i != length - 1) i++;
                if (j != length - 1) j++;
            }
        }
        resIntervals.add(new int[]{left, ends[length-1]});
        int[][] res = new int[resIntervals.size()][2];
        for (int r=0;r<resIntervals.size();r++){
            res[r] = resIntervals.get(r);
        }
        return res;
    }
}

// Find overlap between start and end O(n log(n))
class Solution2 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}