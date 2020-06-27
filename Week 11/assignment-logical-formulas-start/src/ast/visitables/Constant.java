package ast.visitables;

import ast.Formula;
import ast.FormulaVisitor;

public class Constant implements Formula {
    private boolean constant;

    public Constant (boolean constant) {
        this.constant = constant;
    }

    public boolean getConstant() {
        return constant;
    }

    @Override
    public <R, A> R accept(FormulaVisitor<R, A> v, A a) {
        return v.visit(this, a);
    }

    @Override
    public int getPrecedence() {
        return 0;
    }
}
