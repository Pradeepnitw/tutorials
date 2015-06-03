package leetcode.l1505;

public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int reach = 0;
        int i = 0;
        
        while (i < nums.length && i <= reach) {
            reach = Math.max(nums[i] + i, reach);
            i++;
        }
        if (i == nums.length) return true;
        else return false;
    }
}
