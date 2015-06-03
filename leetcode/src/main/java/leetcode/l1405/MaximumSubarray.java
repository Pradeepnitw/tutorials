package leetcode.l1405;

public class MaximumSubarray {
    public int maxSubArray(int[] a) {
        int sum = Integer.MIN_VALUE;
        int tempSum = Integer.MIN_VALUE;
        for (int i: a) {
            tempSum = (tempSum<0?0:tempSum) + i;
            sum = Math.max(tempSum, sum);
        }
        return sum;
    }
}
