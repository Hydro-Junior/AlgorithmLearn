package com.xjy.practice.pat.TheLargestGeneration;

/**
 * @Author: Mr.Xu
 * @Date: Created in 9:51 2018/11/7
 * @Description:
 */
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        int n = Reader.nextInt();
        Node[] member = new Node[n+1];
        Node root = null;
        int m = Reader.nextInt();
        for(int i = 0 ; i < m ; i++){
            int f = Reader.nextInt();
            int kidNum = Reader.nextInt();
            if(member[f] == null) member[f] = new Node();
            if(f == 1) root = member[f];
            for(int j = 0 ; j < kidNum ; j++){
                int kidID = Reader.nextInt();
                if(member[kidID]== null) member[kidID] = new Node();
                member[f].kids.add(member[kidID]);
            }
        }
        Queue<Node> q = new LinkedList<>();
        root.gen = 1;
        int[] genNum = new int[n+1];
        int maxGen = 1;
        int maxGenNum = 1;
        q.offer(root);
        while(!q.isEmpty()){
            Node ft = q.poll();
            genNum[ft.gen]++;
            if(genNum[ft.gen] > maxGenNum){
                maxGenNum = genNum[ft.gen];
                maxGen = ft.gen;
            }
            if(ft.kids.size() == 0) continue;
            for(Node child : ft.kids){
                child.gen = ft.gen + 1;
                q.offer(child);
            }
        }
        System.out.print(maxGenNum + " " + maxGen);
    }
}
class Node{
    int gen = 0;
    List<Node> kids;
    Node(){
        kids = new ArrayList<>();
    }
}
class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }
    static String next() throws IOException{
        while(!tokenizer.hasMoreTokens()){
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    static double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
}