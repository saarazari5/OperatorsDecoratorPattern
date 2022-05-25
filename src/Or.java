
import java.util.function.BinaryOperator;

/**
 * Or binary operator.
 */
public class Or extends BinaryExpression {
    /**
     * @param expression1 first expression.
     * @param expression2 second expression.
     */
    public Or(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    BinaryOperator<Boolean> binaryOperator() {
        return BinaryOperators.or();
    }

    @Override
    String operatorAsString() {
        return "|";
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(getExpression1().nandify(),
                            getExpression1().nandify()),
                new Nand(getExpression2().nandify(),
                        getExpression2().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(getExpression1().norify(),
                        getExpression2().norify()),
                new Nor(getExpression1().norify(),
                        getExpression2().norify()));
    }

    @Override
    public Expression simplify() {
        Expression simplifiedExp1 = getExpression1().simplify();
        Expression simplifiedExp2 = getExpression2().simplify();

        int expression1Value = simplifiedExp1.valueIfIsValue();
        int expression2Value = simplifiedExp2.valueIfIsValue();
        if (expression1Value == 1 || expression2Value == 1) {
            return new Val(true);
        }

        if (expression1Value == 0) {
            return simplifiedExp2;
        }

        if (expression2Value == 0 || simplifiedExp1.equals(simplifiedExp2)) {
            return simplifiedExp1;
        }

        return new Or(simplifiedExp1, simplifiedExp2);
    }
}
