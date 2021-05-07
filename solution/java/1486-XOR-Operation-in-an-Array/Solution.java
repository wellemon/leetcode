/**
 * 位运算：异或
 * 异或运算的重要性质：
 * 性质一：交换率和结合率
 * 性质二：相同数值异或，结果为 0
 * 性质三：任意数值和 0 异或，结果为数值本身
 * 性质四：自反性
 * 性质五：4i ⊕ (4i + 1) ⊕ (4i + 2) ⊕ (4i + 3) = 0
 *        例如 0 ⊕ 1 ⊕ 2 ⊕ 3 = 0
 */
public class Solution {
    /**
     * 模拟
     */
    public int xorOperation(int n, int start) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= start + 2 * i;
        }
        return res;
    }

    /**
     * 数学方法
     * 求 start⊕(start+2)⊕(start+4)⊕...⊕(start+2∗(n−1))
     * 可知数列为 差值为 2 的等差数列，要运用异或运算的性质5 即 4i ⊕ (4i + 1) ⊕ (4i + 2) ⊕ (4i + 3) = 0，需要将差值变为 1 即可
     * 将原始式子除以 2，相当于右移运算，左边所有位数数值保持不变，原数字向右移一位；原式最后一位只和 start 相关，且所有项奇偶性相同
     * 可得：s⊕(s+1)⊕(s+2)⊕...⊕(s+(n−1)) << 1 | last; 其中 s = start >> 1, last 为最后一位
     * 高位： sumXOR(s-1) ^ sumXOR(s+n-1)
     * 最后一位：如果 start 为偶数，最后一位为 0；如果 start 为奇数，和异或的次数 n 相关
     *          即 last = n & start & 1
     */
    public int xorOperationMath(int n, int start) {
        int s = start >> 1;
        int prefix = sumXor(s-1) ^ sumXor(s + n - 1);
        int last = n & start & 1;
        int res = prefix << 1 | last;
        return res;
    }

    /**
     * 推导 sumXor
     * 根据性质五可得  0 ⊕ 1 ⊕ ...... ⊕ x
     * 每四个计算等于 0，再根据性质三可得：
     *           x                          x = 4i  , i为整数
     * sumxor = (x-1) ⊕ x                   x = 4i+1, i为整数
     *          (x-2) ⊕ (x-1) ⊕ x           x = 4i+2, i为整数
     *          (x-3) ⊕ (x-2) ⊕ (x-1) ⊕ x   x = 4i+3, i为整数
     * 化简可得：
     *          x                           x = 4i  , i为整数
     * sumxor = 1                           x = 4i+1, i为整数
     *          x + 1                       x = 4i+2, i为整数
     *          0                           x = 4i+3, i为整数
     */
    private int sumXor(int x) {
        if (0 == x % 4) return x;
        else if (1 == x % 4) return 1;
        else if (2 == x % 4) return x + 1;
        else return 0;  // 3 == x % 4
    }

}