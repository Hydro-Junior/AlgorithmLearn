package com.xjy.string.compress;


import org.junit.Test;
import java.util.PriorityQueue;

/**
 * @Author: Mr.Xu
 * @Date: Created in 17:05 2018/10/22
 * @Description:哈夫曼树实现字符串压缩和解压缩
 */
public class Huffman {
    private static final int R = 128;
    private static Node root;
    /**
     * @param freq 频率表，以字母为下标
     * @return 建立的哈夫曼树的根节点
     */
    private static Node buildTrie(int[] freq){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(char c = 0; c < R; c++){
            if(freq[c] > 0){
                pq.offer(new Node(c,freq[c],null,null));
            }
        }
        while (pq.size() > 1){
            Node x = pq.poll();
            Node y = pq.poll();
            Node parent = new Node('\0',x.freq + y.freq , x, y);
            pq.offer(parent);
        }
        return  root = pq.poll();
    }

    /**
     * 构造Huffman树构建编译表
     * @param root
     * @return 每个字符对应的bit流，用字符串存储
     */
    private static String[] buildCode(Node root){
        String[] st = new String[R];
        buildCode(st, root ,"");
        return st;
    }
    private static void buildCode(String[] st, Node x,String s){
        if(x.isLeaf()){st[x.ch] = s; return;}//如果是叶子节点，说明有字符
        buildCode(st, x.left ,s + '0');
        buildCode(st, x.right,s + '1');
    }
    private static class Node implements Comparable<Node>{
        int freq;
        char ch;
        Node left;
        Node right;
        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq ;
        }
        public boolean isLeaf(){return left == null && right == null;}
        public Node(char ch, int freq, Node left, Node right){
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 压缩
     * @param input
     * @return 压缩后比特流的字符串表示
     */
    public static String compress(String input){
        char[] arr = input.toCharArray();
        //统计频率
        int[] freq = new int[R];
        for(int i = 0; i < arr.length ;i ++){
            freq[arr[i]] ++;
        }
        //构造哈夫曼树
        Node root = buildTrie(freq);
        //递归地构建编译表
        String[] st = buildCode(root);
        //打印输入字符总数
        System.out.println("输入字符总数： " + arr.length);
        //使用Haffman树处理
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < arr.length ; i++){
            sb.append(st[arr[i]]);
        }
        System.out.println("处理后的bit流： ");
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * 解压缩
     * @param bits 比特流的字符串表示
     * @return
     */
    public static String expand(String bits){
        System.out.println("开始解码：");
        if(root == null) {
            System.out.println("尚未构建哈夫曼树!");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < bits.length() ;){
            Node x = root;
            while (!x.isLeaf()){
                if(bits.charAt(i++) == '0'){x = x.left;}
                else x = x.right;
            }
            sb.append(x.ch);
        }
        System.out.println("解码比特流后得到字符串： " + sb);
        return sb.toString();
    }
    @Test
    public void test(){
        Huffman.expand(Huffman.compress("ABRACADABRA!"));
    }


}

