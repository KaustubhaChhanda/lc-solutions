import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        if (restrictions == null || restrictions.length == 0) {
            return n - 1;
        }

        int m = restrictions.length;
        int[][] arr = new int[m + 2][2];
        
        for (int i = 0; i < m; i++) {
            arr[i] = restrictions[i];
        }
        
        arr[m] = new int[]{1, 0};
        arr[m + 1] = new int[]{n, n - 1};
        
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 1; i < arr.length; i++) {
            int dist = arr[i][0] - arr[i - 1][0];
            arr[i][1] = Math.min(arr[i][1], arr[i - 1][1] + dist);
        }
        
        for (int i = arr.length - 2; i >= 0; i--) {
            int dist = arr[i + 1][0] - arr[i][0];
            arr[i][1] = Math.min(arr[i][1], arr[i + 1][1] + dist);
        }
        
        int maxHeight = 0;
        for (int i = 1; i < arr.length; i++) {
            int h1 = arr[i - 1][1];
            int h2 = arr[i][1];
            int dist = arr[i][0] - arr[i - 1][0];
            
            int localMax = (h1 + h2 + dist) / 2; 
            maxHeight = Math.max(maxHeight, localMax);
        }
        
        return maxHeight;
    }
}