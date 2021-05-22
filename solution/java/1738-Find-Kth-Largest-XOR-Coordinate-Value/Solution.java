import java.util.PriorityQueue;
/**
 * 异或前缀和 + 优先队列
 * 根据异或规律画图得：
 * prefix[i][j] = prefix[i - 1][j] ^ prefix[i][j - 1] ^ prefix[i - 1][j - 1] ^ matrix[i-1][j-1]
 *
 * 优先队列的小根堆 大小为 k；
 * 当 pq 的 size 小于 k 时，直接将 prefix 加入到 pq中
 * 当 pq 的 size 等于 k 时：
 *      1） prefix 的值 > 堆顶，将堆顶取出来；将 prefix 加入
 *      2） prefix 的值 <= 堆顶，抛弃此 prefix
 */
public class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = prefix[i - 1][j] ^ prefix[i][j - 1] ^ prefix[i - 1][j - 1] ^ matrix[i-1][j-1];
                if (pq.size() < k) {
                    pq.add(prefix[i][j]);
                } else {
                    if (prefix[i][j] > pq.peek()) {
                        pq.poll();
                        pq.add(prefix[i][j]);
                    }
                }
            }
        }
        return pq.peek();
    }
}
