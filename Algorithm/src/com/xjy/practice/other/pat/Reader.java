package com.xjy.practice.other.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author: Mr.Xu
 * @Date: Created in 8:48 2018/11/6
 * @Description: faster input for Java
 */
public class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    public static void init(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }
    public static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()){
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }
    public static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    public static double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
}
