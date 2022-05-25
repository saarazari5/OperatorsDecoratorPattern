
import java.util.function.BinaryOperator;

/**
 * Xor binary operator.
 */
public class Xor extends BinaryExpression {

    @Override
    String operatorAsString() {
        return "^";
    }
    /**
     * @param expression1 first expression
     * @param expression2 second expression
     */
    public Xor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    BinaryOperator<Boolean> binaryOperator() {
        return BinaryOperators.xor();
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(getExpression1().nandify(),
                new Nand(getExpression1().nandify(), getExpression2().nandify())),
                 new Nand(getExpression2().nandify(), new Nand(getExpression1().nandify(),
                         getExpression2().nandify())));
    }

    @Override
    public Expression norify() {
        return new Nor(
                new Nor(new Nor(getExpression1().norify(), getExpression1().norify()),
                        new Nor(getExpression2().norify(), getExpression2().norify())),
                new Nor(getExpression1().norify(), getExpression2().norify()));
    }

    @Override
    public Expression simplify() {
        Expression simplifiedExp1 = getExpression1().simplify();
        Expression simplifiedExp2 = getExpression2().simplify();
        int expression1Value = simplifiedExp1.valueIfIsValue();
        int expression2Value = simplifiedExp2.valueIfIsValue();
        if (expression1Value == 0) {
            return simplifiedExp2;
        }

        if (expression1Value == 1) {
            return new Not(simplifiedExp2).simplify();
        }

        if (expression2Value == 0) {
            return simplifiedExp1;
        }

        if (expression2Value == 1) {
            return new Not(simplifiedExp1).simplify();
        }

        if (simplifiedExp1.equals(simplifiedExp2)) {
            return new Val(false);
        }
        return new Xor(simplifiedExp1, simplifiedExp2);
    }
}
