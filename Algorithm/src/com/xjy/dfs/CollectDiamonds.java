package com.xjy.dfs;

/**
 * ����ɼ���������˱�������֮���ƺ��и��õؽⷨ���������ֻͨ���˲���������
 * ��Ѷ����ֵ����ܶ࣬�к��ꡢ���ꡢ����ȡ���Ʒ������������׼��Ϊ�²�Ʒ�Ƴ�һ�֡����ꡱ�����Ͽ����в�ͬ��������
 * ������ N �������ꡱ���� i �������ꡱ������Ϊ Si���۸��� Wi���õ�����Ҫ������ʱ��Ϊ Ci���������� M ��Ǯ��
 * ���ڸ���Ǯ���ķ�Χ�ڣ�������Ҫ�೤ʱ�䣬���Եõ� 7 ��������ͬ�ġ����ꡱ��

�����ʽ

��һ������һ������ T��1 �� T �� 20������ʾ����������

ÿ�����ݵĵ�һ�������������� N��1 �� N �� 100���� M��1 �� M �� 109����

�������� N �У�ÿ������������ Si, Wi, Ci��1 �� Si, Wi, Ci �� 109����
3
8 8
1 1 2
1 2 1
2 1 1
3 1 1
4 1 1
5 1 1
6 1 1
7 1 1
10 12
1 1 2
2 2 2
2 3 1
3 1 2
4 2 1
4 2 2
5 1 1
6 1 1
7 2 2
7 3 1
4 5
1 1 1
2 1 2
3 1 1
4 1 1

�����ʽ

���һ��������ʾ������Ҫ����ʱ�䣬����޽������ -1��
 */
import java.util.*;
public class CollectDiamonds{
    static int[] minT; //��¼ÿ��ĿǰΪֹ���ŵĽ�
    static int[] tempSumT; //��¼��ǰ��������ʱ��
    static int[] count; //��¼��ǰ�ɼ���ʯ�ĸ���
    static int[] m;   //ÿ����ͷ��Ǯ
    static int[] n;   //ÿ��������ܸ���
    static int[] fee; //��¼��ǰ��������
    static boolean[] solved; //������������Ƿ��н�
    static int[][] S; //����
    static int[][] W; //�۸�
    static int[][] C; //ʱ�����
    static int[][] had; //����Ƿ������������
    static int[][] book;//��������Ƿ���ѡ����
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int gs = sc.nextInt();//��������
        minT = new int[gs];
        for(int k = 0 ; k < gs; k++){
            minT[k] = (int) 1e11;
        }
        S = new int[gs][];
        W = new int[gs][];
        C = new int[gs][];
        had = new int[gs][100];
        book = new int[gs][];
        n = new int[gs];
        m = new int[gs];
        fee = new int[gs];
        tempSumT = new int[gs];
        count = new int[gs];
        solved = new boolean[gs];
        for(int k = 0 ; k < gs ; k++){
            n[k] = sc.nextInt();
            m[k] = sc.nextInt();
            S[k] = new int[n[k]];
            W[k] = new int[n[k]];
            C[k] = new int[n[k]];
            book[k] = new int[n[k]];
            for(int i = 0 ; i < n[k]; i++){
                S[k][i] = sc.nextInt();
                W[k][i] = sc.nextInt();
                C[k][i] = sc.nextInt();
            }
        }
        for(int k = 0 ;  k < gs ; k++){
            dfs(k,0);
            if(!solved[k]){
                System.out.println(-1);
            }else{
                System.out.println(minT[k]);
            }
        }
    }
    public static void dfs(int k , int step){
        if(step <= n[k] && count[k] == 7){
            if(fee[k] > m[k]){
                return;
            }else{
               if(tempSumT[k] < minT[k]){
                   minT[k] = tempSumT[k];
               }
               solved[k] = true;
               return;
               
            }
        }
        if(step == n[k] && count[k] < 7){
            return;
        }
        for(int j = 0 ; j < n[k]; j++){
        	//˼�������·����൱�ڱ��������������had��book�ܷ�����һ��������˵��ε���(��֦)�õ����Ż��ķ�����
            if(had[k][S[k][j]] == 0 && book[k][j] == 0){
                count[k] ++;
                fee[k] += W[k][j];
                tempSumT[k] += C[k][j];
                book[k][j] = 1;
                had[k][S[k][j]] = 1;
                dfs(k,step + 1);
                book[k][j] = 0;
                had[k][S[k][j]] = 0;
                count[k] --;
                fee[k] -= W[k][j];
                tempSumT[k] -= C[k][j];
            }
        }
        
    }
}
