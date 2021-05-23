/**
 * Tried tree
 */
public class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie trie = new Trie();
        // 1. 将所有数字添加进入字典树
        for (int num : nums) {
            trie.add(num);
        }
        // 2. 查询
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = trie.getMaxXorWithLimit(queries[i][0], queries[i][1]);
        }
        return ans;
    }

}

class Trie {

    final int HIGH_BIT = 29; // 0-29 总共 30 位，num < 10^9 所以 30位足够保存所有数字
    Trie[] children = new Trie[2]; // index 等于 0 或 1
    int min = Integer.MAX_VALUE;

    // 将数字加入到字典树中
    void add(int num) {
        Trie cur = this;
        cur.min = Math.min(cur.min, num);
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (cur.children[bit] == null) {
                cur.children[bit] = new Trie();
            }
            cur = cur.children[bit];
            cur.min = Math.min(cur.min, num);
        }
    }

    // 查询，如果当前位 bit
    public int getMaxXorWithLimit(int num, int limit) {
        Trie cur = this;
        if (cur.min > limit) return -1;
        int ans = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (cur.children[bit ^ 1] != null && cur.children[bit ^ 1].min <= limit) {
                ans |= 1 << k;
                bit ^= 1;
            }
            cur = cur.children[bit];
        }
        return ans;
    }
}
