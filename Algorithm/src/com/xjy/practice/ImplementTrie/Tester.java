package com.xjy.practice.ImplementTrie;

import org.junit.Before;
import org.junit.Test;

/**
 * @Author: Mr.Xu
 * @Date: Created in 14:51 2018/10/11
 * @Description:
 */
public class Tester {
    Trie trie = null;
    @Before
    public void setup(){
        trie = new Trie();
    }
    @Test
    public void testSolution(){
        trie.insert("apple");
        trie.search("apple");
        trie.search("app");
        trie.startsWith("app");
        trie.insert("app");
        trie.search("app");

    }
}
