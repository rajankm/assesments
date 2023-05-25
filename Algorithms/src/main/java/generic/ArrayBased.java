package generic;

public class ArrayBased {
    public static void main(String[] args) {
        int arr[]={4,2,6,3,9,8,5};
        int result[]=getSumElements(arr,9);
        for (int ar:result){
            System.out.println(ar);
        }
    }
    public static int[] getSumElements(int arr[], int sum){
        int result[] = new int[arr.length];
        int tempSum=sum; int j=0,i=0;
        while(sum!=0){
            sum=sum-arr[i++];
            result[j++] = arr[i];

        }
        return result;
    }

}
