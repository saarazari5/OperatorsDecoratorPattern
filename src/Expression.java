import java.util.List;
import java.util.Map;

/**
 */
public interface Expression {
    /**
     * @param assignment a map where key is a variable and value is a boolean
     * @return the result of expression evaluation
     * @throws Exception if there is a var in the evaluation
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;
    /**
     *  A convenience method. Like the `evaluate(assignment)` method above,
     *  but uses an empty assignment.
     * @return the value of the expression
     * @throws Exception if the expression is written worng
     */
    Boolean evaluate() throws Exception;

    /**
     *  @return a list of the variables in the expression.
      */
    List<String> getVariables();

    /**
     *  @return a nice string representation of the expression.
      */
    String toString();

    /**
     * @param var are replaced with the provided expression (Does not modify the
     *              current expression).
     * @param expression the expression we want to assign a var to
     * @return a new expression in which all occurrences of the variable
     */
    Expression assign(String var, Expression expression);

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
      */
    Expression nandify();

    /**
     * @return Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     * @return a simplified version of the current expression.
      */
    Expression simplify();

    /**
     * @return create a deep copy of the object
     */
    Expression copy();

    /**
     * @param expression other exp to compare to
     * @return true if string is equal
     */
    default boolean equals(Expression expression) {
        return this.toString().equals(expression.toString())
                || this.toString().equals(new StringBuilder(expression.toString()).reverse().toString());
    }

    /**
     * @return if the expression is of type Val
     */
    default boolean isValue() {
        return this.getClass().equals(Val.class);
    }
    /**
     * @return if the expression is of type Not
     */
    default boolean isNot() {
        return this.getClass().equals(Not.class);
    }

    /**
     * @return the value of an expression if is a value
     */
    default int valueIfIsValue() {
        if (isValue()) {
            if (((Val) this).getValue()) {
                return 1;
           } else {
                return 0;
            }
        }
        return -1;
    }
}