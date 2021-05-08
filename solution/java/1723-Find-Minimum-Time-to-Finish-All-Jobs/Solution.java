public class Solution {
    int[] sum;
    int[] jobs;
    int n, k;
    int ans = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        n = jobs.length;
        this.k = k;
        this.jobs = jobs;
        sum = new int[k];
        dfs(0, 0, 0);
        return ans;
    }

    /**
     * @param used 当前分配了多个工人
     * @param work 当前处理第几个工作
     * @param max  保存当前分配方式的最大工作时间
     */
    private void dfs(int used, int work, int max) {
        if (max >= ans) return;
        if (work == n) {
            ans = max;
            return;
        }
        // 优先分配空闲工人
        if (used < k) {
            sum[used] += jobs[work];
            dfs(used + 1, work + 1, Math.max(max, sum[used]));
            sum[used] -= jobs[work];
        }

        for (int i = 0; i < used; i++) {
            sum[i] += jobs[work];
            dfs(used, work + 1, Math.max(max, sum[i]));
            sum[i] -= jobs[work];
        }
    }
}
