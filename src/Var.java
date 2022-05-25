import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Var expression.
 */
public class Var implements Expression {

    private String variable;

    /**
     * @param variable a string representation of a variable.
     */
    public Var(String variable) {
        this.variable =  variable;
    }

    /** getter.
     * @return variable
     */
    public String getVariable() {
        return variable;
    }

    /** setter.
     * @param variable the new variable
     */
    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        }
        throw new InvalidOperatorException();
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new InvalidOperatorException();
    }

    @Override
    public List<String> getVariables() {
        ArrayList<String> vars = new ArrayList<>();
        vars.add(variable);
        return  vars;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        }
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
        return new Var(this.variable);
    }

    @Override
    public String toString() {
        return variable;
    }
}
