package com.xjy.practice.SerializeAndDeserializeBinaryTree;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:30 2018/10/22
 * @Description: LeetCode297 按前序遍历更为快捷方便
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //简单思路，层序遍历解析树
        if(root == null) return null;
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode n = queue.poll();
            if(n == null) sb.append(",null");
            else{
                sb.append(","+n.val);
                queue.offer(n.left);
                queue.offer(n.right);
            }
        }
        return sb.toString().substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.equals("")) return null;
        String[] ss = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        Queue<TreeNode> fathers = new LinkedList<>();
        fathers.offer(root);
        int i = 1;
        while(!fathers.isEmpty() && i < ss.length){
            TreeNode n = fathers.poll();
            if(n == null) continue;
            if(i < ss.length) {
                if (!ss[i].equals("null")){
                    n.left = new TreeNode(Integer.parseInt(ss[i]));
                }else{
                    n.left = null;
                }
                fathers.offer(n.left);
                i++;
            }
            if(i < ss.length){
                if(!ss[i].equals("null")){
                    n.right = new TreeNode(Integer.parseInt(ss[i]));
                }else{
                    n.right = null;
                }
                fathers.offer(n.right);
                i++;
            }
        }
        return root;
    }
}
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
