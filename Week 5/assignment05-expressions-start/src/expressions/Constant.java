package expressions;

import java.util.Map;

public class Constant extends NoArgExpr {
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public double eval(Map<String, Double> env) {
        return value;
    }

    @Override
    public Expression partialEval() {
        return new Constant(value);
    }

    @Override
    public Double getConstantValue() {
        return value;
    }


}
