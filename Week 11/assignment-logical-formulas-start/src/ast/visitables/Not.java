package ast.visitables;

import ast.Formula;
import ast.FormulaVisitor;

public class Not implements Formula {
    private Formula operand;

    public Not (Formula operand){
        this.operand = operand;
    }

    public Formula getOperand() {
        return operand;
    }

    @Override
    public <R, A> R accept(FormulaVisitor<R, A> v, A a) {
        return v.visit(this, a);
    }

    @Override
    public int getPrecedence() {
        return 4;
    }
}
