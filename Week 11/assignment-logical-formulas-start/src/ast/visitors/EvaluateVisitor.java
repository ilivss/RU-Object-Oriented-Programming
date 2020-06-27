package ast.visitors;

import ast.FormulaVisitor;
import ast.visitables.Atom;
import ast.visitables.BinaryOperator;
import ast.visitables.Constant;
import ast.visitables.Not;

import java.util.Map;

public class EvaluateVisitor implements FormulaVisitor<Boolean, Void> {
    private Map<String, Boolean> env;

    public EvaluateVisitor (Map<String, Boolean> env){
        this.env = env;
    }

    @Override
    public Boolean visit(Not formula, Void a) {
        return !formula.getOperand().accept(this, null);
    }

    @Override
    public Boolean visit(BinaryOperator formula, Void a) {
        return formula.getOperator().apply(formula.getLeftOperand().accept(this, null), formula.getRightOperand().accept(this, null));
    }

    @Override
    public Boolean visit(Atom formula, Void a) {
        return env.get(formula.getAtomic());
    }

    @Override
    public Boolean visit(Constant formula, Void a) {
        return formula.getConstant();
    }
}
