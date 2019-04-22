package com.xjy.practice.other.pat;

/**
 * @Author: Mr.Xu
 * @Date: Created in 20:57 2019/4/2
 * @Description:从叶子节点到另一个叶子节点的最大乘积
 */
import java.util.*;
public class TreeProduct
{
    static int maxV;
    static boolean[] marked;
    public int maxProduct(int numNodes, List<Integer> values,
                          List<List<Integer>> edges)
    {
        maxV = Integer.MIN_VALUE;
        Node[] nodes = new Node[numNodes+1];
        marked = new boolean[numNodes+1];
        for(int i = 1; i <= numNodes; i++){
            int a = values.get(i-1);
            nodes[i] = new Node(i,a);
        }
        for(List<Integer> pair : edges){
            int v = pair.get(0);
            int w = pair.get(1);
            nodes[v].adj.add(w);
            nodes[w].adj.add(v);
        }
        for(int i = 1; i <= numNodes; i++){
            if(nodes[i].isLeaf()){
                marked[i] = true;
                getProduct(nodes, nodes[i].val , i, i);
                marked[i] = false;
            }
        }
        return maxV;

    }
    public static void getProduct(Node[] nds, int curPro, int start, int cur){
        if(nds[cur].isLeaf()&& cur != start){ //到达另一个叶子节点
            maxV = Math.max(maxV,curPro);
            return;
        }
        for(int v : nds[cur].adj){
            if(!marked[v]){
                marked[v] = true;
                getProduct(nds,curPro * nds[v].val,start,v);
                marked[v] = false; //取消标记，回溯
            }
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        TreeProduct tp = new TreeProduct();
        Integer[] a= new Integer[]{2,5,8,1,3,10,4};
        List<Integer> res = Arrays.asList(a);
        List<List<Integer>> pairs = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        p.add(1); p.add(2);
        pairs.add(p);
        ArrayList<Integer> p2 = new ArrayList<>();
        p2.add(1); p2.add(3);
        pairs.add(p2);
        ArrayList<Integer> p3 = new ArrayList<>();
        p3.add(2); p3.add(4);
        pairs.add(p3);
        ArrayList<Integer> p4 = new ArrayList<>();
        p4.add(2); p4.add(5);
        pairs.add(p4);
        ArrayList<Integer> p5 = new ArrayList<>();
        p5.add(3); p5.add(6);
        pairs.add(p5);
        ArrayList<Integer> p6 = new ArrayList<>();
        p6.add(3); p6.add(7);
        pairs.add(p6);
        int r = tp.maxProduct(7,res,pairs);
        System.out.println(r);
    }
}

class Node{
    int id;
    int val;
    ArrayList<Integer> adj = new ArrayList<>();
    Node(int id, int v){
        this.id = id;
        this.val = v;
    }
    boolean isLeaf(){
        for(int i : adj){
            if(i > id){ //如果邻接顶点有比它大的下标，就不是叶子节点（不小心把id写成val）
                return false;
            }
        }
        return true;
    }
}
