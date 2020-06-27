package ast;

import ast.visitables.*;
import ast.visitors.EvaluateVisitor;
import ast.visitors.PrintVisitor;

import java.util.Map;

public class FormulaFactory {

	public static Formula atom(String atomId) {
		return new Atom(atomId);
	}

	public static Formula and(Formula leftOp, Formula rightOp) {
		return new BinaryOperator(BinOp.AndOp, leftOp, rightOp);
	}

	public static Formula or(Formula leftOp, Formula rightOp) {
		return new BinaryOperator(BinOp.OrOp, leftOp, rightOp);
	}

	public static Formula implies(Formula leftOp, Formula rightOp) {
		return new BinaryOperator(BinOp.ImpliesOp, leftOp, rightOp);
	}

	public static Formula not(Formula notOp) {
		return new Not(notOp);
	}

	public static final Formula TRUE = new Constant(true);

	public static final Formula FALSE = new Constant(false);

	public static String prettyPrint(Formula f) {
		PrintVisitor print = new PrintVisitor();
		f.accept(print,0);
		return print.toString();
	}

	public static Boolean evaluate(Formula f, Map<String,Boolean> env) {
		EvaluateVisitor eval = new EvaluateVisitor(env);
		return f.accept(eval, null);
	}
}
