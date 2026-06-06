class Solution {
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;

        for (long n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2 && n!= max1){
                max3 = max2;
                max2 = n;
            } else if (n > max3 && n!= max1 && n!= max2){
                max3 = n;
            }
        }
        
        return max3 == Long.MIN_VALUE ? (int) max1 : (int) max3; 
    }
}