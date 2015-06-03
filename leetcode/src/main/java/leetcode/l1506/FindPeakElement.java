package leetcode.l1506;


public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        return recurse(nums, 0, nums.length - 1);
    }
    
    private int recurse(int[] nums, int lo, int hi) {
        if (lo == hi) return lo;
        int mid = (hi+lo)/2;
        
        if (mid != 0 && nums[mid-1] > nums[mid]) {
            return recurse(nums, lo, mid - 1);
        } else if (mid != nums.length - 1 && nums[mid+1] > nums[mid]) {
            return recurse(nums, mid + 1, hi);
        } else return mid;
        
    }

}
