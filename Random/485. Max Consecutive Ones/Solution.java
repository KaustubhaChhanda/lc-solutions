class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int currMax = 0;
        for (int num : nums) {
            if (num == 1) {
                currMax++;
                max = Math.max(max, currMax);
            } else {
                currMax = 0;
            }
        }

        return max;
    }
}