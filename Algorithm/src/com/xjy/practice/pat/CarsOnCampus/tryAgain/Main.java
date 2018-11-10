package com.xjy.practice.pat.CarsOnCampus.tryAgain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Author: Mr.Xu
 * @Date: Created in 21:29 2018/11/7
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int[] clock = new int[24 * 60 * 60];
        HashMap<String,Prop> map = new HashMap<>();
        List<Record> list = new ArrayList<>();
        Record[] queryR = new Record[m];
        for(int i = 0 ; i < n; i++) {//读入记录
            String plate = Reader.next();
            String timeStr = Reader.next();
            String stateStr = Reader.next();
            int hour = Integer.parseInt(timeStr.substring(0,2));
            int min = Integer.parseInt(timeStr.substring(3,5));
            int sec = Integer.parseInt(timeStr.substring(6,8));
            int state = stateStr.equals("in")? 0 : 1;
            Record rec = new Record(hour, min, sec, state, plate);
            list.add(rec);
        }
        for(int i = 0 ; i < m; i++){
            String queryT = Reader.next();
            int hour = Integer.parseInt(queryT.substring(0,2));
            int min = Integer.parseInt(queryT.substring(3,5));
            int sec = Integer.parseInt(queryT.substring(6,8));
            queryR[i] = new Record(hour, min, sec, 0 ,null);
        }
        int maxRange = 0;
        Collections.sort(list);
        for(Record rec : list){
            String p = rec.plate;
            if(!map.containsKey(p)){
                map.put(p, new Prop());
            }
            Prop curP = map.get(p);
            int state = rec.state;
            int totalS = rec.time;
            if(state == 0){
                if(curP.curTime == -1){
                    curP.curTime = totalS;
                    clock[totalS]++;
                }else{
                    clock[curP.curTime]--;
                    clock[totalS]++;
                    curP.curTime = totalS;
                }
            }else if(curP.curTime != -1){
                curP.range += totalS - curP.curTime;
                clock[totalS]--;
                curP.curTime = -1;
            }
            if(curP.range > maxRange) maxRange = curP.range;
        }

        List<String> plates = new ArrayList<>();
        for(Map.Entry<String,Prop> entry : map.entrySet()){
            Prop p =  entry.getValue();
            if(p.range == maxRange) plates.add(entry.getKey());
        }
        int prevTarget = 0;
        for(int i = 0; i < queryR.length; i++){
            int target = queryR[i].time;
            for(int j = prevTarget + 1; j <= target; j++){
                clock[j] += clock[j-1];
            }
            System.out.println(clock[target]);
            prevTarget = target;
        }
        Collections.sort(plates);
        for(String s : plates){
            System.out.print(s + " ");
        }
        System.out.print(secsToStr(maxRange));
    }
    public static String secsToStr(int total){
        int hour = total / 3600;
        int min = total / 60 - hour * 60;
        int sec = total - hour * 3600 - min * 60;
        return String.format("%02d:%02d:%02d" ,hour,min,sec);
    }
}

class Prop{
    int curTime = -1;
    int range;
}
class Record implements Comparable<Record>{
    int time;
    int state ;//0-进 1-出
    String plate;
    public Record(int hour, int min, int sec,int state,String plate){
        time = 3600 * hour + 60 * min + sec;
        this.state = state;
        this.plate = plate;
    }
    public int compareTo(Record o) {
        return this.time - o.time;
    }
}
class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }
    static String next() throws IOException {
        while(!tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (NullPointerException e){//个别平台出现空指针异常
            }
        }
        return tokenizer.nextToken();
    }
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
}