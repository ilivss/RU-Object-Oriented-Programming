package ast.visitables;

import ast.Formula;
import ast.FormulaVisitor;

public class BinaryOperator implements Formula {
    private BinOp operator;
    private Formula leftOperand;
    private Formula rightOperand;

    public BinaryOperator (BinOp operator, Formula leftOperand, Formula rightOperand){
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public BinOp getOperator() {
        return operator;
    }

    public Formula getLeftOperand() {
        return leftOperand;
    }

    public Formula getRightOperand() {
        return rightOperand;
    }

    @Override
    public <R, A> R accept(FormulaVisitor<R, A> v, A a) {
        return v.visit(this, a);
    }

    @Override
    public int getPrecedence() {
        return operator.precedence;
    }
}
