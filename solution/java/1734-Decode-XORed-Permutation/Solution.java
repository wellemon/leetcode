/**
 * 已知：
 * 1） encode[i] = perm[i-1] ⊕ perm[i]
 * 2） n 为 perm的长度，因此 encode 的长度为 n-1
 *      encode[1] ⊕ encode[3] ⊕ encode[5] ... ⊕ encode[n-2]         (1)
 * =    perm[1] ⊕ perm[2] ⊕ perm[3] ⊕ ... ⊕ perm[n-1] ⊕ perm[n-1]
 *
 * 根据题目可知 perm 为前 n 个正整数的排列
 *      perm[0] ⊕ permp[1] ⊕ ... ⊕ perm[n-1]                        (2)
 * =    1 ⊕ 2 ⊕ 3 ⊕ ... ⊕ n
 *
 * 将(1)和(2)异或可得：
 * (1) ⊕ (2) = perm[0]
 *
 * 通过以下式子可求解答案：
 * perm[i] = perm[i-1] ⊕ encode[i-1]
 */
public class Solution {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] perm = new int[n];

        // perm 是前 n 个正整数，异或所有 perm 的值
        int a = 0;
        for (int i = 1; i <= n; i++) {
            a ^= i;
        }
        // 异或所有偶数的 encoded 值，即除了 perm[0] 的值
        int b = 0;
        for (int i = 1; i < n - 1; i += 2) {
            b ^= encoded[i];
        }
        // 求 perm
        perm[0] = a ^ b;
        for (int i = 1; i < n; i++) {
            perm[i] = perm[i - 1] ^ encoded[i - 1];
        }

        return perm;
    }
}