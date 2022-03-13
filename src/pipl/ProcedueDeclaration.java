/**
 * Also solution for task 4.4.6
 * @author: Jørgen Lohne
 * @since 2022-03-06
 * */

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

    public static void main(String[] args) {
        List<Parameter> parameters = new ArrayList<>();
        List<VarDecl> varDecls = new ArrayList<>();

        parameters.add(new Parameter(Mode.OBS, new VarDecl(new Var("x"), Type.INTEGERTYPE)));
        parameters.add(new Parameter(Mode.OBS, new VarDecl(new Var("y"), Type.INTEGERTYPE)));
        parameters.add(new Parameter(Mode.OBS, new VarDecl(new Var("q"), Type.INTEGERTYPE)));
        parameters.add(new Parameter(Mode.OBS, new VarDecl(new Var("r"), Type.INTEGERTYPE)));

        ProcedureDeclaration eucliddiv_proc = new ProcedureDeclaration("eucliddiv", parameters, varDecls, Examples.eucliddiv_stmt, new State());

        State state = new State();
        List<Value> values = new ArrayList<>();
        values.add(new I(10));
        values.add(new I(3));

        System.out.println(state.run(eucliddiv_proc, values));
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

