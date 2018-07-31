package com.xjy.basicStructure;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

//初版
public class StackApp_CalculateExpression {
	/**
	 * 第一种：将中缀转化为后缀,再计算后缀表达式 （这里只考虑单位数加减）
	 */
	public static int calculate(String infix) {
		// 中缀转后缀
		MyStack<Character> ms = new MyStack<Character>();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < infix.length(); i++) {
			char tc = infix.charAt(i);
			if (tc <= 57 && tc >= 48) {
				res.append(tc);
			} else if (tc == '+' || tc == '-') {
				if (ms.isEmpty()) {
					ms.push(tc);
				} else {
					res.append(ms.pop());
					ms.push(tc);
				}
			} else {
				System.out.println("wrong input!");
				return -1;
			}
		}
		while (!ms.isEmpty()) {
			res.append(ms.pop());
		}
		String postfix = res.toString();// 后缀表达式
		MyStack<Integer> intStack = new MyStack<>();
		for (int i = 0; i < postfix.length(); i++) {
			char tc = postfix.charAt(i);
			if (tc <= 57 && tc >= 48) {
				intStack.push(tc - 48);
			} else if (tc == '+' || tc == '-') {
				int a = intStack.pop();
				int b = intStack.pop();
				if (tc == '+') {
					intStack.push(a + b);
				} else {
					intStack.push(b - a);
				}
			}
		}
		return intStack.pop();
	}
/**
 * 输入表达式计算的升级版
 * (差错控制和嵌套括号内的负数运算还有所欠缺)
 * @param expr
 */
	public static void calculatePro(String expr) {
		MyStack<String> ops = new MyStack<String>();
		MyStack<Double> vals = new MyStack<Double>();
		expr = expr.replaceAll("\\s+", "");
		String regex = "(\\+|\\-|\\)|\\(|/|\\*|(sqrt)|√|\\^)|(\\d+\\.{0,1}\\d*)";
		String[] container = new String[expr.length()];
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(expr);
		int len = 0;
		while (m.find()) {
			container[len++] = m.group();
		}
		String[] postfix = new String[len];
		int j = 0;
		//先转为后缀表达式
		for (int i = 0; i < len; i++) {
			if(container[i].equals("(")) {
				ops.push(container[i]);
				try {
					if(container[i+1].equals("-")||container[i+1].equals("+")) {
						postfix[j++] = "0.0";
					}
				}catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Wrong expression!");
				}
				continue;
			}if(container[i].matches("\\d+\\.{0,1}\\d*")) {
				postfix[j++] = container[i];
			}else {
				if(ops.isEmpty()) {
					ops.push(container[i]);
				}else {
					if(container[i].equals(")")) {
						while(ops.top()!=null && !ops.top().equals("(")) {
							postfix[j] = ops.pop();
							j++;
						}
						if(ops.top().equals("(")) {
							ops.pop();
						}
					}else {
						while(ops.top()!= null && shouldPop(ops.top(),container[i])) {
							postfix[j] = ops.pop();
							j++;
						}
						ops.push(container[i]);
					}
					
				}
			}
		}
		while(!ops.isEmpty()) {
			postfix[j] = ops.pop();
			j++;
		}
		//输出后缀表达式
		System.out.println("the postfix form :");
		for(int k = 0 ; k < j ; k++) {
			System.out.print(postfix[k]+" ");
		}
		//计算后缀表达式
		double tmp1 = 0;
		double tmp2 = 0;
		for(int k = 0 ; k < j; k++) {
			if(postfix[k].matches("\\d+\\.{0,1}\\d*")){
				vals.push(Double.parseDouble(postfix[k]));
			}else {
				if(postfix[k].equals("√")||postfix[k].equals("sqrt")) {
					vals.push(Math.sqrt(vals.pop()));
				}else if(postfix[k].equals("^")) {
					tmp1 = vals.pop();
					tmp2 = vals.pop();
					vals.push(Math.pow(tmp2, tmp1));
				}else if(postfix[k].equals("*")) {
					vals.push(vals.pop() * vals.pop());
				}else if(postfix[k].equals("/")) {
					tmp1 = vals.pop();
					tmp2 = vals.pop();
					vals.push(tmp2/tmp1);
				}else if(postfix[k].equals("+")) {
					tmp1 = vals.pop();
					if(vals.isEmpty()) {
						vals.push(tmp1);
					}else {
						tmp2 = vals.pop();
						vals.push(tmp1 + tmp2);
					}
				}else if(postfix[k].equals("-")) {
					tmp1 = vals.pop();
					if(vals.isEmpty()) {
						vals.push(0-tmp1);
					}else {
						tmp2 = vals.pop();
						vals.push(tmp2-tmp1);
					}
				}else {
					System.out.println("wrong input!");
				}
			}
		}
		System.out.println("\nthe result:\n" + vals.pop());

	}

	//通过比较运算符优先级判断是否要pop，这里为简单起见只返回true和false，简单化处理同级的情况（因为只有优先级低的保留在栈中，高的或相同的都pop出去）
	public static boolean shouldPop(String bef, String aft) {
		if (bef.equals("(")) {
			return false;
		} else if (bef.equals("+") || bef.equals("-")) {
			if(aft.equals("+") || aft.equals("-")) {
				return true;
			}else {
				return false;
			}
		} else if (bef.matches("(sqrt)|√|\\^")) {
			if(aft.equals("(")){
				return false;
			}else {
				return true;
			}
		} else if(bef.equals("*") || bef.equals("/")) {
			if(aft.matches("\\(|/|\\*|(sqrt)|√|\\^")) {
				return false;
			}else {
				return true;
			}
		}
		return false;
	}

	@Test
	public void testChange() {
		Scanner sc = new Scanner(System.in);
		int result = calculate(sc.next());
		System.out.println(result);
	}

	@Test
	public void regexTest() {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String regex1 = "\\+|\\-|\\)|\\(|/|\\*|(sqrt)";
		String regex2 = "\\d+\\.{0,1}\\d*";
		Pattern p1 = Pattern.compile(regex1);
		Pattern p2 = Pattern.compile(regex2);
		Matcher m = p1.matcher(s);
		while (m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
		Matcher m2 = p2.matcher(s);
		while (m2.find()) {
			System.out.print(m2.group() + " ");
		}
	}

	@Test
	public void testCalPro() {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		calculatePro(s);
	}
	
}
