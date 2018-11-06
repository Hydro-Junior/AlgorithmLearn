package com.xjy.practice.pat;

import org.junit.Test;

import java.io.IOException;

/**
 * @Author: Mr.Xu
 * @Date: Created in 8:58 2018/11/6
 * @Description:
 */
public class Tester {
    @Test
    public void testReader(){

    }
    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        int v = Reader.nextInt();
        int e = Reader.nextInt();
        String start = Reader.next();
        System.out.println("起点："+start);
    }
}
