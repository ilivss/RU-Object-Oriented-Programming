package ast.visitables;

import java.util.function.BinaryOperator;

public enum BinOp implements BinaryOperator<Boolean> {
    AndOp       ("/\\", (a1, a2) -> a1 && a2, 3),
    OrOp        ("\\/", (a1, a2) -> a1 || a2, 2),
    ImpliesOp   ("=>",  (a1, a2) -> !a1 || a2, 1);

    public final String string;
    public final BinaryOperator<Boolean> operator;
    public final int precedence;

    BinOp (String string, BinaryOperator<Boolean> binOp, int precedence){
        this.string = string;
        this.operator = binOp;
        this.precedence = precedence;
    }

    @Override
    public Boolean apply(Boolean a1, Boolean a2) {
        return operator.apply(a1, a2);
    }
}
