package com.xjy.practice.RemoveInvalidParentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:14 2018/10/30
 * @Description: LeetCode301
 */
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        //先统计，限制深搜的返回条件(这也是剪枝的一种技术)
        int mL = 0, mR = 0; //多余的左括号，多余的右括号，在深搜过程中一旦小于零就可以返回
        for(int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(') mL++;
            else if(c == ')'){
                if(mL != 0) mL--;
                else mR++;
            }
        }
        int open = 0; //开放的括号个数（在深搜过程中要保证大于等于零，一旦小于零就返回）
        HashSet<String> res = new HashSet<>(); //在深搜过程中去重第保存结果
        dfs(s, 0, res, new StringBuilder(), mL, mR, open);
        return new ArrayList<String>(res);
    }
    private void dfs(String s, int i, HashSet<String> set, StringBuilder sb, int mL, int mR, int open){
        if(mL < 0 || mR < 0 || open < 0) return;
        if(i == s.length()){
            if(open == 0 && mL == 0 && mR == 0)
                set.add(sb.toString());
            return;
        }
        char c = s.charAt(i);
        int len = sb.length();
        if(c == '('){
            //先短后长，顺序不宜调换
            dfs(s, i+1, set, sb, mL-1, mR, open);//no use(delete)
            dfs(s, i+1, set, sb.append(c), mL, mR, open+1);//use
        }else if(c == ')'){
            dfs(s, i+1, set, sb, mL, mR-1, open);
            dfs(s, i+1, set, sb.append(c), mL, mR, open-1);
        }else{
            dfs(s, i+1, set, sb.append(c), mL, mR, open);
        }
        sb.setLength(len);//这句话很关键,因为操作的是同一个StringBuilder对象
    }
}

