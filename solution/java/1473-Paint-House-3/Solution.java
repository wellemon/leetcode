/**
 * 由暴力法转向动态规划
 * 暴力法：dfs
 * 条件：m * 房子, n * 粉刷方式, target * 街区，之前粉刷过不能再粉刷
 * 遍历所有房子，遍历所有粉刷方式，满足街区条件情况下，最小消耗
 * dfs(int houseNo, int pre, int neighCnt, int costSum)
 * 时间复杂度是 n^m 指数级别，会超时
 * <p>
 * 优化
 * 方式一 记忆化搜索：
 * dfs(int houseNo, int pre, int neighCnt, int costSum)
 * 可以发现深度搜索中 costSum 用来累加最后更新 ans，是因变量
 * 其他几个变量都是自变量
 * int memorySearch(int houseNo, int pre, int neighCnt) 将自底向顶的计算最小值返回
 * 同时保存三维数组中 int[][][] memo = new int[m+1][n+1][target+2];
 * 关于 cnt 设置最大值为 target+1 的原因，因为 memo 存储的时候，进入函数后剪枝，memo 多一点空间防止 「索引越界」
 * memo[i][j][cnt] 表示记忆 第 i 间房子，上一间房子颜色为 j，已经有的分区数量为 cnt，后续房子完成涂色花费的最小值
 * <p>
 * 方式二 动态规划：
 * 记忆化搜索其实就是动态规划的一种特殊形式，动态规划自顶向下
 * int[][][] dp = new int[m+1][n+1][target+1];
 * dp[i][j][cnt] 表示第 i 间房使用第 j 种配色方案，分区数量为 cnt 时的总花费
 * 最后考虑：
 * 第 m个房子，邻居分区正好为 target，此时各种配色方案（1-n）中 dp 的最小值
 * int[m][?][target] 即为涂色花费的最小值
 */
class Solution {

    int[][][] memo; // 用于记忆化搜索
    int INF = 0x3f3f3f3f; // 0xffffffff / 2 用来存储无效值
    int ans = INF; // 用于深度搜索
    int[] houses;
    int[][] cost;
    int m, n, target;

    /**
     * 深度搜索算法
     */
    public int minCostDfs(int[] houses, int[][] cost, int m, int n, int target) {
        this.houses = houses;
        this.cost = cost;
        this.m = m;
        this.n = n;
        this.target = target;

        dfs(0, -1, 0, 0); // 深度搜索 - 用例 9 超时
        return ans == INF ? -1 : ans;
    }

    /**
     * 记忆化搜索
     */
    public int minCostMemorySearch(int[] houses, int[][] cost, int m, int n, int target) {
        this.houses = houses;
        this.cost = cost;
        this.m = m;
        this.n = n;
        this.target = target;
        memo = new int[m + 1][n + 1][target + 2];

        int ans = memorySearch(0, -1, 0);
        return ans == INF ? -1 : ans;
    }

    /**
     * 动态规划
     * dp[i][j][k] 表示第 i 间房使用第 j 种配色方案，分区数量为 k 时的总花费
     */
    public int minCostDp(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m + 1][n + 1][target + 1];

