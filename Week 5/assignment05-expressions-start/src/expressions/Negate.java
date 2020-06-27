package expressions;

import java.util.Map;

public class Negate extends OneArgExpr {

    public Negate(Expression x) {
        super(x);
    }

    @Override
    public String toString () {
        return String.format("-%s",x.toString());
    }

    @Override
    public double eval(Map<String, Double> env) {
        return -x.eval(env);
    }

    @Override
    public Expression partialEval() {
        return new Negate(x);
    }
}
