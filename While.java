/**
 * Derived class that represents a while statement in the SILLY language.
 *   @author Dave Reed
 *   @version 1/20/25
 */
public class While extends Statement {
    private Expression expr;
    private Compound body;  
    
    /**
     * Reads in a while statement from the specified stream
     *   @param input the stream to be read from
     */
    public While(TokenStream input) throws Exception {
        if (!input.next().toString().equals("while")) {
            throw new Exception("SYNTAX ERROR: Malformed while statement");
        }
        this.expr = new Expression(input);     
        this.body = new Compound(input);
    }

    /**
     * Executes the current while statement.
     */
    public void execute() throws Exception {
    	boolean keepLooping = true;
        while (keepLooping) {
        	DataValue eVal = this.expr.evaluate();
        	if (eVal.getType() != DataValue.Type.BOOLEAN) {
        		throw new Exception("RUNTIME ERROR: while statement requires Boolean test.");
        	}
            if ((Boolean)eVal.getValue()) {
            	this.body.execute();
            }
            else {
            	keepLooping = false;
            }   
        }
    }

    
    /**
     * Converts the current while statement into a String.
     *   @return the String representation of this statement
     */
    public String toString() {
        return "while " + this.expr + " " + this.body;
    }
}
