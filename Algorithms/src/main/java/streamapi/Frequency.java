package streamapi;

import java.util.*;
import java.util.stream.Collectors;

public class Frequency {
    public static void main(String[] args) {
        String name= "I like Java I like Coding like";
        //sortByValueFrequency(name);
        Map<String, Integer> map = new HashMap<>();
       /* System.out.println(map.put("A",1)+" ");
        System.out.println(map.put("B",2)+" ");
        System.out.println(map.put("A",3));*/
        charFrequency(name);
    }

    public static void charFrequency(String name){
        Map<Character, Integer> map = new HashMap<>();
        /*char arr[]  =name.toCharArray();
        for(char ch: arr){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }*/
        name.chars().forEach((ch)->map.put((char)ch, map.getOrDefault((char)ch, 0)+1));
        System.out.println(map);
        Set<Map.Entry<Character, Integer>> set = new TreeSet<>((o1, o2)->o1.getValue()-o2.getValue());
        Set<Map.Entry<Character, Integer>> entrySet= map.entrySet();
        for(Map.Entry<Character, Integer> entry: entrySet){
            set.add(entry);
        }
        for(Map.Entry<Character, Integer> key: set){
            System.out.println(key.getKey()+"-"+map.get(key.getKey()));
        }
    }
    public static void sortByValueFrequency(String str){
        String [] ar = str.split(" ");
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(ar).forEach((s)->map.put(s,map.getOrDefault(s,0)+1));
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
    }
}
