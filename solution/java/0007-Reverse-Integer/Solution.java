/**
 * 思路：
 * 反转方法： 初始化 res = 0
 *          1. 计算 x mod 10 求余数 remain ，即为 x 个位数
 *          2. res * 10 左移，后加上 remain
 *          3. x / 10   右移, 如果 x ！= 0 回到步骤 1 循环
 *
 * 限制条件 1：反转后若超过 32位 有符号整数范围就返回 0
 *    因此： 计算 res 时判断：
 *          Integer.MIN_VALUE <= res * 10 + remain <= Integer.MAX_VALUE
 * 限制条件 2：题目要求不能使用 double 保存 res * 10 + remain，
 *    因此： 计算 res 前判断是否溢出：
 *          正数溢出情况： res > (Integer.MAX_VALUE - remain) / 10
 *          负数溢出情况： res < (Integer.MIN_VALUE - remain) / 10
 *
 *  代码如下：
 */
public class Solution {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            // remain = x % 10
            if (x > 0 && res > (Integer.MAX_VALUE - x % 10) / 10 || x < 0 && res < (Integer.MIN_VALUE - x % 10) / 10){
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}