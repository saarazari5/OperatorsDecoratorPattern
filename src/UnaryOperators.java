import java.util.function.UnaryOperator;

/**
 * a util class represent all Unary actions the program can perform.
 */
public class UnaryOperators {
    /**
     * @return a predicate representation of not operator.
     */
    static UnaryOperator<Boolean> not() {
        return (value) -> !value;
    }
}
