package generic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Subsets {
    public static void main(String[] args) {
        int arr[] = {1,2,3};
        System.out.println(sunset(arr));
    }
    public static List<List<Integer>> sunset(int[] arr){
        List<List<Integer>> result = new ArrayList<>();
        int arrLength = arr.length;
        int subsetCount =((int)Math.pow(2, arrLength));
        for(int i=0; i<subsetCount ;++i){
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<arrLength; ++j){
                if(((i>>j)&1)==1){
                    list.add(arr[j]);
                }
            }
            result.add(list);
        }

        return result;
    }
    public static int maxConsucativeSum(int[] arr){
        int result= Integer.MIN_VALUE;



        return result;
    }
}
