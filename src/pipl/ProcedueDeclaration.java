/**
 * Also solution for task 4.4.6
 * @author: Jørgen Lohne
 * @since 2022-03-06
 * */

package pipl;

import java.util.List;

public class ProcedueDeclaration {
    public final String name;
    public final List<String> parameters;
    public final List<VarExp> values;
    public final Stmt stmt; // ??

    public ProcedueDeclaration(String name, List<String> parameters, List<VarExp> values, Stmt stmt) {
        this.name = name;
        this.parameters = parameters;
        this.values = values;
        this.stmt = stmt;
    }





}
