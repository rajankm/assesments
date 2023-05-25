package generic;
public class Recursion {

    public static void main(String[] args) {
        printTable(5, 10);
    }

    /**
     * Print <code>num<code/> table with range 1 to maxCount
     * @param num
     * @param maxCount
     */
    public static void printTable(int num, int maxCount){
        if(maxCount==1){
            System.out.println(num*maxCount);
            return;
        }
        printTable(num, maxCount-1);
        System.out.println(num*maxCount);
    }
}

