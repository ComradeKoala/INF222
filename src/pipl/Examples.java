package pipl;

import java.util.Arrays;

/**
 * A state is an environment of variable-store index associations, and a store
 * which at each store index keeps a value (for that variable).
 * <p>
 * NB! Type location is represented as a Java byte.
 *
 * @author Jørn Lode & Magne Haveraaen
 * @see BIPL3State.hs
 * @since 2022-02-16
 */
@SuppressWarnings("JavadocReference")
class Examples {
    /**
     * Euclidean division statement. @see BIPL3AST.hs
     */
    final static Stmt eucliddiv_stmt = new Sequence(
            Arrays.asList(new Assign("q", new IL(0)), new Assign("r", new VarExp("x")),
                    new While(new Le(new VarExp("y"), new VarExp("r")),
                            new Sequence(Arrays.asList(
                                    new Assign("r", new Plus(new VarExp("r"), new Uminus(new VarExp("y")))),
                                    new Assign("q", new Plus(new VarExp("q"), new IL(1))))))));

    /**
     * Computus statement. @see BIPL3AST.hs
     */
    final static Stmt computus_stmt = new Sequence(
            Arrays.asList(new Assign("x", new VarExp("Y")), new Assign("y", new IL(19)), eucliddiv_stmt,
                    new Assign("a", new VarExp("r")), new Assign("x", new VarExp("Y")), new Assign("y", new IL(100)),
                    eucliddiv_stmt, new Assign("b", new VarExp("q")), new Assign("c", new VarExp("r")),
                    new Assign("x", new VarExp("b")), new Assign("y", new IL(4)), eucliddiv_stmt,
                    new Assign("d", new VarExp("q")), new Assign("e", new VarExp("r")),
                    new Assign("x", new Plus(new VarExp("b"), new IL(8))), new Assign("y", new IL(25)), eucliddiv_stmt,
                    new Assign("f", new VarExp("q")),
                    new Assign("x", new Plus(new Plus(new VarExp("b"), new Uminus(
                            new VarExp("f"))), new IL(
                            1))),
                    new Assign("y", new IL(3)), eucliddiv_stmt, new Assign("g", new VarExp("q")),
                    new Assign("x",
                            new Plus(new Plus(new Plus(new Plus(new Mult(new IL(19), new VarExp("a")), new VarExp("b")),
                                    new Uminus(new VarExp("d"))), new Uminus(new VarExp("g"))), new IL(15))),
                    new Assign("y", new IL(30)), eucliddiv_stmt, new Assign("h", new VarExp("r")),
                    new Assign("x", new VarExp("c")), new Assign("y", new IL(4)), eucliddiv_stmt,
                    new Assign("i", new VarExp("q")), new Assign("k", new VarExp("r")),
                    new Assign("x",
                            new Plus(
                                    new Plus(new Plus(new Plus(new IL(32), new Mult(new IL(2), new VarExp("e"))),
                                            new Mult(new IL(2), new VarExp("i"))), new Uminus(new VarExp("h"))),
                                    new Uminus(new VarExp("k")))),
                    new Assign("y", new IL(7)), eucliddiv_stmt, new Assign("l", new VarExp("r")),
                    new Assign("x",
                            new Plus(new Plus(new VarExp("a"), new Mult(new IL(11), new VarExp("h"))),
                                    new Mult(new IL(22), new VarExp("l")))),
                    new Assign("y", new IL(451)), eucliddiv_stmt, new Assign("m", new VarExp("q")),
                    new Assign("x",
                            new Plus(new Plus(new Plus(new VarExp("h"), new VarExp("l")),
                                    new Uminus(new Mult(new IL(7), new VarExp("m")))), new IL(114))),
                    new Assign("y", new IL(31)), eucliddiv_stmt, new Assign("n", new VarExp("q")),
                    new Assign("o", new VarExp("r")), new Assign("month", new VarExp("n")),
                    new Assign("day", new Plus(new VarExp("o"), new IL(1)))));

    /**
     * Unit test for example statements (above) and expressions -- BIPL3AST.
     */
    public static void main(String[] args) {
        var state = State.newState();

        try {
            state.addVariable("x", new I(49));
            state.addVariable("y", new I(11));
            eucliddiv_stmt.exec(state);
            System.out.printf("q(expected 4) = %s\nr(expected 5) = %s\n", state.getValue("q").show(),
                    state.getValue("r").show());
        } catch (AssertionError err) {
        }

        try {
            state.addVariable("Y", new I(2008));
            computus_stmt.exec(state);
            System.out.printf("month (expected 3) = %s\nday (expected 23) = %s\n", state.getValue("month").show(),
                    state.getValue("day").show());
        } catch (AssertionError err) {
        }

    }
}
