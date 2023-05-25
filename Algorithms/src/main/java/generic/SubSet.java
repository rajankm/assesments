package generic;

import java.util.ArrayList;
import java.util.List;

public class SubSet {

    public static void findSubset(List<Integer> list, List<List<Integer>> resultSet, List<Integer> temp, int index ){
        if(index==list.size()){
            resultSet.add(temp);
            return;
        }
        findSubset(list, resultSet, new ArrayList<>(temp), index+1);
        temp.add(list.get(index));
        findSubset(list, resultSet, new ArrayList<>(temp), index+1);

    }
}
