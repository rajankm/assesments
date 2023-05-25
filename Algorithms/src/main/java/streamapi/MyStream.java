package streamapi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStream {
    public static void main(String[] args) {

    }
    public Map<Employee, Integer> sortByValueMap(Map<Employee, Integer> map){

        return map;
    }
    public static Collection<Employee> sortByValueList(Collection<Employee> list) {
        list.add(new Employee("abc", 2));
        list.add(new Employee("eas", 1));
        list.add(new Employee("bcd", 9));
        list = list.stream().sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.age - o2.age;
            }
        }).collect(Collectors.toList());
        return list;
    }
}
    class Employee{
        String name;
        int age;
        public Employee(String name, int age){
            this.name =name;
            this.age= age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

