package XYXCompiler;

import XYXCompiler.Builder.ASTBuilder;
import XYXCompiler.Parser.XYXLexer;
import XYXCompiler.Parser.XYXParser;
import XYXCompiler.Semantic.SemanticCheck.ScopeTreeBuilder;
import XYXCompiler.Tools.Exceptions.SemanticException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

import static XYXCompiler.Tools.Exceptions.SemanticException.printExceptions;
import static org.antlr.v4.runtime.CharStreams.fromFileName;
import static org.antlr.v4.runtime.CharStreams.fromStream;

public class SemanticOJ {
    public static void main(String[] args) throws Exception {
        try{
            InputStream is = System.in;
            CharStream stream = fromStream(is);
            XYXLexer lexer              = new XYXLexer(stream);
            CommonTokenStream tokens    = new CommonTokenStream(lexer);
            XYXParser parser            = new XYXParser(tokens);
            ParseTree tree = parser.program(); // parse;
            ParseTreeWalker walker = new ParseTreeWalker();
            ASTBuilder builder = new ASTBuilder();
            walker.walk(builder, tree);

            ScopeTreeBuilder STB = new ScopeTreeBuilder();
            STB.visit(builder.Root);

            if(!SemanticException.exceptions.isEmpty()){
                printExceptions();
                throw new Exception();
            }
        }catch (Exception e){
            System.exit(1);
        }
    }
}