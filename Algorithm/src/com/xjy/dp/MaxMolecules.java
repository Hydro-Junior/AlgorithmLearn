package com.xjy.dp;

import org.junit.Test;

/**
 * 
 * @author Mr.Xu
 *����4������ʾ���ӵ�������Ȼ�����뻯���������
 *�û����������ɶ��ٷ�����ɣ����ط�����
 */
public class MaxMolecules {
	//1.�Զ����µݹ������ҵ��������ǹؼ�������������ѭ�������ܶ�
	public static int max(int a , int b) {
		return a >= b ? a:b; 
	}
	public static int findMaxMolecules(int massA , int massB , int massC, int massD , int massX) {
		if(massX == 0) {
			return 0;
		}
		if(massX < 0) {
			return -99999;
		}
		int[] a = new int[]{massA , massB , massC , massD};
		int q = 0;
		for(int i = 0 ; i < a.length ; i++) {
			q = max(q,1 + findMaxMolecules(massA , massB , massC, massD , massX - a[i]));
		}
		return q;		
	}
	//2.��̬�滮�����������Զ�����
	private static int[] r = new int[10000];
	static{
		for(int i = 0 ; i < r.length ; i++) {
		r[i] = -99999;
	}
	}
	public static int findMaxMolecules2(int massA , int massB , int massC, int massD , int massX) {
		int q = 0;
		if(r[massX] >= 0) {
			return r[massX];
		}
		if(massX == 0) {
			q = 0;
		}
		/*if(massX < 0) {
			q = -9999;
		}*/
		int[] a = new int[]{massA , massB , massC , massD};
		for(int i = 0 ; i < a.length ; i++) {
			q = max(q,1 + findMaxMolecules(massA , massB , massC, massD , massX - a[i]));
		}
		r[massX] = q;
		return q;		
	}
	//3.��̬�滮���Ե�����
	public static int findMaxMolecules3(int massA , int massB , int massC, int massD , int massX) {
		r[0] = 0;
		int q ;
		int[] a = new int[]{massA , massB , massC , massD};
		for(int j = 1 ; j <= massX ; j ++) {
			q = -99999;
			for(int i = 0 ; i < a.length && a[i] <= j ; i ++) {
				q = max(q , 1 + r[j - a[i]]);
			}
			r[j] = q;
		}
		for(int j = 1 ; j <= massX ; j ++) {
			System.out.println(r[j]); //������н����������ʾ�û�������������������ṩ�ļ��ַ������b
		}
		return r[massX];
	}
	@Test
	public void test() {
		int res = findMaxMolecules3(5, 8, 10, 6, 23);
		System.out.println(res);
	}                
}
