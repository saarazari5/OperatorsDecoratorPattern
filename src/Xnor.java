
import java.util.function.BinaryOperator;

/**
 * Xnor binary operator.
 */
public class Xnor extends BinaryExpression {
    /**
     * @param expression1 first expression
     * @param expression2 second expression
     */
    public Xnor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    BinaryOperator<Boolean> binaryOperator() {
        return BinaryOperators.xnor();
    }

    @Override
    String operatorAsString() {
        return "#";
    }


    @Override
    public Expression nandify() {
        return new Nand(new Nand(new Nand(getExpression1().nandify(), getExpression1().nandify()),
                                 new Nand(getExpression2().nandify(), getExpression2().nandify())),
                new Nand(getExpression1().nandify(), getExpression2().nandify()));
    }


    @Override
    public Expression norify() {
        return new Nor(
                new Nor(getExpression1().norify(),
                        new Nor(getExpression1().norify(), getExpression2().norify())),
                new Nor(getExpression2().norify(),
                        new Nor(getExpression1().norify(), getExpression2().norify()))
        );
    }

    @Override
    public Expression simplify() {
        Expression simplifiedExp1 = getExpression1().simplify();
        Expression simplifiedExp2 = getExpression2().simplify();

        int expression1Value = simplifiedExp1.valueIfIsValue();
        int expression2Value = simplifiedExp2.valueIfIsValue();

        if (expression1Value == 0) {
            if (expression2Value == 1) {
                return new Val(false);
            } else if (expression2Value == 0) {
                return new Val(true);
            }
        }

        if (expression2Value == 0) {
            if (expression1Value == 1) {
                return new Val(false);
            }
        }

        if (simplifiedExp1.equals(simplifiedExp2)) {
            return new Val(true);
        }
        return new Xnor(simplifiedExp1, simplifiedExp2);
    }
}
