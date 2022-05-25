
import java.util.function.BinaryOperator;

/**
 * And binary operator.
 */
public class And extends BinaryExpression {
    /**
     * @param expression1 first expression
     * @param expression2 second expression
     */
    public And(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    BinaryOperator<Boolean> binaryOperator() {
        return BinaryOperators.and();
    }

    @Override
    String operatorAsString() {
        return "&";
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(getExpression1().nandify(), getExpression2().nandify()),
                new Nand(getExpression1().nandify(), getExpression2().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(getExpression1().norify(), getExpression1().norify()),
                new Nor(getExpression2().norify(), getExpression2().norify()));
    }

    @Override
    public Expression simplify() {
        Expression simplifiedExp1 = getExpression1().simplify();
        Expression simplifiedExp2 = getExpression2().simplify();

        int expression1Value = simplifiedExp1.valueIfIsValue();
        int expression2Value = simplifiedExp2.valueIfIsValue();
        if (expression1Value == 0 || expression2Value == 0) {
            return new Val(false);
        }

        if (expression1Value == 1) {
            return simplifiedExp2;
        }

        if (expression2Value == 1) {
            return simplifiedExp1;
        }

        if (simplifiedExp1.equals(simplifiedExp2)) {
            return simplifiedExp1;
        }
        return new And(simplifiedExp1, simplifiedExp2);
    }
}
