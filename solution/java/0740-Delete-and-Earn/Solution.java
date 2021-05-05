/**
 * 思路
 * 将 nums 数组相同的值存储在 sums 数组中；
 * sums 数组的大小为 nums 数组的最大值；
 * sums 数组进行打家劫舍计算收益最大值
 */
class Solution {
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num:nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] sum = new int[maxVal + 1];
        for (int num:nums) {
            sum[num] += num;
        }
        return rob(sum);
    }

    /**
     * 打家劫舍
     * dp0 表示当前位置不拿，拿到价值的最大值
     * dp1 表示当前位置拿，拿到价值的最大值
     * 状态转义方程：
     * dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
     * dp[i][1] = dp[i-1][0] + sum[i]
     */
    int rob(int[] sum){
        int size = sum.length;
        int dp0 = 0, dp1 = sum[0];
        for (int i=1; i<size; i++) {
            int tmp = dp0;
            dp0 = Math.max(dp0, dp1);
            dp1 = tmp + sum[i];
        }
        return Math.max(dp0, dp1);
    }
}