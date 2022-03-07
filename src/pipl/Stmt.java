package pipl;

import java.util.List;

/**
 * Solution for task 4.3.1
 *
 * @author Jørgen Lohne
 * @since 2022-03-06
 *
 */
public abstract class Stmt {
    /*
    data pipl.Stmt =
        Assert pipl.Expr
        | Assign Var pipl.Expr
        | While pipl.Expr pipl.Stmt
        | IfStmt pipl.Expr pipl.Stmt pipl.Stmt
        | Sequence [ pipl.Stmt ]
        deriving (Show, Eq, Read)
    */

    public Stmt() {

    }

}

class Assert extends Stmt {

    Expr expr;

    public Assert(Expr expr) {
        this.expr = expr;
    }
}

class Assign extends Stmt {

    private String var;
    private Expr expr;

    public Assign(String var, Expr expr) {
        this.var = var;
        this.expr = expr;
    }
}

class While extends Stmt {

    private Le le;
    private Sequence sequence;

    public While(Le le, Sequence sequence) {
        this.le = le;
        this.sequence = sequence;
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
}

class Sequence extends Stmt {

    private List stmnts;

    public <T> Sequence(List<T> stmnts) {
        this.stmnts = stmnts;
    }
}