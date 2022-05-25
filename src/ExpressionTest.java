import java.util.HashMap;

/**
 * main class.
 */
public class ExpressionTest {

    /**
     * This is the main method.
     *
     * @param args nothing
     */
    public static void main(String[] args) {

        Expression e = new Xor(new And(new Var("x"), new Val(false)), new Or(new Var("y"),
                new Xnor(new Or(new Val(false), new Var("z")), new Var("x"))));
        System.out.println(e);
        HashMap<String, Boolean> hashMap = new HashMap<>();
        hashMap.put("x", Boolean.TRUE);
        hashMap.put("y", Boolean.FALSE);
        hashMap.put("z", Boolean.TRUE);
        try {
            System.out.println(e.evaluate(hashMap));
            System.out.println(e.nandify());
            System.out.println(e.norify());
            System.out.println(e.simplify());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}