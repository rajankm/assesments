package streamapi;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondHighest {
    public static void main(String[] args) {
       // String name = "rajanmishra";
        //char cArr[] = name.toCharArray();
       // int ar[] = {2,4,1,7,9,2,3};
       // Map<Character, Integer> map = new HashMap<>();
      //  Arrays.asList(cArr).stream().flatMap(chars -> Stream.of(chars).forEach(ch-> System.out.println(ch)));
        List<Employee1> list = new ArrayList<>();
        list.add(new Employee1("abc",1200l));
        list.add(new Employee1("abc3",1000l));
        list.add(new Employee1("abc1",1300l));
        list.add(new Employee1("abc4",900l));
        list.add(new Employee1("abc4",700l));
        list.add(new Employee1("abc4",600l));
        list.add(new Employee1("abc4",500l));
        list.add(new Employee1("abc4",400l));
        list.add(new Employee1("abc4",300l));
        list.add(new Employee1("abc2",1100l));

        //Employee1 emp = list.stream().reduce((a,b)->a.getSalary()<b.getSalary()?a:b).get();
        //System.out.println(emp.getSalary());

        TreeSet<Employee1> set= new TreeSet<>(new Comparator<Employee1>() {
            @Override
            public int compare(Employee1 o1, Employee1 o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });
        Comparator<Employee1> comparator = (a,b)->{return b.getSalary().compareTo(a.getSalary());};
        //Set<Employee1> topEmp = list.stream().map((e)->e.getSalary()).collect(Collectors.toSet());
        Set<Employee1> topEmp = list.stream().sorted(comparator).limit(3).collect(Collectors.toSet());
        for(Employee1 employee1: topEmp){
            System.out.println(employee1.getSalary());
        }

    }
}

class Employee1 {
    private String name;
    private Long salary;
    public Employee1(String name, Long salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Long getSalary() {
        return salary;
    }
}
