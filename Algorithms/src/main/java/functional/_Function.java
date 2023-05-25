package functional;

import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        //
        System.out.println(incrementByOne(1));
        System.out.println(incrementByOneFunction.apply(1));
        int val = incrementByOneFunction.andThen(multiplyByFiveFunction).apply(1);
        System.out.println(val);
    }
    private static Function<Integer, Integer> incrementByOneFunction = num->num+1;
    private static Function<Integer, Integer> multiplyByFiveFunction = num->num*5;
    public static int incrementByOne(int num){
        return num+1;
    }
}
