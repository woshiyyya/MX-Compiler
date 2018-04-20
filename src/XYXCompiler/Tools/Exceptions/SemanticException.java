package XYXCompiler.Tools.Exceptions;

import java.util.ArrayList;
import java.util.List;

public class SemanticException {

    public static List<XYXException> exceptions = new ArrayList<>();

    public static void printExceptions(int i){
        for(Exception X: exceptions)
            System.err.println(i + X.getMessage());
    }

    public void add(XYXException e){
        exceptions.add(e);
    }
}
