import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * an abstract representation of Unary operator.
 */
public abstract class UnaryExpression extends BaseExpression {

    private Expression expression;

    /**
     *
     * @param expression that represent the expression we will perform this unary operator on
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }
    abstract UnaryOperator<Boolean> unaryOperator();

    /**
     * getter.
     * @return this.expression
     */
    public Expression getExpression() {
        return expression;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return unaryOperator().apply(expression.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return unaryOperator().apply(expression.evaluate());
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>(expression.getVariables())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        UnaryExpression copy = this.copy();
        copy.expression = copy.expression.assign(var, expression);
        return copy;
    }

    @Override
    public String toString() {
        return operatorAsString()
                .concat("(")
                .concat(expression.toString())
                .concat(")");
    }

    @Override
    public UnaryExpression copy() {
        UnaryExpression unaryExpression = this;
        try {
            unaryExpression = getClass().getDeclaredConstructor(Expression.class).newInstance(expression.copy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unaryExpression;
    }
}
