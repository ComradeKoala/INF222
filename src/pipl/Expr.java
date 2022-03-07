package pipl;

/**
 * Solution for task 4.3.1
 *
 * @author Jørgen Lohne
 * @since 2022-03-06
 *
 */


/*
    data pipl.Expr =
    IL Integer -- integer literal
    | Plus pipl.Expr pipl.Expr
    | Mult pipl.Expr pipl.Expr
    | Uminus pipl.Expr
    | BL Bool -- boolean literal
    | Or pipl.Expr pipl.Expr
    | And pipl.Expr pipl.Expr
    | Not pipl.Expr
    | Choice pipl.Expr pipl.Expr pipl.Expr
    | Equal pipl.Expr pipl.Expr -- equality between integers
    | Le pipl.Expr pipl.Expr -- less or equal ≤
    | VarExp Var -- a variable is an expression
    deriving (Show, Eq, Read)
*/

public abstract class Expr {



    public Expr(){

    }

}

class IL extends Expr {

    public IL(int i) {

    }
}

class Plus extends Expr {

    public Plus(Expr ex1, Expr ex2) {

    }
}

class Mult extends Expr {

    public Mult(Expr expr1, Expr expr2) {

    }
}

class Uminus extends Expr {

    public Uminus(Expr  expr) {

    }
}

class BL extends Expr {

    public BL(Expr expr) {

    }
}

class Or extends Expr {

    public Or(Expr expr1, Expr expr2) {

    }
}

class And extends Expr {

    public And(Expr expr1, Expr expr2) {

    }
}

class Not extends Expr {

    public Not(Expr expr) {

    }
}

class Choice extends Expr {

    public Choice(Expr expr1, Expr expr2) {

    }
}

class Equal extends Expr {

    public Equal(Expr expr1, Expr expr2) {

    }
}

class Le extends Expr {

    public Le(Expr expr1, Expr expr) {

    }
}

class VarExp extends Expr {

    public VarExp(String expr) {

    }
}