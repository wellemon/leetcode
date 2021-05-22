/**
 * 1） 根据奇偶数情况判断输赢
 * 2） 异或和为 0 时，先手直接判断胜利；这个条件容易忽视
 */
public class Solution {
    public boolean xorGame(int[] nums) {
        if (nums.length % 2 == 0) return true;

        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        return xorSum == 0 ? true : false;
    }
}
