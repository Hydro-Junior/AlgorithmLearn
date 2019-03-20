package com.xjy.practice.other.pat.AcuteStroke.dfs;

/**
 * @Author: Mr.Xu
 * @Date: Created in 10:26 2018/11/9
 * @Description:
 */
//一开始以为slice不是连续的，想得贼复杂，看了讨论才知道是道3维数组的搜索题
//深搜可能超出堆栈范围(用C++在pat上也是部分段错误，但是在nowcoder上全部正确，哎，对java太不友好了)，试试广搜
import java.util.*;
import java.io.*;
public class Main{
    static int res;
    static int counter;
    static int[] dx = {0,0,-1,1,0,0};
    static int[] dy = {0,0,0,0,1,-1};
    static int[] dz = {1,-1,0,0,0,0};
    public static void main (String[] args){
        Reader.init(System.in);
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int L = Reader.nextInt();
        int T = Reader.nextInt();
        int[][][] G = new int[L][n][m];
        res = 0;
        for(int i = 0 ; i < L ; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0 ; k < m; k++){
                    G[i][j][k] = Reader.nextInt();
                }
            }
        }
        for(int i = 0; i < L ;i++){
            for(int j = 0 ; j < n; j++){
                for(int k = 0 ; k < m; k++){
                    counter = 0;
                    if(G[i][j][k] == 1) dfs(G, i, j, k);
                    if(counter >= T) res += counter;
                }
            }
        }
        System.out.print(res);
    }
    static void dfs(int[][][] G, int i, int j, int k){
        if(i < 0 || i >= G.length || j < 0 || j >= G[0].length
                || k < 0 || k >= G[0][0].length || G[i][j][k] == 0) return;
        G[i][j][k] = 0;
        counter++;
        for(int v = 0 ; v < 6; v++){
            dfs(G,i+dx[v],j+dy[v],k+dz[v]);
        }
    }
}
class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }
    static String next(){
        while(!tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch(Exception e){}
        }
        return tokenizer.nextToken();
    }
    static int nextInt(){
        return Integer.parseInt(next());
    }
}
