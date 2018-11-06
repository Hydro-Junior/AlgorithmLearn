package com.xjy.practice.leetcode.RemoveInvalidParentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:50 2018/10/30
 * @Description:LeetCode301
 * 以下参考discuss中的最高票解法，初步思路与其一致，但实在是想不出来！想到多解性就望而却步不知如何处理，而且同时考虑左右括号也限制了思路，以后这种
   对称性问题可以先只从一个方向考虑。
   对于序列 ()(()) ))... 常规的判断到第7个位置（下标从1开始）发现异常，此时可行的移除方案是：2或(5,6,7中的一个)，这样到第7个为止序列正常
   子序列正常后，它的子移除方案是总移除方案的子集，如此继续直到全部正常为止
   怎么继续是一个问题！此时我已经得到了一个可行的子序列，而后面的序列还未考虑。实际上移除了一个多余字符后，需要记住两个值：发现异常的位置i和移除的位置j
   这样，移除了这个字符之后，i,j作为新字符串的下标，表示的符号是原符号顺移1位。从而可以在移除字符后的基础上继续判断！
   当然，此时的“正常”只是右括号不多余，对于左括号多余的情况，只需要反转字符串序列用同样的手段判断即可！
 */
public class BetterSolution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(s, res, 0, 0, new char[]{'(',')'});
        return res;
    }
    private void remove(String s,List<String> res, int lastI, int lastJ, char[] pair){
        for(int i = lastI, counter = 0; i < s.length(); i++){
            if(s.charAt(i) == pair[0]) counter++;
            else if(s.charAt(i) == pair[1]) counter--;
            if(counter >= 0) continue;
            //出现多余“闭符号”
            //删除该闭符号（包括本身）之前所有闭符号中的一个<规定删除连续闭符号的头一个以避免重复>
            for(int j = lastJ; j <= i; j++){
                if(s.charAt(j) == pair[1] && (j == lastJ || s.charAt(j-1) != pair[1])) //j == lastJ 意味着异常位置就是移除位置的情况比如')('
                    remove(s.substring(0,j)+s.substring(j+1), res, i, j, pair);
            }
            return;
        }
        //合法的符号流可以到达这一步，将其反转作第二次处理
        String reversed = new StringBuilder(s).reverse().toString();//如果是第二次处理，将其反转为正常状态！
        if(pair[0] == '('){
            remove(reversed, res, 0, 0, new char[]{')','('});
        }else{//第二轮也处理完
            res.add(reversed);
        }
    }
}
