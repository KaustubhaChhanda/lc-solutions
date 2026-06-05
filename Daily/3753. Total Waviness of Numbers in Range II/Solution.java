class Solution {
  private class Result {
    long ways;
    long wave;

    Result(long ways, long wave) {
      this.ways = ways;
      this.wave = wave;
    }
  }

  public long totalWaviness(long num1, long num2) {
    return solve(num2) - solve(num1 - 1);
  }

  private long solve(long num) {
    if (num < 0)
      return 0;
    char[] s = String.valueOf(num).toCharArray();

    Result[][][][][] memo = new Result[s.length][11][11][2][2];

    return dfs(0, 10, 10, 1, 1, s, memo).wave;
  }

  private Result dfs(int index, int prev2, int prev1, int isTight, int isLeadingZero, char[] s, Result[][][][][] memo) {
    if (index == s.length) {
      return new Result(1, 0);
    }

    if (memo[index][prev2][prev1][isTight][isLeadingZero] != null) {
      return memo[index][prev2][prev1][isTight][isLeadingZero];
    }

    int limit = (isTight == 1) ? s[index] - '0' : 9;
    long totalWays = 0;
    long totalWave = 0;

    for (int d = 0; d <= limit; d++) {
      int newTight = (isTight == 1 && d == limit) ? 1 : 0;
      int newLz = (isLeadingZero == 1 && d == 0) ? 1 : 0;

      int newPrev1 = (newLz == 1) ? 10 : d;
      int newPrev2 = (newLz == 1) ? 10 : prev1;

      Result res = dfs(index + 1, newPrev2, newPrev1, newTight, newLz, s, memo);

      totalWays += res.ways;
      totalWave += res.wave;

      if (prev2 != 10 && prev1 != 10 && newLz == 0) {
        boolean isPeak = (prev1 > prev2) && (prev1 > d);
        boolean isValley = (prev1 < prev2) && (prev1 < d);

        if (isPeak || isValley) {
          totalWave += res.ways;
        }
      }
    }

    return memo[index][prev2][prev1][isTight][isLeadingZero] = new Result(totalWays, totalWave);
  }
}