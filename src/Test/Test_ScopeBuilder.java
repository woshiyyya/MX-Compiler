package Test;

import XYXCompiler.Builder.ASTBuilder;
import XYXCompiler.Parser.XYXLexer;
import XYXCompiler.Parser.XYXParser;
import XYXCompiler.Semantic.SemanticCheck.ScopeTreeBuilder;
import XYXCompiler.Tools.ASTViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import static XYXCompiler.Tools.Exceptions.SemanticException.printExceptions;
import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Test_ScopeBuilder{
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

            ScopeTreeBuilder STB = new ScopeTreeBuilder();
            STB.visit(builder.Root);

            printExceptions();
        }
}
