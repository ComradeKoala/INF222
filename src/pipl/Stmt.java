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

    public Assert() {}
}

class Assign extends Stmt {

    public Assign(String q, Expr expr) {
    }
}

class While extends Stmt {

    public While(Le le, Sequence sequence) {
    }
}

class IfStmt extends Stmt {

}

class Sequence extends Stmt {

    public <T> Sequence(List<T> asList) {
    }
}