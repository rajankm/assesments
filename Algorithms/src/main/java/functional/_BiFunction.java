package functional;

import java.util.function.BiFunction;

public class _BiFunction {
    public static void main(String[] args) {
        int result = addNumbersBiFunctional.apply(1,2);
    }
   static  BiFunction<Integer, Integer, Integer> addNumbersBiFunctional = (a,b) ->{return a+b;};
    static BiFunction<Integer, Integer, Integer> multiplyNumbersBiFunctional = (a,b) ->{return a*b;};
}
