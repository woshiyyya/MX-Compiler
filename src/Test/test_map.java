package Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class test_map {
    public static void main(String [] args){
        animal A = new animal();
        A.map.put("A",1);
        animal B = new animal(A);
        B.map.put("B",2);
        if(A.map.containsKey("B"))
            System.out.println("Contain");
    }

}
