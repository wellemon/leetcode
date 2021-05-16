/**
 * 构建前缀数进行搜索异或结果
 */
public class Solution {
    Trie root = new Trie();
    final int HIGH_BIT = 30;

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            add(nums[i - 1]);
            ans = Math.max(ans, check(nums[i]));
        }
        return ans;
    }

    private void add(int num) {
        Trie cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) { // 左子树
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            } else { // 右子树
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }

    private int check(int num) {
        Trie cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) { // 当前位为 0
                if (cur.right != null) {
                    cur = cur.right;
                    x = (x << 1) + 1;
                } else  {
                    cur = cur.left;
                    x = x << 1;
                }
            } else { // 当前位为 1
                if (cur.left != null){
                    cur = cur.left;
                    x = (x << 1) + 1;
                } else {
                    cur = cur.right;
                    x = x << 1;
                }
            }
        }
        return x;
    }
}

class Trie {
    // 左子树表示当前位 0
    Trie left = null;
    // 右子树表示当前位　1
    Trie right = null;
}
