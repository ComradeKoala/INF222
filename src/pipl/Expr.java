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

public abstract class Expr extends State{

    public Expr(){

    }

}

class IL extends Expr {

    private I value;

    public IL(int i) {
        this.value = new I(i);
    }
}

class Plus extends Expr {

    private Expr expr1;
    private Expr expr2;

    public Plus(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2= expr2;

    }
}

class Mult extends Expr {

    Expr expr1;
    Expr expr2;

    public Mult(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2= expr2;
    }
}

class Uminus extends Expr {

    private Expr expr;

    public Uminus(Expr expr) {
        this.expr = expr;
    }
}

class BL extends Expr {

    private Expr expr;

    public BL(Expr expr) {
        this.expr = expr;

    }
}

class Or extends Expr {

    private Expr expr1;
    private Expr expr2;

    public Or(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2= expr2;

    }
}

class And extends Expr {

    private Expr expr1;
    private Expr expr2;

    public And(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
}

class Not extends Expr {

    private Expr expr;

    public Not(Expr expr) {
        this.expr = expr;
    }
}

class Choice extends Expr {

    private Expr expr1;
    private Expr expr2;

    public Choice(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
}

class Equal extends Expr {

    private Expr expr1;
    private Expr expr2;

    public Equal(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2= expr2;
    }
}

class Le extends Expr {

    private Expr expr1;
    private Expr expr2;

    public Le(Expr expr1, Expr expr) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
}

class VarExp extends Expr {

    private String s;

    public VarExp(String s) {
        this.s = s;
    }
}