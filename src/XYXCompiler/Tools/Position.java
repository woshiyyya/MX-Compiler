package XYXCompiler.Tools;

public class Position {
    public int row;
    public int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public String toString(){
        return "(Line: " + row + ", Col: " + col + ") ";
    }
}
