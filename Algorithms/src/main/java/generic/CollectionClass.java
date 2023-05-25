package generic;

import java.util.*;

public class CollectionClass{
    public static void main(String[] args){
        Map<Integer, Employee> map = new HashMap<>();
        map.put(12734, new Employee(1, "a"));
        map.put(6345, new Employee(6, "f"));
        map.put(2324, new Employee(2, "b"));
        map.put(45, new Employee(5, "e"));
        map.put(1234, new Employee(3, "c"));
        map.put(156, new Employee(4, "d"));
        Set<Map.Entry<Integer, Employee>> treeSet = new TreeSet<>(new Comparator<Map.Entry<Integer, Employee>>() {

            @Override
            public int compare(Map.Entry<Integer, Employee> o1, Map.Entry<Integer, Employee> o2) {
                return o1.getValue().getName().compareTo(o2.getValue().getName());
            }
        });

        Comparator<Map.Entry<Integer, Employee>> nameComparator = new Comparator<Map.Entry<Integer, Employee>>(){

            @Override
            public int compare(Map.Entry<Integer, Employee> o1, Map.Entry<Integer, Employee> o2) {
                return o1.getValue().getName().compareTo(o2.getValue().getName());
            }
        };

        //Set<Map.Entry<Integer, Employee>> set = map.entrySet().stream().sorted(nameComparator).collect(Collectors.toSet());
        Set<Map.Entry<Integer, Employee>> set = map.entrySet();
        for(Map.Entry<Integer, Employee> entry: set){
           treeSet.add(entry);
        }
        for(Map.Entry<Integer, Employee> entry: treeSet){
            System.out.println(entry.getKey()+", "+entry.getValue());
        }

    }
}

class Employee{
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

