
import java.util.function.UnaryOperator;

/**
 * Not unary operator.
 */
public class Not extends UnaryExpression {

    @Override
    UnaryOperator<Boolean> unaryOperator() {
        return UnaryOperators.not();
    }
    /**
     * @param expression expression
     */
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    String operatorAsString() {
        return "~";
    }

    @Override
    public Expression nandify() {
        return new Nand(getExpression().nandify(), getExpression().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(getExpression().norify(), getExpression().norify());
    }

    @Override
    public Expression simplify() {
        Expression simplifiedExp = getExpression().simplify();
        int simplifiedExpVal = simplifiedExp.valueIfIsValue();
        if (simplifiedExpVal == 0) {
            return new Val(true);
        } else if (simplifiedExpVal == 1) {
            return new Val(false);
        }
        return new Not(simplifiedExp);
    }
}
