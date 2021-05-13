/**
 * 状态设计：dp[step][col]
 * dp 表示通过 step 步，现在处于 col 位置，有多少个方案
 * 状态转移的三个过程分别为 stay left right
 * dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + dp[i-1][j+1];
 * 初始化 dp[0][0] = 1
 */
public class Solution {
    public int numWays(int steps, int arrLen) {
        final int MOD = (int) (1e9 + 7);
        // 压缩空间 maxCol
        int maxCol = Math.min(steps / 2, arrLen - 1);
        int[][] dp = new int[steps + 1][maxCol + 1];
        dp[0][0] = 1;   // 初始化：走过 0 步 算一种方案
        for (int i = 1; i <= steps; i++) {
            // 回原点能抵达的最远处 边缘优化
            // int edge = Math.min(maxCol, steps - i);
            // for (int j = 0; j <= edge; j++) {
            for (int j = 0; j <= maxCol; j++) {
                // stay
                dp[i][j] = dp[i - 1][j];
                // left
                if (j - 1 >= 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                // right
                if (j + 1 <= maxCol) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
            }
        }
        return dp[steps][0];
    }
}
