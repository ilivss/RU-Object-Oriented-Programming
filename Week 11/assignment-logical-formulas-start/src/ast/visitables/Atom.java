package ast.visitables;

import ast.Formula;
import ast.FormulaVisitor;

public class Atom implements Formula {
    private String atomic;

    public Atom (String atomic){
        this.atomic = atomic;
    }

    public String getAtomic() {
        return atomic;
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
