package com;

public class WeekDayCalculator {
    public static void main(String[] args) {
        //System.out.println(findDay("Sat", 1));
        String maxStr=""; int count=0;
        countMax("deekew", maxStr, count );
        System.out.println("MaxStr: "+maxStr+", Count: "+count);
    }

    public static void countMax(String name, String maxStr, int count) {
        if(name.length()<=0){
            return;
        }
        char ch = name.charAt(0);
        if(maxStr.indexOf(ch+"")==-1){
            maxStr += ch;
        }else {
            if(count<=maxStr.length()){
                count = maxStr.length();
            }
            maxStr = "";
        }
        System.out.println("MaxStr: "+maxStr+", Count: "+count);
        countMax(name.substring(1, name.length()), maxStr, count);


    }

    static String findDay(String day, int next) {
        String days[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        next = next % 7;
        int pos = -1;
        for (int i = 0; i < days.length; ++i) {
            if (day.equals(days[i])) {
                pos = i;
                break;
            }
        }
        next += pos;
        next = (next > 7) ? next - 7 : next;
        return days[next];
    }


}
