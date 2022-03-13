/**
 * Solution for tasks 4.3.1 together with:
 * @see pipl.Stmt
 * Also solution for task 4.8.1 1 & 2
 * @author: Jørgen Lohne
 * @since 2022-03-06
 * */

package pipl;

public abstract class Expr {
    public Expr() {
    }

    public abstract Value eval(State state);
}

class IL extends Expr {
    private final int value;

    public IL(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Value eval(State state) {
        return new I(value);
    }
}

class Plus extends Expr {
    private final Expr lhs, rhs;

    public Plus(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        I i1 = (I) lhs.eval(state);
        I i2 = (I) rhs.eval(state);
        return new I(i1.v0 + i2.v0);
    }
}

class Mult extends Expr {
    private final Expr lhs, rhs;

    public Mult(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        I i1 = (I) lhs.eval(state);
        I i2 = (I) rhs.eval(state);
        return new I(i1.v0 * i2.v0);
    }
}

class Uminus extends Expr {
    private final Expr value;

    public Uminus(Expr value) {
        this.value = value;
    }

    @Override
    public Value eval(State state) {
        I neg = (I) value.eval(state);
        return new I(-neg.v0);
    }
}

class BL extends Expr {
    private final boolean value;

    public BL(boolean value) {
        this.value = value;
    }

    @Override
    public Value eval(State state) {
        return new B(value);
    }
}

class Or extends Expr {
    private final Expr lhs, rhs;

    public Or(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        B b1 = (B) lhs.eval(state);
        B b2 = (B) rhs.eval(state);
        return new B(b1.v0 || b2.v0);
    }
}

class And extends Expr {
    private final Expr lhs, rhs;

    public And(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        B b1 = (B) lhs.eval(state);
        B b2 = (B) rhs.eval(state);
        return new B(b1.v0 && b2.v0);
    }
}

class Not extends Expr {
    private final Expr expr;

    public Not(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Value eval(State state) {
        B not = (B) expr.eval(state);
        return new B(!not.v0);
    }
}

class Choice extends Expr {
    private final Expr cond, e1, e2;

    public Choice(Expr cond, Expr e1, Expr e2) {
        this.cond = cond;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Value eval(State state) {
        B condition = (B) cond.eval(state);
        return condition.v0 ? e1.eval(state) : e2.eval(state);
    }
}

class Equal extends Expr {
    private final Expr lhs, rhs;

    public Equal(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        I i1 = (I) lhs.eval(state);
        I i2 = (I) rhs.eval(state);
        return new B(i1.v0.equals(i2.v0));
    }
}

class Le extends Expr {
    private final Expr lhs, rhs;

    public Le(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        I i1 = (I) lhs.eval(state);
        I i2 = (I) rhs.eval(state);
        return new B(i1.v0 <= i2.v0);
    }
}

class VarExp extends Expr {
    private final String name;

    public VarExp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Value eval(State state) {
        return state.getValue(name);
    }
}

class IntDiv extends Expr { //Integer division
    private final Expr lhs, rhs;

    public IntDiv(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        I i1 = (I) lhs.eval(state);
        I i2 = (I) rhs.eval(state);
        return new I(i1.v0 / i2.v0);
    }
}

class Mod extends Expr { //Modulus
    private final Expr lhs, rhs;

    public Mod(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Value eval(State state) {
        I i1 = (I) lhs.eval(state);
        I i2 = (I) rhs.eval(state);
        return new I(i1.v0 % i2.v0);
    }
}