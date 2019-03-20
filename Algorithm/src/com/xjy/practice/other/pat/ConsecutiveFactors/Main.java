package com.xjy.practice.other.pat.ConsecutiveFactors;

/**
 * @Author: Mr.Xu
 * @Date: Created in 19:28 2018/11/10
 * @Description:找一个数的最长连续因子，nowcoder上AC，但官网上有一个通不过，扣了1分
 */
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //找出所有因子
        List<Integer> factors = new ArrayList<>();
        for(int i = 2 ; i * i <= n ;i ++){
            if(n % i == 0){
                factors.add(i);
                if(n / i != i) factors.add(n/i);
            }
        }
        //对每个因子找出最短序列和最长序列数
        int end = factors.size()==0? n : factors.get(0), len = 1;
        for(int i = 0 ; i < factors.size(); ){
            int m = factors.get(i);
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
            i += tmpN;//跳过已判断过的数
        }
        System.out.println(len);
        for(int i = 1 ; i <= len; i++){
            if(i==1)System.out.print(end - len + i);
            else System.out.print("*" + (end - len + i));
        }
    }
}
/*
更高效的方法
#include<iostream>
#include<cstdio>
#include<algorithm>
#include<cmath>
#include<cstring>
using namespace std;
typedef long long LL;
int main() {
    LL n;
    scanf("%lld", &n);
    LL sqr = (LL)sqrt(1.0 * n), first = 0, maxLen = 0;
    for(LL i = 2; i <= sqr; i++) {
        LL temp = 1, j = i;
        while(1) {
            temp *= j;
            if(n % temp != 0) {
                 break;
            }
            if(j - i + 1 > maxLen) {
                first = i;
                maxLen = j - i + 1;
            }
            j++;
        }
    }
        if(maxLen == 0) {
            printf("1\n%lld", n);
        } else {
                printf("%lld\n", maxLen);
                for(LL i = 0; i < maxLen; i++) {
                printf("%lld", first + i);
                if(i < maxLen - 1) {
                    printf("*");
                }
            }
        }
        return 0;
       }*/
