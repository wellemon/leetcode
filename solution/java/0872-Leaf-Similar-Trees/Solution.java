/**
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
 *
 * 用深度优先搜索寻找叶子节点
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<>();
        if (root1 != null) {
            dfs(root1, seq1);
        }

        List<Integer> seq2 = new ArrayList<>();
        if ( root2 != null) {
            dfs(root2, seq2);
        }

        return seq1.equals(seq2);
    }

    /**
     * 深度优先搜索
     * 按先左后右的顺序寻找叶子节点
     *
     * @param node  待检查的节点
     * @param seq   用于保存节点值的列表
     */
    private void dfs(TreeNode node, List<Integer> seq) {
        if (node.left == null && node.right == null) {
            seq.add(node.val);
            return;
        }
        if (node.left != null) dfs(node.left, seq);
        if (node.right != null) dfs(node.right, seq);
    }
}