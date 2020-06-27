package ast.visitors;

import ast.FormulaVisitor;
import ast.visitables.Atom;
import ast.visitables.BinaryOperator;
import ast.visitables.Constant;
import ast.visitables.Not;

public class PrintVisitor implements FormulaVisitor<Void, Integer> {
    private StringBuilder result;

    public PrintVisitor (){
        result = new StringBuilder();
    }

    @Override
    public String toString() {
        return result.toString();
    }

    @Override
    public Void visit(Not formula, Integer a) {
        result.append("!");
        formula.getOperand().accept(this, formula.getPrecedence());

        return null;
    }

    @Override
    public Void visit(BinaryOperator formula, Integer a) {
        if (formula.getPrecedence() <= a){
            result.append("(");
        }

        formula.getLeftOperand().accept(this, formula.getPrecedence());
        result.append(formula.getOperator().string);
        formula.getRightOperand().accept(this, formula.getPrecedence());

        if (formula.getPrecedence() <= a){
            result.append(")");
        }

        return null;
    }

    @Override
    public Void visit(Atom formula, Integer a) {
        result.append(formula.getAtomic());

        return null;
    }

    @Override
    public Void visit(Constant formula, Integer a) {
        if (formula.getConstant()) {
            result.append("True");
        } else {
            result.append("False");
        }

        return null;
    }
}
