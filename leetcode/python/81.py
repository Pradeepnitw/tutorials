class Solution(object):
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        if not nums:
            return -1
        pivot_idx = len(nums) - 1
        for i, n in enumerate(nums):
            if i != len(nums) - 1 and nums[i] > nums[i+1]:
                pivot_idx = i
        target_idx = self.binary_search(nums[:pivot_idx+1], target)
        if target_idx is not None:
            return target_idx
        target_idx = self.binary_search(nums[pivot_idx+1:], target)
        if target_idx is not None:
            return pivot_idx+1+target_idx
        return -1
    
    def binary_search(self, nums, target):
        """
        :type nums: int[] sorted asc
        :type target: int
        :rtype: int
        """
        if not nums:
            return
        lo = 0
        hi = len(nums) - 1
        idx = hi/2

        while lo < hi and nums[idx] != target:
            idx = lo + (hi - lo)/2
            if nums[idx] < target:
                # target is located on the right side
                lo = idx + 1  # inclusive indexing
            else:
                # target is located on the left side
                hi = idx - 1
        if nums[idx] == target:
            return idx
