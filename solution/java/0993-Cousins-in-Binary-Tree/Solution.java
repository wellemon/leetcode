/**
 * 思路：
 * 寻找值为 x 和 y 的 TreeNode
 * 比较它们的深度和父亲节点
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root.val == x || root.val == y) return false;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        boolean foundOne = false; // foundOne 表示匹配了一个节点
        boolean foundX = false, foundY = false;

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 按层搜索：使用堂兄弟 depth 相同的特性
            int size = queue.size(); // size 用来确定当前 depth 深度搜索的大小
            // 每次判断节点的子节点，利用子节点拥有相同的 parent 的特性
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    if (node.left.val == x) {
                        foundX = true;
                    } else if (node.left.val == y) {
                        foundY = true;
                    }
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    if (node.right.val == x) {
                        foundX = true;
                    } else if (node.right.val == y) {
                        foundY = true;
                    }
                }
                // x 和 y 都找到；终止搜索
                if (foundX && foundY) {
                    if (foundOne) { // 之前就找到一个，现在找到另一个，不是同一个父节点
                        return true;
                    } else { // 之前一个没找到，现在一下找到两个，说明他们是同一个父亲
                        return false;
                    }
                }
                // 更新 foundOne
                foundOne = foundX | foundY;
            }
            // 本层搜索完毕，只找到一个值，说明另一个值不在同 depth
            if (foundOne) return false;
        }
        return false;
    }
}