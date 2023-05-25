import java.util.*;

/**
 *
 *
 */
class Emp{
    int id;
    String name;
    Emp(int id, String name){
        this.id =id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



}
public class DemoClass {
    public static void main(String[] args) {



    }























    public static int gemstones(List<String> arr) {
        // Write your code here
        Map<Character, Integer> map = new HashMap<>();
        arr.stream().flatMap(str -> str.chars().mapToObj(ch -> (char) ch)).forEach(ch -> map.put(ch, map.getOrDefault(ch, 0) + 1));
        System.out.println(map);
        return 0;
    }
}

