package com.xjy.practice.leetcode.ImplementTrie;

/**
 * @Author: Mr.Xu
 * @Date: Created in 9:28 2018/10/12
 * @Description:LeetCode208
 */
class Trie {
    Node root;
    private static class Node{
        Object  val; //The val does not need to be a string,and it is usually used to store the value
        //corresponding to the string.In this case,if the val is not null, then the corresponding string exist.
        Node[] next;
        public Node(){
            val = null;
            next = new Node[128];
        }
    }
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(root,word,0);
    }

    private void insert(Node n, String w, int d){
        if(w.length() == d) {
            n.val = new Object();
            return;
        }
        if(n.next[w.charAt(d)] == null){
            n.next[w.charAt(d)] = new Node();
        }
        insert(n.next[w.charAt(d)],w,d+1);
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(root,word,0);
    }

    private boolean search(Node n, String w, int d){
        if(n == null) return false;
        if(w.length() == d){
            if(n.val != null) return true;
            else return false;
        }else{
            return search(n.next[w.charAt(d)],w,d+1);
        }
    }
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startsWith(root,prefix,0);
    }
    private boolean startsWith(Node n, String w, int d){
        if(n == null) return false;
        if(w.length() == d) return true;
        return startsWith(n.next[w.charAt(d)],w,d+1);
    }
}

