#### [872. Leaf-Similar Trees](https://leetcode-cn.com/problems/leaf-similar-trees/)

**Tag:**   `Tree`  `Depth-first Search`

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a **leaf value sequence**.

![tree](../../../assets/img/q872-tree.png)

For example, in the given tree above, the leaf value sequence is `(6, 7, 4, 9, 8)`.

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return `true` if and only if the two given trees with head nodes `root1` and `root2` are leaf-similar.

**Example 1:**

![leaf-similar-1](../../../assets/img/q872-leaf-similar-1.jpg)

> **Input:** root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
>
> **Output:** true

**Example 2:**

> **Input:** root1 = [1], root2 = [1]
>
> **Output:** true

**Example 3:**

> **Input:** root1 = [1], root2 = [2]
>
> **Output:** false

**Example 4:**

> **Input:** root1 = [1,2], root2 = [2,2]
>
> **Output:** true

**Example 5:**

![leaf-similar-2](../../../assets/img/q872-leaf-similar-2.jpg)

> **Input:** root1 = [1,2,3], root2 = [1,3,2]
>
> **Output:** false

**Constraints:**

- The number of nodes in each tree will be in the range `[1, 200]`.
- Both of the given trees will have values in the range `[0, 200]`.




**Problem Source:** [力扣(LeetCode)](https://leetcode-cn.com/)