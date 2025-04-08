/**
 * Class that represents a function declaration in the SILLY language.
 * A function is defined with a name, a list of parameters, and a body 
 * consisting of one or more statements. 
 * @author Santiago Lizarraga
 * @version 3/27/25
 */



import java.util.List;
import java.util.ArrayList;

public class FunctionDecl extends Statement {
    private final String name;
    private final List<Statement> body;
    private final List<String> parameters;

    public FunctionDecl(String name, List<String> parameters, List<Statement> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    // Constructor that parses func declarations from input
    public FunctionDecl(TokenStream input) throws Exception {
        Token idTok = input.next();  
        if (idTok.getType() != Token.Type.IDENTIFIER) {
            throw new Exception("SYNTAX ERROR: Function name expected after 'func'");
        }
        this.name = idTok.toString();
        if (!input.next().toString().equals("(")) {
            throw new Exception("SYNTAX ERROR: '(' expected after function name");
        }

        this.parameters = new ArrayList<>();
        while (!input.lookAhead().toString().equals(")")) {
            Token paramTok = input.next();
            if (paramTok.getType() != Token.Type.IDENTIFIER) {
                throw new Exception("SYNTAX ERROR: Parameter names must be identifiers.");
            }
            this.parameters.add(paramTok.toString());
        }

        input.next();  
        Compound compound = new Compound(input);
        this.body = new ArrayList<>(compound.getStatements());

        Interpreter.MEMORY.declareFunction(this.name, this);
    }

    @Override
    public void execute() throws Exception {

    }

    public List<Statement> getBody() {
        return body;
    }

    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "FunctionDecl{name='" + name + "', parameters=" + parameters + ", body=" + body + '}';
    }
}













