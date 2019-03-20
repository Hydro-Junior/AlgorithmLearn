package com.xjy.practice.leetcode.TreeTravsal;

import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 17:19 2019/3/20
 * @Description:迭代前序遍历二叉树
 */
public class PreOrder {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null) {
                list.add(cur.val);
                if(cur.right != null) stack.push(cur.right);
                cur = cur.left;
            }
            if(!stack.isEmpty()) cur = stack.pop();
        }
        return list;
    }

}
