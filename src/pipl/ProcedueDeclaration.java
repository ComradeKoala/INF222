/**
 * Also solution for task 4.4.6
 *
 * @author: Jørgen Lohne
 * @since 2022-03-06
 */

package pipl;

import java.util.ArrayList;
import java.util.List;

class ProcedureDeclaration {
    private final String name; //Name of procedure
    private final List<Parameter> parameters; //Parameter
    private final List<VarDecl> localVars; // Local variable
    private final Stmt stmt; // Procedure body (statement)

    public ProcedureDeclaration(String name, List<Parameter> parameters, List<VarDecl> localVars, Stmt stmt, State state) {
        this.name = name;
        this.parameters = parameters;
        this.localVars = localVars;
        this.stmt = stmt;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public List<VarDecl> getLocalVars() {
        return localVars;
    }

    public Stmt getStmt() {
        return stmt;
    }

    public static List<Integer> eucliddiv_proc(int x, int y) {
        List<Parameter> parameters = new ArrayList<>();
        List<VarDecl> varDecls = new ArrayList<>();

        parameters.add(new Parameter(Mode.OBS, new VarDecl(new Var("x"), Type.INTEGERTYPE)));
        parameters.add(new Parameter(Mode.OBS, new VarDecl(new Var("y"), Type.INTEGERTYPE)));
        parameters.add(new Parameter(Mode.OUT, new VarDecl(new Var("q"), Type.INTEGERTYPE)));
        parameters.add(new Parameter(Mode.OUT, new VarDecl(new Var("r"), Type.INTEGERTYPE)));


        State state = new State();
        List<Value> values = new ArrayList<>();
        values.add(new I(x));
        values.add(new I(y));

        ProcedureDeclaration eucliddiv = new ProcedureDeclaration("eucliddiv", parameters, varDecls, Examples.eucliddiv_stmt, new State());

        List<Value> returnValues = state.run(eucliddiv, values);

        //convert return values to java ints

        I r = (I) returnValues.get(1);
        I q = (I) returnValues.get(0);
        int qInt = q.v0;
        int rInt = r.v0;

        List<Integer> rList = new ArrayList<>();
        rList.add(qInt);
        rList.add(rInt);
        return rList;
    }

    public static void main(String[] args) {
        System.out.println(eucliddiv_proc(10, 3));
    }

}

class Parameter {
    private Mode mode;
    private VarDecl varDecl;

    public Parameter(Mode mode, VarDecl varDecl) {
        this.mode = mode;
        this.varDecl = varDecl;
    }

    public Mode getMode() {
        return mode;
    }

    public VarDecl getVarDecl() {
        return varDecl;
    }
}

class VarDecl {
    private Var var;
    private Type type;

    public VarDecl(Var var, Type type) {
        this.var = var;
        this.type = type;
    }

    public Var getVar() {
        return var;
    }

    public Type getType() {
        return type;
    }
}

enum Type {
    UNKNOWN,
    INTEGERTYPE,
    BOOLTYPE,
    LOCATIONTYPE
}

enum Mode {
    OBS,
    UPD,
    OUT
}

class Var {
    String var;

    public Var(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

}

