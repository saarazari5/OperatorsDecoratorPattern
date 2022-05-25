import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Val expression.
 */
public class Val implements Expression {

    private boolean value;

    /**
     * @param value boolean of the expression.
     */
    public Val(boolean value) {
        this.value = value;
    }

    /** getter.
     * @return value
     */
    public boolean getValue() {
        return value;
    }

    /**
     * @param value new value.
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return evaluate();
    }

    @Override
    public Boolean evaluate() {
        return value;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return value ? "T" : "F";
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return this.copy();
    }

    @Override
    public Expression nandify() {
        return this.copy();
    }

    @Override
    public Expression norify() {
        return this.copy();
    }

    @Override
    public Expression simplify() {
        return this.copy();
    }

    @Override
    public Expression copy() {
        return new Val(this.value);
    }
}
