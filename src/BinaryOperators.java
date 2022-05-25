import java.util.function.BinaryOperator;

/**
 * a utils class with representations of binary operators in the program.
 */
public class BinaryOperators {
    /**
     * @return BinaryOperator for Xor.
     */
    public static BinaryOperator<Boolean> xor() {
        return Boolean::logicalXor;
    }

    /**
     * @return binary operator for and
     */
    public static BinaryOperator<Boolean> and() {
        return Boolean::logicalAnd;
    }

    /**
     * @return binary operator for or
     */
    public static BinaryOperator<Boolean> or() {
        return Boolean::logicalOr;
    }

    /**
     * @return binary operator for nor.
     */
    public static BinaryOperator<Boolean> nor() {
        return (first, second) -> !(first || second);
    }

    /**
     * @return binary operator for xnor.
     */
    public static BinaryOperator<Boolean> xnor() {
        return (first, second) -> first == second;
    }

    /**
     * @return binary operator for nand.
     */
    public static BinaryOperator<Boolean> nand() {
        return (first, second) -> !(Boolean.logicalAnd(first, second));
    }

}
