/**
 * 
 */
package com.xjy.string.sort;

/**
 * @Description 
 *	低位优先的字符串排序，W轮键索引计数法（w:字符串长度），适合排序一系列定长字符串，时间复杂度为线性！
 *  为这种排序在脑海构建动图：
 *  1.统计频率（注意为前面的字符预留一位 比如count['1']是5，而count['0']是0，'0'其实是没出现过的，而'1'前面要排5个，
 *    因此写成count['1'+1]=5,代表'1'出现5次,这样'1'就能从0号位排起）
 *  2.频率转索引 count[r+1] += count[r]
 *  3.元素分类写入新数组 aux[count[a[i].charAt(d)]++] = a[i] //核心
 *  4.回写 a[] = aux[]
 * @author Mr.Xu
 * @date 2018年9月26日 上午9:20:55
 *
 */
public class LSD {
	private static int R = 256;
	/**
	 * @param a
	 * @param w 字符串长度
	 */
	public static void sort(String[] a, int w) {
		String[] aux = new String[a.length];
		
		for(int d = w-1; d >= 0 ; d--) {
			int[] count = new int[R+1];
			for(int i = 0 ; i < a.length; i++) {
				count[a[i].charAt(d) +1]++;//注意这里+1，预留一位
			}
			for(int i = 0 ; i < R; i++) {
				count[i+1] += count[i]; //频率 ->索引
			}
			for(int i = 0 ; i < a.length; i++) {
				aux[count[a[i].charAt(d)]++] = a[i];//核心：归位
			}
			
			for(int i = 0; i < a.length; i++) {
				a[i] = aux[i];//回写
			}
		}
	}
	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strs = new String[] 
		{"4PGC938","2IYE230","3CIO720","1ICK750","1OHV845","4JZY524","1ICK750","3CIO720",
				"1OHV845","1OHV845","2RLA629","2RLA629","3ATW723"};
		sort(strs,7);
		for(String s : strs) {
			System.out.print(s+" ");
		}
	}
}