        // 初始化 分区为 0 的情况
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j][0] = INF;
            }
        }

        // 遍历每间房子
        for (int i = 1; i <= m; i++) {
            int color = houses[i - 1];
            // 遍历每一种配色方案
            for (int j = 1; j <= n; j++) {
                // 遍历每种目标值
                for (int k = 1; k <= target; k++) {

                    if (k > i) { // 分区数量不可能大于房子数量，无效化数据
                        dp[i][j][k] = INF;
                        continue;
                    }

                    if (color == 0) { // 第 i 间房间未上色
                        int min = INF; // min 表示 dp[i-1] 时以下两种情况的最小值
                        // 上一间房颜色为 p
                        for (int p = 1; p <= n; p++) {
                            if (p != j) { // 情况一：颜色不同
                                min = Math.min(min, dp[i - 1][p][k - 1]);
                            } else { // 情况二： 颜色相同，之前就是 k 分区
                                min = Math.min(min, dp[i - 1][p][k]);
                            }
                        }
                        dp[i][j][k] = min + cost[i - 1][j - 1];

                    } else { // 第 i 间房间已经上色，因此不需要上色
                        if (color == j) { // 房间真实颜色 color 与 遍历时的颜色 j 相同时状态才有意义
                            int min = INF; // min 表示 dp[i-1] 时以下两种情况的最小值
                            for (int p = 1; p <= n; p++) {
                                if (p != j) { // 情况一：颜色不同
                                    min = Math.min(min, dp[i - 1][p][k - 1]);
                                } else { // 情况二： 颜色相同，之前就是 k 分区
                                    min = Math.min(min, dp[i - 1][p][k]);
                                }
                            }
                            dp[i][j][k] = min;
                        } else {
                            dp[i][j][k] = INF;
                        }
                    }
                }
            }
        }

        // dp[m][?][target] 中的最小值及为最小开销
        int ans = INF;
        for (int j = 1; j <= n; j++) {
            ans = Math.min(ans, dp[m][j][target]);
        }
        return INF == ans ? -1 : ans;
    }

    /**
     * 深度搜索
     *
     * @param houseNo  当前处理房间编号
     * @param pre      当前处理房间颜色
     * @param neighCnt 当前邻居分区数量
     * @param costSum  当前的上色成本
     */
    private void dfs(int houseNo, int pre, int neighCnt, int costSum) {
        // 剪枝
        if (costSum >= ans || neighCnt > target) return;
        // 正常终止条件 计算 ans
        if (houseNo == m) {
            if (neighCnt == target) {
                ans = Math.min(ans, costSum);
            }
            return;
        }
        int color = houses[houseNo];
        if (color == 0) {
            // 遍历所有配色方案
            for (int j = 1; j <= n; j++) {
                int add = j == pre ? 0 : 1;
                dfs(houseNo + 1, j, neighCnt + add, costSum + cost[houseNo][j - 1]);
            }
        } else { // 当前房间已上色
            int add = color == pre ? 0 : 1;
            dfs(houseNo + 1, color, neighCnt + add, costSum);
        }
    }

    /**
     * 记忆化搜索
     * memo[i][j][cnt] 表示记忆 第 i 间房子，上一间房子颜色为 j，已经有的分区数量为 cnt，此时后续房子完成涂色花费的最小值
     * 其实 memo 就是保存 memorySearch 相同参数的返回值；下次需要再次调用相同参数的 方法时不再递归继续，快速返回
     *
     * @param houseNo  当前处理房间编号
     * @param pre      当前处理房间颜色
     * @param neighCnt 当前邻居分区数量
     * @return 返回当前层数的最小 cost
     */
    private int memorySearch(int houseNo, int pre, int neighCnt) {
        // 剪枝
        if (neighCnt > target) {
            return INF;
        }
        // 记忆条件
        if (houseNo > 0 && memo[houseNo][pre][neighCnt] != 0) {
            return memo[houseNo][pre][neighCnt];
        }
        // 终止条件遍历了所有的房子
        if (houseNo == m) {
            if (neighCnt == target) {
                return 0;
            }
            return INF;
        }

        int min = INF;
        int color = houses[houseNo];
        // 判断当前房子是否已经被涂色
        if (color == 0) {
            for (int j = 1; j <= n; j++) {
                int add = pre == j ? 0 : 1;
                memo[houseNo + 1][j][neighCnt + add] = memorySearch(houseNo + 1, j, neighCnt + add);
                int costSum = memo[houseNo + 1][j][neighCnt + add] + cost[houseNo][j - 1];
                min = Math.min(min, costSum);
            }
        } else { // 当前房间不能粉刷
            int add = pre == color ? 0 : 1;
            memo[houseNo + 1][color][neighCnt + add] = memorySearch(houseNo + 1, color, neighCnt + add);
            min = memo[houseNo + 1][color][neighCnt + add];
        }
        return min;
    }
}
