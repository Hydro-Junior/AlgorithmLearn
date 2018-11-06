package com.xjy.practice.leetcode.BinaryTreeMaximumPathSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:07 2018/10/29
 * @Description:LeetCode124,但是这种方法只击败了4%的提交，效率较低
 */
class Solution {
    //value中的数组中包括两个值，第一，未走折线得到的包括自身的最大和，
    //第二，走了折线得到的包括自身的最大和（不能再和父节点相加）
    HashMap<TreeNode , int[]> resMap ;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        resMap = new HashMap<>();
        constructMap(root);
        int maxSum = Integer.MIN_VALUE;
        for(Map.Entry<TreeNode,int[]> entry : resMap.entrySet()){
            int[] val = entry.getValue();
            maxSum = max(maxSum, val[0],val[1]);
        }
        return maxSum;
    }
    private int[] constructMap(TreeNode root){
        if(root == null) return new int[]{0,0};
        if(resMap.containsKey(root)) return resMap.get(root);
        if(root.left == null && root.right == null) {
            resMap.put(root,new int[]{root.val,root.val});
        }else{
            int sum1 = constructMap(root.left)[0] + root.val;
            int sum2 = constructMap(root.right)[0] + root.val;
            int sum3 = constructMap(root.left)[0] + constructMap(root.right)[0] + root.val;
            int[] res = new int[2];
            res[0] = max(sum1, sum2, root.val); //未走折线的值，参与之后的和运算
            res[1] = sum3; //走了折线的值
            resMap.put(root,res);
        }
        return resMap.get(root);
    }
    private int max(int a, int b, int c){
        return Math.max(Math.max(a,b),c);
    }
}
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }
