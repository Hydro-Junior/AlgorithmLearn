package com.xjy.dfs;

import java.util.Scanner;

/**
 * 八皇后问题dfs回溯法实现（每个位置有分数，求所有解中的最大和）
 * 回溯起到一定的优化效果
 * @author Mr.Xu
 * 样例输入：
 * 1
 1  2  3  4  5  6  7  8
 9 10 11 12 13 14 15 16
17 18 19 20 21 22 23 24
25 26 27 28 29 30 31 32
33 34 35 36 37 38 39 40
41 42 43 44 45 46 47 48
48 50 51 52 53 54 55 56
57 58 59 60 61 62 63 64
 *
 */
public class EightQueen_Recall {
	static int k ;
    static int[][] score = new int[9][9];
    static int[] row;//存放每行皇后所在的列
    static boolean[] column,diag,diag_B;//被占的列、正对角、反对角
    static int maxSum;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        for(int m=0 ; m < k ; m++){
            //每次操作一个棋盘，先初始化
            for(int i = 1 ; i <= 8 ; i++){
                for(int j = 1 ; j <= 8 ; j++){
                    score[i][j] = sc.nextInt();
                }
            }
            row = new int[9];
            column = new boolean[9];
            diag = new boolean[20];
            diag_B = new boolean[20];
            maxSum = 0;//每次的总分
            dfs(1);//从第一行开始
            System.out.println(maxSum);
        }
    }
   public static void dfs(int r) {
	   if(r > 8) {
		   int sum = 0;
           for(int k = 1 ; k <= 8 ; k++){
               sum += score[k][row[k]];
           }
           if(sum > maxSum){maxSum = sum;}
           return;
	   }
	   for(int i = 1 ; i <= 8 ; i++) {
		   if(row[r]== 0 && !column[i] && !diag[r + i]&& !diag_B[r-i+8]) {
			   row[r] = i;
			   column[i] = diag[r+i] = diag_B[r-i+8] = true;
			   dfs(r+1);
			   row[r]= 0;
			   column[i]= diag[r+i] = diag_B[r-i+8] = false;
		   }
	   }
   }
}
