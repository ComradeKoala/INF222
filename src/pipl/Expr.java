package pipl;

/**
 * Solution for task 4.3.1
 *
 * @author Jørgen Lohne
 * @since 2022-03-06
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

public abstract class Expr extends State {

    public Expr() {
    }

    public abstract Value eval();

}

class IL extends Expr { //integer literal

    private int value;

    public IL(int i) {
        this.value = i;
    }

    @Override
    public Value eval() {
        return new I(value);
    }
}

class Plus extends Expr {

    private Expr expr1;
    private Expr expr2;

    public Plus(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;

    }

    @Override
    public Value eval() {
        I val1 = (I) expr1.eval();
        I val2 = (I) expr2.eval();
        return new I(val1.v0 + val2.v0);
    }
}

class Mult extends Expr {

    Expr expr1;
    Expr expr2;

    public Mult(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval() {
        I val1 = (I) expr1.eval();
        I val2 = (I) expr2.eval();

        return new I(val1.v0 * val2.v0);
    }
}

class Uminus extends Expr {

    private Expr expr;

    public Uminus(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Value eval() {
        I val = (I) expr.eval();

        return new I(-val.v0);
    }
}

class BL extends Expr {

    private Boolean bool;

    public BL(Boolean b) {
        this.bool = b;
    }

    @Override
    public Value eval() {
        return new B(bool);
    }
}

class Or extends Expr {

    private Expr expr1;
    private Expr expr2;

    public Or(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval() {
        B b1 = (B) expr1.eval();
        B b2 = (B) expr2.eval();
        return new B(b1.v0 || b2.v0);
    }
}

class And extends Expr {

    private Expr expr1;
    private Expr expr2;

    public And(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval() {
        B b1 = (B) expr1.eval();
        B b2 = (B) expr2.eval();
        return new B(b1.v0 && b2.v0);
    }
}

class Not extends Expr {

    private Expr expr;

    public Not(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Value eval() {
        B b = (B) expr.eval();
        return new B(!b.v0);
    }
}

class Choice extends Expr {

    private Expr expr1;
    private Expr expr2;
    private Expr expr3;

    public Choice(Expr expr1, Expr expr2, Expr expr3) {
        this.expr1 = expr1; //Bool
        this.expr2 = expr2;
        this.expr3 = expr3;
    }

    @Override
    public Value eval() {
        B b = (B) expr1.eval();
        Value v1 = expr2.eval();
        Value v2 = expr3.eval();
        return b.v0 ? v1 : v2; //if b true return v1 else return v2
    }
}

class Equal extends Expr { //equality between integers

    private Expr expr1;
    private Expr expr2;

    public Equal(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval() {
        I val1 = (I) expr1.eval();
        I val2 = (I) expr2.eval();
        return new B(val1.v0.equals(val2.v0));
    }
}

class Le extends Expr { //less or equal between integers

    private Expr expr1;
    private Expr expr2;

    public Le(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval() {
        I val1 = (I) expr1.eval();
        I val2 = (I) expr2.eval();
        return new B(val1.v0 >= val2.v0);
    }
}

class VarExp extends Expr {

    private String s;

    public VarExp(String s) {
        this.s = s;
    }

    @Override
    public Value eval() {
        return getValue(s);
    }
}