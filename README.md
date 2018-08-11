# MX-Compiler
Write a compiler from scratch

## Architecture

**Front End**  
M× language -----> Antlr Parse Tree -----> AST(Abstract Syntax Tree)

**Semantic Checker**  
AST -----> ScopeTree Builder -----> Type Checker -----> Reference Checker

**Intermediate Representation**  
AST -----> IR(Control Flow Graph)

**Back End**  
CFG -----> Liveliness Analyse -----> Allocate Stack Frame -----> Code Generator

## Tools
```
LLIRInterpreter.java (By Lequn Chen)
```
Print your IR in LLIR format, and the interpreter will run your IR and return a number.  
Original Repo： https://github.com/abcdabcd987/LLIRInterpreter

```
ASTViewer.java
IRViewer.java
```
Print AST and CFG for Inspection.
