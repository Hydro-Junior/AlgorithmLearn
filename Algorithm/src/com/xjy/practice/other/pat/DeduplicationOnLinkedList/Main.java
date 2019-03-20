package com.xjy.practice.other.pat.DeduplicationOnLinkedList;

/**
 * @Author: Mr.Xu
 * @Date: Created in 11:22 2018/11/12
 * @Description:https://www.nowcoder.com/pat/5/problem/4321
 * 大概方法，实现得不全面，有些用例可能出现空指针异常
 * 下面注释附上自己写并完善的C++代码，AC，感觉写起来还是挺爽的，判断空指针要注意（其实在处理链表时可以写得更简洁）
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
public class Main{
    public static void main(String[] args){
        Reader.init(System.in);
        Map<Integer,Node> addrMap = new HashMap<>();
        Set<Integer> values = new HashSet<>();
        int headAddr = Reader.nextInt();
        int n = Reader.nextInt();
        Node head = null;
        Node out = new Node(-3);
        //读入数据并构建address->node 的映射
        for(int i = 0; i < n; i++){
            int addr = Reader.nextInt();
            int val = Reader.nextInt();
            int nextAddr = Reader.nextInt();
            Node nNode = new Node(addr, val);
            nNode.nextAddress = nextAddr;
            if(addr == headAddr) head = nNode;
            addrMap.put(addr,nNode);
        }
        //构建链表
        Node cur = head;
        Node inCur = head;
        Node outCur = out;
        values.add(Math.abs(cur.val));
        do{
            Node nextOne = addrMap.get(cur.nextAddress);
            if(!values.contains(Math.abs(nextOne.val))){
                values.add(Math.abs(nextOne.val));
                inCur.next = nextOne;
                inCur = inCur.next;
                cur = inCur;
            }else{
                outCur.next = nextOne;
                outCur = outCur.next;
                cur = outCur;
            }
        }while(cur.nextAddress != -1);
        //打印两个链表
        while(head.next != null){
            System.out.println(String.format("%05d"+" "+head.val+" "+"%05d",head.address ,head.next.address));
            head = head.next;
        }
        System.out.println(String.format("%05d"+" "+head.val+" "+(-1),head.address));
        out = out.next;
        while(out.next != null){
            System.out.println(String.format("%05d"+" "+out.val+" "+"%05d",out.address ,out.next.address));
            out = out.next;
        }
        System.out.println(String.format("%05d"+" "+out.val+" "+(-1),out.address));
    }
}
class Node{
    int address;
    int val;
    int nextAddress = -1;
    Node next = null;
    public Node(int addr){this.address = addr;}
    public Node(int addr,int val){this.address = addr; this.val = val;}
}
class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }
    static String next(){
        while(!tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch(Exception e){}
        }
        return tokenizer.nextToken();
    }
    static int nextInt(){
        return Integer.parseInt(next());
    }
}
/*
#include <iostream>
#include <iostream>
#include <cstdio>
#include <cmath>
#include <unordered_map>
#include <unordered_set>
#include <climits>
using namespace std;
struct Node {
	int addr;
	int val;
	int next_addr;
	Node *next;
	Node(int _address, int _val) :addr(_address), val(_val), next_addr(-1), next(NULL) {}
};
int main()
{
	//读数据
	unordered_map<int, Node*> addr_map;
	unordered_set<int> vals;
	int head_addr;
	int n;
	scanf("%d%d", &head_addr, &n);
	Node *head = NULL;
	Node *out = new Node(INT_MIN, INT_MAX);//DUMMY
	//读入数据并构建address->node 的映射
	for (int i = 0; i < n; i++) {
		int address;
		int value;
		int next_address;
		scanf("%d%d%d", &address, &value, &next_address);
		Node *nNode = new Node(address, value);
		nNode->next_addr = next_address;
		if (address == head_addr)head = nNode;
		addr_map.insert({ address, nNode});
	}
	//构建链表
	Node *cur = head, *inCur = head, *outCur = out;
	vals.insert(abs(cur->val));
	do {
        if(!cur) break;
		Node *next = addr_map[cur->next_addr];
		if(!next) break;
		if (vals.find(abs(next->val)) == vals.end()) {//不存在
			vals.insert(abs(next->val));
			inCur->next = next;
			inCur = inCur->next;
			cur = inCur;
		}
		else {
			outCur->next = next;
			outCur = outCur->next;
			cur = outCur;
		}
	} while (cur->next_addr != -1);
	while (head && head->next) {
		printf("%05d %d %05d\n", head->addr, head->val, head->next->addr);
		head = head->next;
	}
	if(head) printf("%05d %d %d\n", head->addr, head->val, -1);
	out = out->next;
	while (out && out->next) {
		printf("%05d %d %05d\n", out->addr, out->val, out->next->addr);
		out = out->next;
	}
	if(out)printf("%05d %d %d\n", out->addr, out->val, -1);
	system("pause");
	return 0;
}
    }*/
