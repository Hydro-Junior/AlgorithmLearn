package com.xjy.practice.pat.CarsOnCampus.lastTry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 9:14 2018/11/8
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int[] clock = new int[24 * 60 * 60];
        HashMap<String, Integer> map = new LinkedHashMap<>();
        List<Record> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {//读入记录
            String plate = Reader.next();
            String timeStr = Reader.next();
            String stateStr = Reader.next();
            int hour = Integer.parseInt(timeStr.substring(0, 2));
            int min = Integer.parseInt(timeStr.substring(3, 5));
            int sec = Integer.parseInt(timeStr.substring(6, 8));
            int state = stateStr.equals("in") ? 0 : 1;
            Record rec = new Record(hour, min, sec, state, plate);
            list.add(rec);
        }
        int maxRange = 0;
        Collections.sort(list);
        System.out.println(list);
        for(int i = 0 ; i < n ; i++){
            if(i+1 < n ){
                Record in = list.get(i);
                Record out = list.get(i+1);
                if(in.match(out)){
                    map.put(in.plate, map.getOrDefault(in.plate,0)+out.time - in.time);
                    if(map.get(in.plate) > maxRange){
                        maxRange = map.get(in.plate);
                    }
                    clock[in.time]++;clock[out.time]--;
                }
            }
        }
        for(int i = 1; i < clock.length; i++){
            clock[i] += clock[i-1];
        }
        for(int i = 0 ; i < m; i++){
            String timeStr = Reader.next();
            int hour = Integer.parseInt(timeStr.substring(0, 2));
            int min = Integer.parseInt(timeStr.substring(3, 5));
            int sec = Integer.parseInt(timeStr.substring(6, 8));
            System.out.println(clock[hour*3600+min*60+sec]);
        }
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue() == maxRange) System.out.print(entry.getKey() + " ");
        }
        System.out.println(secsToStr(maxRange));
    }

    public static String secsToStr(int total) {
        int hour = total / 3600;
        int min = total / 60 - hour * 60;
        int sec = total - hour * 3600 - min * 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}

class Record implements Comparable<Record> {
    int time;
    int state;//0-进 1-出
    String plate;

    public Record(int hour, int min, int sec, int state, String plate) {
        time = (hour * 60 + min) * 60 + sec;
        this.state = state;
        this.plate = plate;
    }
    public boolean match(Record other){
        return this.plate.equals(other.plate) && time < other.time && state == 0 && other.state == 1;
    }
    public int compareTo(Record o) {
        if(!plate.equals(o.plate)){
            return plate.compareTo(o.plate);
        }else if(this.time != o.time){
            return this.time - o.time;
        }else{
            return this.state - o.state;
        }
    }

    @Override
    public String toString() {
        return "Record{" +
                "time=" + time +
                ", state=" + state +
                ", plate='" + plate + '\'' +
                '}';
    }
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (NullPointerException e) {//个别平台出现空指针异常
            }
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}