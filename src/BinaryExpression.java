import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Binary expression.
 */
public abstract  class BinaryExpression extends BaseExpression {

    private Expression expression1;
    private Expression expression2;

    /**
     * @param expression1 expression one.
     * @param expression2 expression two.
     */
    public BinaryExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    abstract BinaryOperator<Boolean> binaryOperator();

    protected Expression getExpression1() {
        return expression1;
    }

    protected Expression getExpression2() {
        return expression2;
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.addAll(expression1.getVariables());
        vars.addAll(expression2.getVariables());
        return new HashSet<>(vars)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        BinaryExpression copy = this.copy();
         copy.expression1 = copy.expression1.assign(var, expression);
         copy.expression2 = copy.expression2.assign(var, expression);
        return copy;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.binaryOperator().apply(expression1.evaluate(assignment), expression2.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
       return this.binaryOperator().apply(expression1.evaluate(), expression2.evaluate());
    }

    @Override
    public String toString() {
        return "(".concat(expression1.toString())
                .concat(" ")
                .concat(this.operatorAsString())
                .concat(" ")
                .concat(expression2.toString())
                .concat(")");
    }

    @Override
    public BinaryExpression copy() {
        BinaryExpression binaryExpression = this;
        try {
            binaryExpression = getClass()
                    .getDeclaredConstructor(Expression.class, Expression.class)
                    .newInstance(expression1.copy(), expression2.copy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return binaryExpression;
    }
}
