package ast;

public interface Formula {
    <R, A> R accept (FormulaVisitor<R,A> v, A a);
    int getPrecedence();
}
