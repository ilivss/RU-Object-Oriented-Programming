package expressions;

import java.util.Map;
import java.util.function.BinaryOperator;

public abstract class TwoArgExpr implements Expression {
    private final Expression x, y;
    private final BinaryOperator<Double> evalOp;
    private final BinaryOperator<Expression> peOp;

    protected TwoArgExpr (Expression x, Expression y, BinaryOperator<Double> evalOp, BinaryOperator<Expression> peOp) {
        this.x = x;
        this.y = y;
        this.evalOp = evalOp;
        this.peOp = peOp;
    }

    @Override
    public double eval(Map<String, Double> env) {
        return evalOp.apply(x.eval(env), y.eval(env));
    }

    @Override
    public Expression partialEval() {
        return peOp.apply(x.partialEval(), y.partialEval());
    }

    @Override
    public String toString() {
        return "(" + x + getOperator() + y + ")";
    }

    protected abstract String getOperator();
}
