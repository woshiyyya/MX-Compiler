package XYXCompiler;

import XYXCompiler.BackEnd.CodeGenerator;
import XYXCompiler.FrontEnd.Builder.ASTBuilder;
import XYXCompiler.FrontEnd.Builder.XIRBuilder;
import XYXCompiler.FrontEnd.Semantic.SemanticCheck.ReferenceChecker;
import XYXCompiler.FrontEnd.Semantic.SemanticCheck.ScopeTreeBuilder;
import XYXCompiler.FrontEnd.Semantic.SemanticCheck.TypeChecker;
import XYXCompiler.Parser.XYXLexer;
import XYXCompiler.Parser.XYXParser;
import XYXCompiler.Tools.Exceptions.SemanticException;
import XYXCompiler.Tools.Exceptions.VerboseListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

import static XYXCompiler.Tools.Exceptions.SemanticException.printExceptions;
import static org.antlr.v4.runtime.CharStreams.fromStream;

public class Main {
    public static void main(String[] args)throws Exception{
        try{
            InputStream is = System.in;
            CharStream stream = fromStream(is);
            XYXLexer lexer              = new XYXLexer(stream);

            lexer.removeErrorListeners();
            lexer.addErrorListener(new VerboseListener());

            CommonTokenStream tokens    = new CommonTokenStream(lexer);
            XYXParser parser            = new XYXParser(tokens);

            parser.removeErrorListeners(); // remove ConsoleErrorListener
            parser.addErrorListener(new VerboseListener()); // add ours

            ParseTree tree = parser.program(); // parse;
            ParseTreeWalker walker = new ParseTreeWalker();
            ASTBuilder builder = new ASTBuilder();
            walker.walk(builder, tree);

            ScopeTreeBuilder STB = new ScopeTreeBuilder();
            STB.visit(builder.Root);

            TypeChecker TCK = new TypeChecker(STB.typeTable);
            TCK.visit(builder.Root);

            ReferenceChecker RCK = new ReferenceChecker();
            RCK.visit(builder.Root);

            if(!SemanticException.exceptions.isEmpty()){
                printExceptions(0);
                throw new Exception();
            }

            //-----Build CFG
            XIRBuilder XIR = new XIRBuilder(STB.typeTable);
            try{
                XIR.VISIT(builder.Root);
            }catch (Exception IR){
                System.err.println("IRbuild error!");
            }


            //Print Assembly
            CodeGenerator generator = new CodeGenerator(XIR.Root);
            try{
                generator.generate(false);
            }catch (Exception ge){
                System.out.println("Codegen error!");
            }


        }catch (Exception e){
            System.err.println("Runtime Exception!");
            System.exit(1);
        }
    }

}
