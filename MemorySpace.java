import java.util.Stack;
import java.util.HashMap;

/**
 * Class that defines the memory space for the SILLY interpreter.
 *   Updated to support function declarations.
 *   @author Dave Reed
 *   @version 1/20/25
 */
public class MemorySpace {
    private Stack<ScopeRec> runtimeStack;
    private HashMap<String, FunctionDecl> functionTable; 

    /**
     * Constructs a memory space with a single (global) scope record.
     */
    public MemorySpace() {
        this.runtimeStack = new Stack<>();
        this.runtimeStack.push(new ScopeRec(null));
        this.functionTable = new HashMap<>(); // Initialize function storage
    }
    
    /**
     * Adds a new scope to the top of the runtime stack (linked to previous top).
     */
    public void beginNestedScope() {
        this.runtimeStack.push(new ScopeRec(this.runtimeStack.peek()));
    }
    
    /**
     * Removes the current scope from the top of the runtime stack.
     */
    public void endCurrentScope() {
        if (this.runtimeStack.size() > 1) {
            this.runtimeStack.pop();
        }
    }
    
    

    /**
     * Declares a variable (without storing an actual value).
     * @param variable the variable to be declared
     */
    public void declareVariable(Token variable) {
        this.runtimeStack.peek().storeInScope(variable, null);
    }
    
    /**
     * Determines if a variable is already declared.
     * @param variable the variable to be found
     * @return true if it is declared and/or assigned; else, false
     */
    public boolean isDeclared(Token variable) {
        return (this.findScopeinStack(variable) != null);
    }
    
    /**
     * Stores a variable/value in the runtime stack.
     * @param variable the variable name
     * @param val the value to be stored under that name
     */
    public void storeValue(Token variable, DataValue val) {
        ScopeRec top = this.runtimeStack.peek();
    
        if (top.declaredInScope(variable)) {
            top.storeInScope(variable, val);  
        } else {
            top.storeInScope(variable, val);  
        }
    }
    
    
    

    /**
     * Determines the value associated with a variable in memory.
     * @param variable the variable to look up
     * @return the value associated with that variable
     */      
    public DataValue lookupValue(Token variable) {
        ScopeRec scope = this.findScopeinStack(variable);
        if (scope == null) {
            throw new RuntimeException("RUNTIME ERROR: Variable '" + variable + "' not declared.");
        }
        return scope.lookupInScope(variable);
    }
    

    /**
     * Declares a function and stores it in the function table.
     * @param name the function name
     * @param function the function declaration
     * @throws Exception if a function or variable with the same name already exists
     */
    public void declareFunction(String name, FunctionDecl function) throws Exception {
        if (functionTable.containsKey(name) || isDeclared(new Token(name))) {
            throw new Exception("RUNTIME ERROR: Function or variable '" + name + "' already exists.");
        }
        functionTable.put(name, function);
    }

    /**
     * Stores a function declaration in memory.
     * @param name the function name
     * @param function the function declaration
     */
    public void storeFunction(String name, FunctionDecl function) {
        functionTable.put(name, function);
    }

    /**
     * Retrieves a function declaration from memory.
     * @param name the function name
     * @return the function declaration, or null if not found
     */
    public FunctionDecl lookupFunction(String name) {
        return functionTable.get(name);
    }

    /**
     * Locates the Scope in the stackSegment that contains the specified variable.
     * @param variable the variable being searched for
     * @return the Scope containing that variable
     */
    private ScopeRec findScopeinStack(Token variable) {
        ScopeRec stepper = this.runtimeStack.peek();
        while (stepper != null && !stepper.declaredInScope(variable)) {
            stepper = stepper.getParentScope();
        }
        return stepper;
    }
    public ScopeRec getCurrentScope() {
        return this.runtimeStack.peek();
    }
    
}




