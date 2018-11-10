package com.xjy.practice.pat.CarsOnCampus;

/**
 * @Author: Mr.Xu
 * @Date: Created in 15:20 2018/11/7
 * @Description:
 * https://www.nowcoder.com/pat/5/problem/4319
 * 超时，关于此题尝试了一整天，最后尝试是用标答C一样的思路，然而由于语言性质，Java还是超时，在PAT官网上仅通过两个用例。
 */
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int[] clock = new int[24 * 60 * 60];
        HashMap<String,List<Time>> map = new HashMap<>();
        for(int i = 0 ; i < n; i++) {//读入记录
            String plate = Reader.next();
            String timeStr = Reader.next();
            String stateStr = Reader.next();
            Time theT = getTime(timeStr, stateStr);
            if (map.containsKey(plate)) {
                map.get(plate).add(theT);
            } else {
                map.put(plate, new ArrayList<>());
                map.get(plate).add(theT);
            }
        }
        Time[] queryT = new Time[m];
        for(int i = 0 ; i < m; i++){
            String query  = Reader.next();
            queryT[i] = getTime(query);
        }
        int maxRange = 0;
        List<String> plates = new ArrayList<>();
        for(Map.Entry<String,List<Time>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<Time> list = entry.getValue();
            Collections.sort(list);
            Time curTime = null;
            int range = 0;
            for (Time t : list) {
                int curTotal = t.getTotalSecs();
                if (t.state == 0) {
                    if(curTime == null ){
                        clock[curTotal] ++;
                        curTime = t;
                    }else {
                        clock[curTime.getTotalSecs()]--;
                        clock[curTotal] ++;
                        curTime = t;
                    }
                } else {
                    if (curTime != null) {
                        range += t.getTotalSecs() - curTime.getTotalSecs();
                        clock[curTotal]--;
                        curTime = null;
                    }
                }
            }
            if(range > maxRange){
                maxRange = range;
                plates.clear();
                plates.add(key);
            } else if(range == maxRange) {
                plates.add(key);
            }
        }
       /* for(int i = 1; i < clock.length; i++){
            clock[i] += clock[i-1];
        }*/
        int prevTarget = 0;
        for(int i = 0; i < queryT.length; i++){
            int target = queryT[i].getTotalSecs();
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
    public static Time getTime(String timeStr,String stateStr){
        int hour = Integer.parseInt(timeStr.substring(0,2));
        int min = Integer.parseInt(timeStr.substring(3,5));
        int sec = Integer.parseInt(timeStr.substring(6,8));
        int state = stateStr.equals("in")? 0 : 1;
        return new Time(hour,min,sec,state);
    }
    public static Time getTime(String timeStr){
        int hour = Integer.parseInt(timeStr.substring(0,2));
        int min = Integer.parseInt(timeStr.substring(3,5));
        int sec = Integer.parseInt(timeStr.substring(6,8));
        return new Time(hour,min,sec,0);
    }

}
class Time implements Comparable<Time>{
    int hour;
    int min;
    int sec;
    int state ;//0-进 1-出
    public Time(int hour, int min, int sec,int state){
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.state = state;
    }
    public int getTotalSecs(){
        return  3600 * hour + 60 * min + sec;
    }
    public int compareTo(Time o) {
        if(this.hour != o.hour) return this.hour - o.hour;
        else if(this.min != o.min) return this.min - o.min;
        else return this.sec - o.sec;
    }
}
class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }
    static String next() throws IOException{
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
