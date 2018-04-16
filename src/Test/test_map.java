package Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class test_map {
    public static void main(String [] args){
        Map<String, animal> map = new LinkedHashMap<>();
        animal A = new animal(1);
        animal B = new animal(2);
        map.put("A", A);
        map.put("B", B);
        A.A = 5;
        System.out.println(map.get("A").A);
    }

}
