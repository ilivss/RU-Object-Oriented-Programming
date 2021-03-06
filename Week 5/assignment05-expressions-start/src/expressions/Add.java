package expressions;

public class Add extends TwoArgExpr {

    public Add(Expression x, Expression y) {
        super(x, y,
                (a1, a2) -> a1 + a2,
                (p1, p2) -> partialEvalAdd(p1, p2));
    }

    private static Expression partialEvalAdd (Expression x, Expression y) {
        x=x.partialEval();
        y=y.partialEval();

        if (x.getClass() == Constant.class && y.getClass() == Constant.class) {
            return new Constant(x.getConstantValue() + y.getConstantValue());
        }
        else if (x.getClass() == Constant.class && x.getConstantValue() == 0.0) {
            return y;
        }
        else if (y.getClass() == Constant.class && y.getConstantValue() == 0.0) {
            return x;
        }
        else {
            return new Add(x,y);
        }
    }

    @Override
    protected String getOperator() {
        return "+";
    }
}
