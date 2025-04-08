/**
 * Derived class that represents an if statement in the SILLY language.
 *   @author Dave Reed
 *   @version 1/20/25
 */
public class If extends Statement {
    private Expression test;
    private Compound ifBody;
    private Compound elseBody;

    /**
     * Reads in an if statement from the specified stream
     *   @param input the stream to be read from
     */
    public If(TokenStream input) throws Exception {
        if (!input.next().toString().equals("if")) {
            throw new Exception("SYNTAX ERROR: Malformed if statement");
        }
        this.test = new Expression(input);
        this.ifBody = new Compound(input);
        
        if (!input.next().toString().equals("else")) {
        	throw new Exception("SYNTAX ERROR: Malformed if statement");
        }
        
        this.elseBody = new Compound(input);
    }

    /**
     * Executes the current if statement.
     */
    public void execute() throws Exception {
        DataValue test = this.test.evaluate();
        if (test.getType() != DataValue.Type.BOOLEAN) {
            throw new Exception("RUNTIME ERROR: If statement requires Boolean test.");
        } 
        
        if (((Boolean) test.getValue())) {
            this.ifBody.execute();
        } 
        else {
            this.elseBody.execute();
        }
    }

    /**
     * Converts the current if statement into a String.
     *   @return the String representation of this statement
     */
    public String toString() {
    	return "if " + this.test + " " + this.ifBody + "\nelse " + this.elseBody;
    }
}
