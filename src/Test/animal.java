package Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class animal {
    public int A;
    public Map<String, Integer> map;
    public animal(){
        map = new LinkedHashMap<>();
    }

    public animal(animal other){
        map = other.map;
    }

    public animal(int a) {
        A = a;
    }
}
