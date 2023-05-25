package generic;
public class Cycle{
    public static void main(String[] args ){
        int ar[] = {1,2,3,4,5,6};
        int[][] result = printCycle(ar, 4,5);
        for(int i=0; i<result.length; ++i){
            for(int j=0; j<result[i].length; ++j){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }
    public static int[][] printCycle(int ar[], int group, int itr){
        int[][] result = new int [itr][group];
        int k=0;
        for(int i=0; i<itr; ++i){
            for(int j=0; j<group; ++j){
                if(k>=ar.length)
                   k=0;

                result[i][j] = ar[k++];
            }
        }
        return result;

    }

}
