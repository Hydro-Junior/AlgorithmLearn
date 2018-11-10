package com.xjy.practice.pat;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author: Mr.Xu
 * @Date: Created in 8:58 2018/11/6
 * @Description:
 */
public class TestReader {

    public static void main(String[] args) throws IOException{
        LocalReader.init(System.in);
        String plate = LocalReader.next();
        String time = LocalReader.next();
        int hour = Integer.parseInt(time.substring(0,2));
        int minite = Integer.parseInt(time.substring(3,5));
        int second = Integer.parseInt(time.substring(6,8));
        String status = LocalReader.next();
        System.out.println("【"+plate +"】"+ hour + ":" + minite + ":" + second +" "+status);
    }
}
class LocalReader {
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
