package XYXCompiler.XIR.Operand.Static;

import XYXCompiler.XIR.Operand.DataSrc;

public class StaticData extends DataSrc {
    public String name;
    public int size;

    public StaticData(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
