package ast;

import ast.visitables.Atom;
import ast.visitables.BinaryOperator;
import ast.visitables.Constant;
import ast.visitables.Not;

public interface FormulaVisitor <Result, AdditionalArg> {
    Result visit (Not formula,               AdditionalArg a);
    Result visit (BinaryOperator formula,    AdditionalArg a);
    Result visit (Atom formula,              AdditionalArg a);
    Result visit (Constant formula,          AdditionalArg a);
}
