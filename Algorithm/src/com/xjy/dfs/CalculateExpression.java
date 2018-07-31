package com.xjy.dfs;
/**
 * 请考虑一个被空格分隔的，由1到N的整数组成的递增数列：1 2 3 ... N。现在请在数列中插入表示加的“+”，或者表示减“-”，亦或者表示空白的“ ”(例如1-2 3就等于1-23)，来将每一对数字组合成一个表达式（第一个数字前无空格）。
计算该表达式的结果并判断其值是否为0。请你写一个程序找出所有产生和为零的长度为N的数列。

输入为一行，包含一个整数N（3≤N≤9）。

输出为所有在每对数字间插入“+”, “-”, 或 “ ”后能得到和为零的数列，并按照字典（ASCII码）序排列。

样例输入

7

样例输出

1+2-3+4-5-6+7
1+2-3-4+5+6-7
1-2 3+4+5+6+7
1-2 3-4 5+6 7
1-2+3+4-5+6-7
1-2-3-4-5+6+7
 */
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CalculateExpression{
    public static int n;
    public static char[] arrs;
    public static TreeSet<String> results = new TreeSet<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arrs = new char[2 * n - 1];
        for(int i = 0 ; i < n ; i ++){
            arrs[2*i] = (char) (i + 1 + 48);
        }
        dfs(1);
        for(String ss : results) {
        	System.out.println(ss);
        }
    }
    public static void dfs(int i){
        if(i == n){
           String tempRes = String.valueOf(arrs);
           if(calculate(tempRes) == 0){
        	   //如果结果符合要求，加到TreeSet中可以自动按ASCII码排序
               results.add(tempRes);     
           } 
           return;
        }
        //每个空的符号位有三种可能
        arrs[2 * i - 1] = '+';
        dfs(i+1);
        arrs[2 * i - 1] = '-';
        dfs(i+1);
        arrs[2 * i - 1] = ' ';
        dfs(i+1);
    }
    //计算一个字符串表达式的值（这里只包含加减）
    public static int calculate(String expr) {
    	
    	//建立堆栈记录每一步计算的结果
		Stack<Integer> stc = new Stack<Integer>();
		expr = expr.replaceAll(" ","");
		String[] nums = expr.split("(\\+|\\-)");
		String regex = "\\+|\\-";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(expr);
		for(int i = 0 ; i < nums.length ;i++) {
			if(stc.isEmpty()) {
				stc.push(Integer.parseInt(nums[i]));
			}else {
				int a = stc.pop();
				//寻找下一个符号
				matcher.find();
				if(matcher.group().equals("+")) {
					stc.push(a + Integer.parseInt(nums[i]));
				}else {
					stc.push(a - Integer.parseInt(nums[i]));
				}
			}
		}
		return stc.pop();
	}
}
