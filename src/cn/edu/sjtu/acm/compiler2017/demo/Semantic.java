package cn.edu.sjtu.acm.compiler2017.demo;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;

public class Semantic {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("program.txt");
        System.err.println("Hello world");
    }
}
