/**
* Class that represents a return statement in the SILLY language.
 * A return statement evaluates an expression and stores the result
 * in the current function scope under the special variable "__return__".
 *  @author Santiago Lizarraga
 *  @version 3/27/25
 */

public class Return extends Statement {
    private Expression expr;

    public Return(TokenStream input) throws Exception {
        this.expr = new Expression(input);
    }

    @Override
public void execute() throws Exception {
    DataValue value = this.expr.evaluate();
    Token returnToken = new Token("__return__");
    ScopeRec currentScope = Interpreter.MEMORY.getCurrentScope();
    currentScope.storeInScope(returnToken, value);
    throw new ReturnException();
}
    public String toString() {
        return "return " + expr;
    }
}











