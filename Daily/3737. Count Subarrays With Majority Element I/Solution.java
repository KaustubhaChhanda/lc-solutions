class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int[] pre = new int[nums.length];
        pre[0] = (nums[0] == target ? 1 : 0);

        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i - 1] + (nums[i] == target ? 1 : 0);
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int freq = 0;

                if (i == 0) {
                    freq = pre[j];
                } else {
                    freq = pre[j] - pre[i - 1];
                }

                if (freq > (j - i + 1) / 2) {
                    count++;
                }
            }
        }

        return count;
    }
}