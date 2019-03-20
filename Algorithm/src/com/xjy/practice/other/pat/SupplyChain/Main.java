package com.xjy.practice.other.pat.SupplyChain;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:19 2018/11/6
 * @Description:
 * https://www.nowcoder.com/pat/5/problem/4316
 */
import java.util.*;
import java.io.*;
public class Main{
    private static int maxLen;
    private static int maxNum;
    public static void main(String[] args) throws IOException{
        maxLen = 0;
        maxNum = 0;
        Reader.init(System.in);
        int len = Reader.nextInt();
        double ogPrice = Reader.nextDouble();
        double incrm = Reader.nextDouble();
        double rate = incrm /100 + 1;
        int[] father = new int[len];
        int[] dis = new int[len];
        Arrays.fill(dis , -1);
        for(int i = 0 ; i < len; i++){
            father[i] = Reader.nextInt();
            if(father[i] == -1){
                dis[i] = 0;
            }
        }
        for(int i = 0 ; i < len; i++){
            dis[i] = getDist(i, father, dis);
        }
        while(maxLen-- > 0){
            ogPrice *= rate;
        }
        System.out.print(String.format("%.2f"+" "+ maxNum, ogPrice));
    }
    public static int getDist(int i, int[] father, int[] dis){
        if(dis[i] != -1) return dis[i];
        if(father[i] == -1){
            dis[i] = 1;
        }else {
            dis[i] = getDist(father[i], father, dis) + 1;
        }
        if(dis[i] > maxLen) {
            maxLen = dis[i];
            maxNum = 1;
        }else if(dis[i] == maxLen){
            maxNum += 1;
        }
        return dis[i];
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