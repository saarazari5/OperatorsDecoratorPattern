/**
 * an exception that represent when an expression has been evaluated with an un assigned variable.
 */
public class InvalidOperatorException extends Exception {
    @Override
    public String getMessage() {
        return "You performed an Operator in something that is not a boolean, check you variables \n";
    }
}
