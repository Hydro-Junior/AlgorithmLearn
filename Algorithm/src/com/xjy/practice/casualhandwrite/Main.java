package com.xjy.practice.casualhandwrite;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Author: Mr.Xu
 * @Date: Created in 19:39 2019/3/30
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt(), k = sc.nextInt();
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int sum = 1;
        boolean[] marked = new boolean[m * n];
        Queue<Integer> queue = new LinkedList<>();
        marked[0] = true;
        queue.offer(0);
        while(!queue.isEmpty()){
            int pos = queue.poll();
            int x = pos / n , y = pos % n;
            for(int[] d : dirs){
                int nx = x + d[0];
                int ny = y + d[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && validPos(nx,ny,k) && !marked[nx*n+ny]){
                    queue.offer(nx * n + ny);
                    marked[nx*n+ny] = true;
                    sum ++;
                }
            }
        }
        System.out.println(sum);

    }
    public static boolean validPos(int a, int b, int k){
        int s = 0;
        while(a != 0){
            s += a % 10;
            a /= 10;
        }
        while(b != 0){
            s += b % 10;
            b /= 10;
        }
        return s <= k;
    }
}
