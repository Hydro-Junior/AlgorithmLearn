package com.xjy.practice.leetcode.TreeTravsal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Mr.Xu
 * @Date: Created in 16:59 2019/3/20
 * @Description:迭代式中序遍历
 */
public class InOrder {
    public static List<Integer>  iterativelyInOrder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode>  stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }
}
