package generic;
public class RemoveElement {

    public static void main(String[] args) {
        int [] ar = {1,3,1,4,5,6};
        int [] ar1 = new int[ar.length];
        int pos=0;
        for(int i=0; i<ar.length; ++i){
            boolean flag =false;
            for(int j=0; j < ar1.length; ++j){
                if(ar[i]==ar1[j]){
                    flag = true;
                }
            }
            if(!flag){
                ar1[pos++] = ar[i];
            }
        }
        for(int i=0; i<pos ; ++i){
            System.out.print(ar1[i]);

        }
    }
}
