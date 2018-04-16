package Test;

import XYXCompiler.ASTNode.ASTRoot;
import XYXCompiler.Parser.XYXLexer;
import XYXCompiler.Parser.XYXParser;
import XYXCompiler.Tools.ASTViewer;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import XYXCompiler.Builder.*;

import javax.swing.text.View;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Test_Listener {
    public static void main(String[] args) throws Exception {
//        String inputFile = "/Users/bluesnap/Documents/Project/IntelliJ Idea Project/Mx/src/compiler/testfile/compile_error/incop-1-5120309049-liaochao.mx";
       //String inputFile = "/Users/bluesnap/Documents/Project/IntelliJ Idea Project/Mx/src/compiler/testfile/test.mx";
        String inputFile = "C:\\Users\\lenovo\\Desktop\\MX-Compiler\\XYXCompiler\\src\\Test\\testcase\\test6.mx";
        CharStream stream = fromFileName(inputFile);
        XYXLexer lexer              = new XYXLexer(stream);
        CommonTokenStream tokens    = new CommonTokenStream(lexer);
        XYXParser parser            = new XYXParser(tokens);
        ParseTree tree = parser.program(); // parse;

        ParseTreeWalker walker = new ParseTreeWalker();
        ASTBuilder builder = new ASTBuilder();
        walker.walk(builder, tree);

        ASTViewer Viewer = new ASTViewer();
        int a  = 0;
        Viewer.visit(builder.Root);
    }
}
