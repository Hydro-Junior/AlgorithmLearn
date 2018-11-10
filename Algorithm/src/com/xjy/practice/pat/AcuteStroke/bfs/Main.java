package com.xjy.practice.pat.AcuteStroke.bfs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @Author: Mr.Xu
 * @Date: Created in 14:12 2018/11/9
 * @Description:https://www.nowcoder.com/pat/5/problem/4317
 * 用bfs在nowcoder上全部通过，在PAT上仍有两个极端案例通不过（但同样代码用C可以）
 */
public class Main {
    static int res ;
    static int num ;
    static int[] dx = {0,0,-1,1,0,0};
    static int[] dy = {0,0,0,0,1,-1};
    static int[] dz = {1,-1,0,0,0,0};
    public static void main(String[] args) {
        Reader.init(System.in);
        int m = Reader.nextInt();
        int n = Reader.nextInt();
        int l = Reader.nextInt();
        int T = Reader.nextInt();
        int[][][] G = new int[l][m][n];
        for(int k = 0; k < l; k++){
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    G[k][i][j] = Reader.nextInt();
                }
            }
        }
        res = 0;
        for(int k = 0 ; k < l; k++){
            for(int i = 0 ; i < m ;i ++){
                for(int j = 0 ; j < n; j++){
                    num = 0;
                    if(G[k][i][j] == 1){
                        bfs(G,k,i,j,m,n,l,T);
                        if(num >= T) res += num;
                    }
                }
            }
        }
        System.out.println(res);
    }
    public static void bfs(int[][][] G, int k, int i, int j,int m, int n , int l, int T){
        Queue<Triple> q = new LinkedList<>();
        q.offer(new Triple(i, j , k));
        G[k][i][j] = 0;
        while(!q.isEmpty()){
            Triple t = q.poll();
            num++;
            for(int v = 0 ; v < 6; v++){
                int nx = t.x + dx[v];
                int ny = t.y + dy[v];
                int nz = t.z + dz[v];
                if(nx >= 0 && ny >= 0 && nz >=0 && nx < m && ny < n && nz < l && G[nz][nx][ny] == 1){
                    q.offer(new Triple(nx,ny,nz));
                    G[nz][nx][ny] = 0;
                }
            }
        }
    }
}
class Triple{
    int x;
    int y;
    int z;
    public Triple(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
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