import jdk.nashorn.internal.runtime.ECMAException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;

/**
 * Created by yzwang on 2017/11/20.
 */
public class LyricAnalyzer {

    private HashMap<String, ArrayList<Integer>> map;


    public LyricAnalyzer(){
        
        map = new HashMap<String, ArrayList<Integer>>();
    }

    public void read(File file){

        try {
            FileReader reader = new FileReader(file);
            Scanner sc = new Scanner(reader);
            
            int wordCount = 0;
            while (sc.hasNext()){
                String line = sc.nextLine();
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    wordCount ++;
                    if(i == words.length - 1)
                        add(words[i],0 - wordCount);
                    else
                        add(words[i],wordCount);
                }
            }
            
        }catch (Exception e){

            System.out.println("Exception in file process");
        }


    }

    private void add(String old, int wordPosition){

        String lyricWord = old.toUpperCase();

        if(map.containsKey(lyricWord)){
            ArrayList list = map.get(lyricWord);
            if(list == null){
                list = new ArrayList<Integer>();
                list.add(wordPosition);
                map.put(lyricWord,list);
            }else {
                list.add(wordPosition);
            }
        }else {
            ArrayList list = new ArrayList<Integer>();
            list.add(wordPosition);
            map.put(lyricWord,list);
        }

    }

    public void displayWords(){


        TreeSet<String> set = new TreeSet<String>();
        for (String key : map.keySet()) {
//            System.out.println("Key = " + key);
            set.add(key);
        }

        for(String str: set){
            System.out.print(str+": ");
            List list = map.get(str);
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
                if(i < list.size() -1)
                    System.out.print(",");
            }
            System.out.println();
        }

    }

    public void writeLyrics(File file){

        String[] words = new String[count()+1];
        for (int i = 0; i < words.length; i++) {
            words[i] = "";
        }

        for(String str : map.keySet()){

            List list = map.get(str);
            for (int i = 0; i < list.size(); i++) {
                if((Integer)list.get(i) > 0)
                    words[(Integer) list.get(i)] = str;
                else
                    words[0- (Integer) list.get(i)] = str +"\n";
            }
        }
        System.out.println("string[] words: \n");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i]);
        }

        //wirte to the file
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            for (int i = 1; i < words.length; i++) {
                out.write(words[i].getBytes());
            }
            out.close();
        }catch (Exception e){
            System.out.println("error in write file...");
        }finally {

        }

    }

    public int count(){

        int result = 0;
        for(String str : map.keySet()) {

            List list = map.get(str);
            result += list.size();

        }
        return result;
    }

    public String mostFrequentWord(){

        List<MYObject> result = new ArrayList<MYObject>();

        int max = Integer.MIN_VALUE;
        for(String str : map.keySet()) {

            List list = map.get(str);
            if(list.size() > max){
                max = list.size();
                result.add(new MYObject(str,max));
            }
        }
        if(result.size()>1 && result.get(result.size()-1) == result.get(result.size()-2)){
            if(result.get(result.size()-1).value.compareTo(result.get(result.size()-2).value) >= 0)
                return result.get(result.size()-2).value;
            else
                return result.get(result.size()-1).value;

        }else
            return result.get(result.size() - 1).value;

    }

    private class MYObject{

        public String value;
        public int count;

        public MYObject(String a,int b){
            value = a;
            count = b;
        }
    }

    public static void main(String[] args) {

        LyricAnalyzer ly = new LyricAnalyzer();
        File file = new File("/Users/yzwang/IdeaProjects/wyzimplementfunction/src/main/java/test2_3.txt");
        ly.read(file);

        ly.displayWords();
        System.out.println();
        System.out.println();
        System.out.println("count: "+ly.count());
        System.out.println("mostFrequentWord: "+ly.mostFrequentWord());

        ly.writeLyrics(new File("/Users/yzwang/IdeaProjects/wyzimplementfunction/src/main/java/result.txt"));


    }
}
