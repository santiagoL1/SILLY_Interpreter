import java.util.ArrayList;
import java.util.List;

/**
 * Derived class that represents a compound statement in the SILLY language.
 *   @author Dave Reed
 *   @version 1/20/25
 */
public class Compound extends Statement {
    private ArrayList<Statement> stmts;

    /**
     * Reads in a compound statement from the specified stream
     *   @param input the stream to be read from
     */
    public Compound(TokenStream input) throws Exception {
        if (!input.next().toString().equals("{")) {
            throw new Exception("SYNTAX ERROR: Malformed compound statement");
        }

        this.stmts = new ArrayList<Statement>();
        while (!input.lookAhead().toString().equals("}")) {
            this.stmts.add(Statement.getStatement(input));
        }
        input.next();
    }

    @Override
    public void execute() throws Exception {
        Interpreter.MEMORY.beginNestedScope();
    
        try {
            for (Statement stmt : this.stmts) {
                stmt.execute();
            }
            Interpreter.MEMORY.endCurrentScope(); 
        } catch (ReturnException e) {
            throw e;
        }
    }

    

    
    /**
     * Converts the current compound statement into a String.
     *   @return the String representation of this statement
     */
    public String toString() {
        String str = "{\n";
        for (Statement stmt : this.stmts) {
            str += "  " + stmt + "\n";
        }
        return str + "}";
    }
    public List<Statement> getStatements() {
    return this.stmts;
}

}
