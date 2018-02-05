package com.xjy.dfs;
/**
 * �뿼��һ�����ո�ָ��ģ���1��N��������ɵĵ������У�1 2 3 ... N���������������в����ʾ�ӵġ�+�������߱�ʾ����-��������߱�ʾ�հ׵ġ� ��(����1-2 3�͵���1-23)������ÿһ��������ϳ�һ�����ʽ����һ������ǰ�޿ո񣩡�
����ñ��ʽ�Ľ�����ж���ֵ�Ƿ�Ϊ0������дһ�������ҳ����в�����Ϊ��ĳ���ΪN�����С�

����Ϊһ�У�����һ������N��3��N��9����

���Ϊ������ÿ�����ּ���롰+��, ��-��, �� �� �����ܵõ���Ϊ������У��������ֵ䣨ASCII�룩�����С�

��������

7

�������

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
        	   //����������Ҫ�󣬼ӵ�TreeSet�п����Զ���ASCII������
               results.add(tempRes);     
           } 
           return;
        }
        //ÿ���յķ���λ�����ֿ���
        arrs[2 * i - 1] = '+';
        dfs(i+1);
        arrs[2 * i - 1] = '-';
        dfs(i+1);
        arrs[2 * i - 1] = ' ';
        dfs(i+1);
    }
    //����һ���ַ������ʽ��ֵ������ֻ�����Ӽ���
    public static int calculate(String expr) {
    	
    	//������ջ��¼ÿһ������Ľ��
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
				//Ѱ����һ������
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
