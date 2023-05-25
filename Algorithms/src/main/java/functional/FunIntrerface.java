package functional;

public class FunIntrerface {
    public void foo(MyInterface obj){
        System.out.println(obj.add(1,2));
    }
    public static void main(String[] args) {
        new FunIntrerface().foo((a, b)->{return a+b;});
        MyInterface1 a = (i)->i;
    }
}

@FunctionalInterface
interface MyInterface{
    public int add(int a, int b);

}
@FunctionalInterface
interface MyInterface1{
    public int add(int a);

}
