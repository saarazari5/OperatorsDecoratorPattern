
import java.util.function.BinaryOperator;

/**
 * Not binary expression.
 */
public class Nor extends BinaryExpression {
    /**
     * @param expression1 first expression.
     * @param expression2 second expression.
     */
    public Nor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    BinaryOperator<Boolean> binaryOperator() {
        return BinaryOperators.nor();
    }

    @Override
    String operatorAsString() {
        return "V";
    }

    @Override
    public Expression nandify() {
        return new Nand(
                new Nand(new Nand(getExpression1().nandify(), getExpression1().nandify()),
                        new Nand(getExpression2().nandify(), getExpression2().nandify())),
                new Nand(new Nand(getExpression1().nandify(), getExpression1().nandify()),
                        new Nand(getExpression2().nandify(), getExpression2().nandify()))
        );
    }

    @Override
    public Expression norify() {
        return new Nor(getExpression1().norify(), getExpression2().norify());
    }

    @Override
    public Expression simplify() {
        Expression simplifiedExp1 = getExpression1().simplify();
        Expression simplifiedExp2 = getExpression2().simplify();

        int expression1Value = simplifiedExp1.valueIfIsValue();
        int expression2Value = simplifiedExp2.valueIfIsValue();
        if (expression1Value == 1 || expression2Value == 1) {
            return new Val(false);
        }

        if (expression1Value == 0) {
            return new Not(simplifiedExp2).simplify();
        }

        if (expression2Value == 0 || simplifiedExp1.equals(simplifiedExp2)) {
            return new Not(simplifiedExp1).simplify();
        }

        return new Nor(simplifiedExp1, simplifiedExp2);
    }
}
