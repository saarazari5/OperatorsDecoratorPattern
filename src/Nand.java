
import java.util.function.BinaryOperator;

/**
 * Nand binary expression.
 */
public class Nand extends BinaryExpression {
    /**
     * @param expression1 first expression.
     * @param expression2 second expression.
     */
    public Nand(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    BinaryOperator<Boolean> binaryOperator() {
        return BinaryOperators.nand();
    }

    @Override
    public Expression nandify() {
        return new Nand(getExpression1().nandify(), getExpression2().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(
                new Nor(new Nor(getExpression1().norify(), getExpression1().norify()),
                        new Nor(getExpression2().norify(), getExpression2().norify())),
                new Nor(new Nor(getExpression1().norify(), getExpression1().norify()),
                        new Nor(getExpression2().norify(), getExpression2().norify()))
        );
    }

    @Override
    String operatorAsString() {
        return "A";
    }

    @Override
    public Expression simplify() {
        Expression simplifiedExp1 = getExpression1().simplify();
        Expression simplifiedExp2 = getExpression2().simplify();

        int expression1Value = simplifiedExp1.valueIfIsValue();
        int expression2Value = simplifiedExp2.valueIfIsValue();
        if (expression1Value == 0 || expression2Value == 0) {
            return new Val(true);
        }

        if (expression1Value == 1) {
            return new Not(simplifiedExp2).simplify();
        }

        if (expression2Value == 1 || getExpression1().equals(getExpression2())) {
            return new Not(simplifiedExp1).simplify();
        }

        return new Nand(simplifiedExp1, simplifiedExp2);
    }
}
