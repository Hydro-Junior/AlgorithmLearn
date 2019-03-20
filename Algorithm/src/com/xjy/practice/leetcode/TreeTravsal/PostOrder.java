package com.xjy.practice.leetcode.TreeTravsal;

import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 20:13 2019/3/20
 * @Description:
 */
public class PostOrder {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return ans;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.addFirst(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return ans;
    }
}
