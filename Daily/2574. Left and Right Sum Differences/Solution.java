class Solution {
    public int[] leftRightDifference(int[] nums) {
        int leftSum = 0, rightSum = 0;
        int[] ans = new int[nums.length];
        for (int num : nums) rightSum += num;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            rightSum -= val;
            ans[i] = Math.abs(leftSum - rightSum);
            leftSum += val;
        }

        return ans;
    }
}