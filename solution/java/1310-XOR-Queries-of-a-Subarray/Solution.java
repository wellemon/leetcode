public class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;
        // 前缀异或
        // prefix[i] 表示前 i 个数异或的结果
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i-1] ^ arr[i - 1];
        }

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int left = queries[i][0], right = queries[i][1] + 1;
            res[i] = prefix[left] ^ prefix[right];
        }

        return res;
    }
}
