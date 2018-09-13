package com.xjy.problems.dfs;
/**
 * 
 * @author Mr.Xu
 * 找钥匙：输出是输入最小的一个排列，输入一个整数(-10^5~10^5),输出不以零开头,要分正负情况考虑
 */
class FindUnlockKey {
	private static int len;
	private static char[] originArr;
	private static char[] possibleArr;
	private static boolean[] book;
	private static long minValue = Long.MAX_VALUE;
	private static long maxValue = Long.MIN_VALUE;
	private static long currentValue = 0;
	private static boolean isPositive;
	
    public long findUnlockKey(long lockingKey) {
		
    	if(lockingKey > 0) {isPositive = true;}else {
			isPositive = false;
		}
		lockingKey = Math.abs(lockingKey);
		originArr = String.valueOf(lockingKey).toCharArray();
		len = originArr.length;
		possibleArr = new char[len];
		book = new boolean[len];
		dfs(0);
		if(isPositive) {
			return minValue;
		}else {
			return (0-maxValue);
		}
    	
    }
    public static void dfs(int i) {
    	if(i == len) {
    		if(isPositive) {
    			//if(possibleArr[0]=='0') {return;}
    			currentValue = Long.parseLong(String.valueOf(possibleArr,0,len));
    			if(currentValue < minValue) {
    				minValue = currentValue;
    			}
    		}else {
    			//if(possibleArr[0]=='0') {return;}
    			currentValue = Long.parseLong(String.valueOf(possibleArr,0,len));
    			if(currentValue > maxValue) {
    				maxValue = currentValue;
    			}
    		}
    		return;
    	}
    	for(int j = 0 ; j < originArr.length; j++) {
    		if(!book[j]) {
    			if(originArr[j] == '0' && i==0)continue;
    			book[j] = true;
    			possibleArr[i] = originArr[j];
    			dfs(i+1);
    			book[j] = false;
    		}
    	}
    }
}