/**
 * Solution for tasks 4.3.1 together with
 *
 * @author: Jørgen Lohne
 * @see pipl.Expr
 * @since 2022-03-06
 */

package pipl;

import java.util.List;

public abstract class Stmt {
    public Stmt() {
    }

    public abstract State exec(State state);
}

class Assert extends Stmt {
    private final Expr expr;

    public Assert(Expr expr) {
        this.expr = expr;
    }

    public State exec(State state) {
        B cond = (B) expr.eval(state);
        if (cond.v0)
            return state;

        throw new Error("Assert failed for " + expr.toString() + "in state" + state.toString());

    }
}

class Assign extends Stmt {
    private final String name;
    private final Expr expr;

    public Assign(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public State exec(State state) {
        Value val = expr.eval(state);
        if (state.isVariable(name)) {
            state.changeValue(name, val);
        } else {
            state.addVariable(name, val);
        }

        return state;
    }
}

class While extends Stmt {
    private final Expr cond;
    private final Stmt stmt;

    public While(Expr cond, Stmt stmt) {
        this.cond = cond;
        this.stmt = stmt;
    }

    @Override
    public State exec(State state) {
        B condition = (B) cond.eval(state);
        if (condition.v0) {
            return this.exec(stmt.exec(state));
        }

        return state;
    }
}

class IfStmt extends Stmt {
    private final Expr cond;
    private final Stmt s1, s2;

    public IfStmt(Expr cond, Stmt s1, Stmt s2) {
        this.cond = cond;
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public State exec(State state) {
        B condition = (B) cond.eval(state);
        if (condition.v0) {
            return s1.exec(state);
        } else {
            return s2.exec(state);
        }
    }
}

class Sequence extends Stmt {
    private final List<Stmt> stmts;

    Sequence(List<Stmt> stmts) {
        this.stmts = stmts;
    }

    @Override
    public State exec(State state) {
        for (Stmt stmt : stmts) {
            state = stmt.exec(state);
        }

        return state;
    }
}
