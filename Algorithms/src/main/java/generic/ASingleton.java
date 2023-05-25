package generic;
import java.util.*;
/**
 * Threadsafe singleton class.
 * extra result increases performance by 25%.
 */
public class ASingleton {
    private static volatile ASingleton instance;
    private static Object mutex = new Object();
    private ASingleton(){
    }
    public static ASingleton getInstance(){
        ASingleton result = instance;
        if(result==null){
            synchronized (mutex){
                result = instance;
                if(result==null){
                    instance = result = new ASingleton();
                }
            }
        }

        return result;
    }

}

