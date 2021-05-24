package pers.dynamicprogramming.p664;

/**
 * 状态定义：
 * dp[l][r] 表示从位置 l 到 r 这一段打印成目标结果消耗的最小打印次数
 */
public class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int l = n - 1; l >= 0; l--) {
            dp[l][l] = 1;   // 长度位 1 的情况
            for (int r = l + 1; r < n; r++) {
                if (s.charAt(l) == s.charAt(r)) {
                    dp[l][r] = dp[l][r - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = l; k < r; k++) {
                        min = Math.min(min, dp[l][k] + dp[k + 1][r]);
                    }
                    dp[l][r] = min;
                }
            }
        }
        return dp[0][n - 1];
    }

    public int strangePrinter2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // 初始化 len 等于 1 的情况
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;    // 右边界
                // 如果 r右边界字符 和 l左边界字符相同，那么 dp[l][r] = dp[l][r-1]
                // 如 ababba, 最右边字符总是可以先和最左边一起打印出来，之后覆盖打印中间的字符
                // 如何证明
                if (s.charAt(l) == s.charAt(r)) {
                    dp[l][r] = dp[l][r - 1];
                } else {
                // 如果不相等，只能拆分打印，取拆分的最优解
                // 如 ab; abb; abbab
                    int min = Integer.MAX_VALUE;
                    for (int k = l; k < r; k++) {
                        min = Math.min(min, dp[l][k] + dp[k + 1][r]);
                    }
                    dp[l][r] = min;
                }
            }
        }
        return dp[0][n - 1];
    }
}
