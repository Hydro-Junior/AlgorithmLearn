package com.xjy.practice.SerializeAndDeserializeBinaryTree;

import org.junit.Test;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:33 2018/10/22
 * @Description:
 */
public class Tester {
    @Test
    public void testCodec(){
        Codec codec = new Codec();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left =new TreeNode(3);
        root.right.left.right = new TreeNode(1);
        codec.deserialize(codec.serialize(root));
    }
}
