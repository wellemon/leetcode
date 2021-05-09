/**
 * Minimum Number of Days to Make m Bouquets
 *
 * 二分法
 */
public class Solution {

    public int minDays(int[] bloomDay, int m, int k) {
        if (k * m < bloomDay.length) return -1;

        int l = Arrays.stream(bloomDay).min().getAsInt();
        int r = Arrays.stream(bloomDay).max().getAsInt();

        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid, bloomDay, m, k)) { // 返回 true 表示 mid 天满足制作花束要求
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
        // 严格意义上 l == r 时,且为左边界或右边界时,未检查，应该再次检查边界
        // return check(l, bloomDay, m, k) ? l : -1;
        // 但由于左边界为至少制作一朵花的天数，可以完成花束
        // 右边界，在进入函数初已判断需要的花的朵数满足制作花束要求，右边界必定能完成花束
    }

    /**
     * @param day      第几天
     * @param bloomDay 开花天数的数组
     * @param m        要求 m 束花朵
     * @param k        需要 k 朵相邻的花朵
     * @return 返回是否 day 天可以制作 m 束花
     */
    private boolean check(int day, int[] bloomDay, int m, int k) {
        int bouquets = 0;
        int flowers = 0;
        for (int i = 0; i < bloomDay.length && bouquets < m; i++) {
            if (bloomDay[i] <= day) {
                flowers++;
            } else {
                flowers = 0;
            }
            // 判断 flowers 是否满足花束所需要求
            if (flowers == k) {
                bouquets++;
                flowers = 0;
            }
        }

        return bouquets >= m;
    }
}
