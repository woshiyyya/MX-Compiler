package XYXCompiler.XIR.Operand.Static;

import XYXCompiler.XIR.Operand.Memory.DataSrc;

public class StringLiteral extends Literal {
    public String value;

    public StringLiteral(String value) {
        this.value = value;
    }
}
