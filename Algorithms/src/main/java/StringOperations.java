public class StringOperations {
    public static void main(String[] args) {
        String str = "peekew";
        String maxSubStr="";
        int maxStrLength = 0;
        getMaxSubStr(str,maxSubStr, maxStrLength);
        System.out.println("maxSubStr: "+maxSubStr+", maxStrLength: "+maxStrLength);
    }
    public static void getMaxSubStr(String str, String maxSubStr, int maxStrLength){
        if(str.length()<=0)return;
        String temp="";
        char firstChar = str.charAt(0);
        if(maxSubStr.indexOf(String.valueOf(firstChar))==-1){
            maxSubStr+=firstChar;
        }else{
            temp = maxSubStr;
            maxSubStr="";
        }



        if(maxStrLength<=(maxSubStr.length()>temp.length()?maxSubStr.length():temp.length())){
            maxStrLength =  temp.length();
        }
        System.out.println("maxSubStr: "+maxSubStr+", maxStrLength: "+maxStrLength);
        getMaxSubStr(str.substring(1), maxSubStr, maxStrLength);
    }
}
