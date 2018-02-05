package com.xjy.dfs;
import java.util.*;
/*
 * ���Բ�ʿ�����������ֱ��� A,B,C�����Թܣ�A,B,C�ֱ���������1 �� 20�������������A �� B �Թܶ��ǿյģ��� C �Թ���װ������ͭ
 * ��Һ�ġ���ʱ�����Բ�ʿ������ͭ��Һ��һ���Թܵ�����һ���Թ��У�ֱ�������Թ�װ����ԭ�Թܿ��ˡ���Ȼÿһ�ι�ע������ȫ�ġ�
 * �������Բ�ʿ������ô���ڣ�������������Һ�ڵ��Ĺ����в����ж�ʧ��

дһ������ȥ�������Բ�ʿ�ҳ��� AA �Թ��ǿյ�ʱ��CC �Թ�������ͭ��Һ��ʣ�������п����ԡ�
 */
public class PourWater {
	    public static int A , B , C;
	    public static int[][] res = new int[21][21];
	    public static void main(String[] args){
	        Scanner sc = new Scanner(System.in);
	        A = sc.nextInt();
	        B = sc.nextInt();
	        C = sc.nextInt();
	        dfs(0,0,C);
	        int flag = 1;
	        for(int i = B; i >= 0  ; i--){
	            if(res[0][i] == 1){
	                if(flag == 1){
	                    flag = 0;
	                }else{
	                    System.out.print(" ");
	                }
	                System.out.print(C-i);
	            }
	        }   
	        
	    }
	    public static void dfs(int a , int b , int c){
	        res[a][b] = 1;
	        if(a < A){
	            if(b < A-a && res[a+b][0] == 0){
	                dfs(a + b, 0, c);
	            }
	            if(b >= A-a && res[A][b-A+a] == 0){
	                dfs(A, b-A+a , c);
	            }
	            if(c < A - a && res[a+c][b] == 0){
	                dfs(a+c,b,0);
	            }
	            if(c >= A - a && res[A][b] == 0){
	                dfs(A,b,c-A+a);
	            }
	        }
	        if(b < B){
	            if(a < B-b && res[0][b+a] == 0){
	                dfs(0 , b+a ,c);
	            }
	            if(a >= B-b && res[a-B+b][B] == 0){
	                dfs(a-B+b , B ,c);
	            }
	            if(c > B-b && res[a][B] == 0){
	                dfs(a , B , c - B + b);
	            }
	            if(c <= B-b && res[a][b+c]==0){
	                dfs(a , b + c , 0);
	            }
	        }
	        if(c < C){
	            if(a < C-c && res[0][b] == 0){
	                dfs(0 , b , c+a);
	            }
	            if(a >= C-c && res[a-C+c][b] == 0){
	                dfs(a-C+c , b ,C);
	            }
	            if(b < C-c && res[a][0] == 0){
	                dfs(a , 0 , c+b);
	            }
	            if(b >= C-c && res[a][b-C+c]==0){
	                dfs(a , b-C+c ,C);
	            }
	        }
	    }
}

