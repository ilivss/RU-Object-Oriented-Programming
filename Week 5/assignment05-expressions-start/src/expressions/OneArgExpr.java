package expressions;

public abstract class OneArgExpr implements Expression {
    protected final Expression x;


    public OneArgExpr(Expression x) {
        this.x = x;
    }
}
