/**
 * 计算前缀异或和数组 xorSum[]
 * 定义：xorSum[i] = arr[0] ⊕ ... ⊕ arr[i-1]
 *
 * 计算 xorSum[i] ^ xorSum[j]: 其中 j > i
 *  xorSum[i] ⊕ xorSum[j] = (arr[0] ⊕ ... ⊕ arr[i-1]) ⊕ (arr[0] ⊕ ... ⊕ arr[j-1])
 *  xorSum[i] ⊕ xorSum[j] = arr[i] ⊕ ... ⊕ arr[j-1]
 *
 *  根据 a 和 b 的定义可得：
 *      a = xorSum[i] ⊕ xorSum[j]
 *      b = xorSum[j] ⊕ xorSum[k+1]
 *  要 a = b，则有
 *      xorSum[i] ⊕ xorSum[j] = xorSum[j] ⊕ xorSum[k+1]
 *  即：
 *      xorSum[i] = xorSum[k+1]
 *  只要满足上式的 枚举 0 <= i < j <= k < n 的下表 i 和 k 即可以算出三元数组的个数
 */
public class Solution {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        // 计算 xorSum 数组
        int[] xorSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            xorSum[i] = xorSum[i - 1] ^ arr[i - 1];
        }

        int ans = 0;
        // 枚举 i 和 k
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (xorSum[i] == xorSum[k+1]) {
                    ans += k - i;
                }
            }
        }

        return ans;
    }
}
