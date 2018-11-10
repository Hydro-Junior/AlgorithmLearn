package com.xjy.practice.pat.ConsecutiveFactors;

/**
 * @Author: Mr.Xu
 * @Date: Created in 19:28 2018/11/10
 * @Description:找一个数的最长连续因子，nowcoder上AC，但官网上有一个通不过，扣了1分
 */
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);//小规模输入可以用scanner
        int n = sc.nextInt();
        //找出所有因子
        List<Integer> factors = new ArrayList<>();
        for(int i = 2 ; i * i <= n ;i ++){
            if(n % i == 0){
                factors.add(i);
                if(n / i != i) factors.add(n/i);
            }
        }
        //对每个因子做深搜，找出最短序列和最长序列数
        int end = factors.size()==0? n : factors.get(0), len = 1;
        for(int m : factors){
            int tmp = n/m;
            int tmpN = 1;
            while(tmp % (m+1) == 0){
                tmpN ++;
                m += 1;
                tmp /= m;
            }
            if(tmpN > len) {
                len = tmpN;
                end = m;
            }
        }
        System.out.println(len);
        for(int i = 1 ; i <= len; i++){
            if(i==1)System.out.print(end - len + i);
            else System.out.print("*" + (end - len + i));
        }
    }
}
