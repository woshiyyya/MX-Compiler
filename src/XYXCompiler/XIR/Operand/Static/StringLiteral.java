package XYXCompiler.XIR.Operand.Static;

public class StringLiteral extends Literal {

    public String value;
    public int length;
    public String label;
    public static int cnt = 0;

    public StringLiteral(String value) {
        this.value = value;
        length = value.length();
        label = "_String_" + cnt++;
    }

    public String toInt(){
        StringBuffer str = new StringBuffer();
        int n = value.length();
        for (int j = 0; j < n; j++) {
            if (value.charAt(j) == '\\') {
                ++j;
                if (value.charAt(j) == 'n') {
                    str.append("  10,");
                }
                if (value.charAt(j) == '\"') {
                    str.append("  34,");
                }
                if (value.charAt(j) == '\\') {
                    str.append("  92,");
                }
            } else {
                str.append(String.format(" %3s,", (int) value.charAt(j)));
            }
        }
        str.append("   0\n");
        return str.toString();
    }
}
