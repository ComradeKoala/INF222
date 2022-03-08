package pipl;

import java.util.List;

/**
 * Solution for task 4.3.1
 *
 * @author Jørgen Lohne
 * @since 2022-03-06
 */
public abstract class Stmt extends State {
    /*
    data pipl.Stmt =
        Assert pipl.Expr
        | Assign Var pipl.Expr
        | While pipl.Expr pipl.Stmt
        | IfStmt pipl.Expr pipl.Stmt pipl.Stmt
        | Sequence [ pipl.Stmt ]
        deriving (Show, Eq, Read)
    */

    public abstract State exec(State state);

}

class Assert extends Stmt {

    Expr expr;

    public Assert(Expr expr) {
        this.expr = expr;
    }

    @Override
    public State exec(State state) {
        B b = (B) expr.eval();

        if (b.v0) {
            return state;
        } else {
            System.out.println("Assert failed for " + expr.toString() + " in state" + state.toString());
        }

        return null;
    }
}

class Assign extends Stmt {

    private String var;
    private Expr expr;

    public Assign(String var, Expr expr) {
        this.var = var;
        this.expr = expr;
    }

    /**
     * Updates variable if it exists in the store, otherwise adds variable to store.
     *
     * @param state the state of the store
     * @return the updated state of the store
     */
    @Override
    public State exec(State state) { //

        Value val = expr.eval();

        if (isVariable(var)) {
            changeValue(var, val);
        } else {
            addVariable(var, val);
        }
        return state;
    }
}

class While extends Stmt {

    private Expr expr;
    private Sequence sequence;

    public While(Expr expr, Sequence sequence) {
        this.expr = expr;
        this.sequence = sequence;
    }


    /**
     * Performs a while loop while condition is met
     *
     * @param state
     * @return current state
     */
    @Override
    public State exec(State state) {
        B condition = (B) expr.eval();

        while (condition.v0) {
            sequence.exec(state);
        }

        return state;
    }
}

class IfStmt extends Stmt {

    private Expr expr;
    private Stmt stmt1;
    private Stmt stmt2;

    public IfStmt(Expr expr, Stmt stmt1, Stmt stmt2) {
        this.expr = expr;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    @Override
    public State exec(State state) {
        B b = (B) expr.eval();

        if (b.v0) {
            stmt1.exec(state);
        } else {
            stmt2.exec(state);
        }
        return state;
    }
}

class Sequence extends Stmt {

    private List stmts;

    public Sequence(List<Stmt> stmts) {
        this.stmts = stmts;
    }

    /**
     * Iterates through list of sequences and executes them one by one
     *
     * @param state
     * @return current state
     */
    @Override
    public State exec(State state) {
        for (Object stmt : stmts) {
            Stmt currentStatment = (Stmt) stmt;
            state = currentStatment.exec(state); //TODO: reasigned state check if work
        }
        return state;

    }
}