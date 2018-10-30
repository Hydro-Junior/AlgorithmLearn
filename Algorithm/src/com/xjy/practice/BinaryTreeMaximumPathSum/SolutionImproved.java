package com.xjy.practice.BinaryTreeMaximumPathSum;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:15 2018/10/29
 * @Description: discuss中的优秀解法，核心思想与我的想法一致，走了折线就不能参与之后的和运算，但它避免了使用map保存值，而是直接在递归过程中更新值，
 * 并且返还给上一层的是未走折线的最大值
 */
public class SolutionImproved {
    int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
