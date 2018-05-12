package XYXCompiler.XIR.Operand.Static;

import XYXCompiler.XIR.Operand.Memory.DataSrc;
import javafx.scene.chart.PieChart;

public class StaticData extends DataSrc {
    public String name;
    public int size;

    public StaticData(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
