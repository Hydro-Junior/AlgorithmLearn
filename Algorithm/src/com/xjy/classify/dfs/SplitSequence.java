package com.xjy.classify.dfs;

/**
 * 数字序列分割：（关于这道题还有一些细节没有掌握，包含了DFS的精髓，需要反复看，而且基本判断DP行不通）
 * 
姐姐的实验报告上原本记录着从 1 到 n 的序列，任意两个数字间用空格间隔。
但是“坑姐”的蒜头居然把数字间的空格都给删掉了，整个数字序列变成一个长度为 1到 100的且首部没有空格的数字串。
现在姐姐已经怒了，蒜头找你写个程序快点把试验数据复原。

输入
输入文件有一行，为一个字符串——被蒜头搞乱的实验数据。
字符串的长度在 1 到 100 之间。

输出
输出共一行，为姐姐的原始测试数据 —— 1到 n的输出。
任意两个数据之间有一个空格。

如果有多组符合要求的正确解，输出其中任意一组即可。
 */
import java.util.*;
public class SplitSequence{
	static boolean finished = false;
	static int len,num;
	static char[] c ;
	static int[] vis; //标记该字符是否需要输出空格
	static int[] has; //标记某数字是否被输出过
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        len = s.length();
        c = s.toCharArray();
        num = (len - 9)/2 + 9; //找到序列的范围
        has = new int[200];
        has[0] = 1; //这个也很有必要(但是还是感觉没必要,我的猜测是有可能有两个0连续的错误序列)，避免单独输出0（C++原来答案没有这句代码，但是它方便在两位的情况时作判断，而Java会报数组越界错误）
        vis = new int[len];
        dfs(0);
    }
    public static void dfs(int i){
    	//如果已经有解，不用做直接返回，如果要输出所有解可注释此段
        if(finished){
            return;
        }
        if(i >= len -1){
            for(int k = 0 ; k < len; k++){
                System.out.print(c[k]);
                //从本质上来讲，dfs的目的是要获得每个字符的空格属性（最后一个除外）
                if(vis[k] == 1 && k != len - 1){System.out.print(" ");}
            }
            finished = true;
            return;
        }
        if(vis[i] == 0){
            int tmp = c[i] - '0';
            //必须判断下一个数字是不是0
            if(has[tmp] == 0 && tmp <= num && c[i+1]!='0'){
                vis[i] = 1; has[tmp] = 1;
                dfs(i + 1);
                //恢复标记是为了可以回退，这里必须这么做！
                vis[i] = 0 ;has[tmp] = 0;
            }
        }
        if(vis[i+1] == 0){
            int tmp = (c[i] -'0') * 10 + (c[i+1] - '0');
            //值得琢磨的是这里，判断c[i+2]是否为0的话就会数组越界（C++里不会），但是这里不判断也能通过，因为之前判断一次就可以了
            //如果上下都不判断会有案例通不过，只在上面作判断的话要加 has[0]=1
            if(has[tmp] == 0 && tmp <= num){
                vis[i+1] = 1; has[tmp] = 1;
                dfs(i + 2);
                //恢复标记是为了可以回退，这里必须这么做！
                vis[i+1] = 0; has[tmp] = 0;
            }
        }
    }
}
