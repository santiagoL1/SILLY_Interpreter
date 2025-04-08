import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a token in the SILLY language.
 *   Updated to support function calls.
 *   @author Dave Reed
 *   @version 1/20/25
 */
public class Token {
    public static List<String> delims =    Arrays.asList("{", "}", "(", ")", "[", "]");
    public static List<String> booleans =  Arrays.asList("true", "false");
    public static List<String> mathFuncs = Arrays.asList("+", "*", "/");
    public static List<String> boolFuncs = Arrays.asList("==", "!=", ">", ">=", "<", "<=", "and", "or", "not");
    public static List<String> seqFuncs =  Arrays.asList("len", "get", "cat", "str");
    public static List<String> keywords =  Arrays.asList("=", "print", "if", "else", "while", "repeat", "func", "return");

    public static enum Type { UNKNOWN, DELIM, KEYWORD, IDENTIFIER, BOOL_FUNC, MATH_FUNC, SEQ_FUNC,  
                              NUM_LITERAL, BOOL_LITERAL, CHAR_LITERAL, STR_LITERAL, FUNC_CALL } 

    private String strVal;

    /**
     * Constructs a token out of the given string.
     * @param str the string value of the token
     */
    public Token(String str) {
        this.strVal = str;
    }

    /**
     * Identifies what type of token it is.
     * @return the token type (e.g., Token.Type.IDENTIFIER)
     */
    public Token.Type getType() {
        if (Character.isDigit(this.strVal.charAt(0)) || 
            (this.strVal.charAt(0) == '-' && this.strVal.length() > 1 && Character.isDigit(this.strVal.charAt(1)))) {
            try {
                Double.parseDouble(strVal);
                return Token.Type.NUM_LITERAL;
            } catch (Exception e) {
                return Token.Type.UNKNOWN;
            }
        } else if (Token.delims.contains(this.strVal)) {
            return Token.Type.DELIM;
        } else if (Token.keywords.contains(this.strVal)) {
            return Token.Type.KEYWORD;
        } else if (Token.boolFuncs.contains(this.strVal)) {
            return Token.Type.BOOL_FUNC;
        } else if (Token.mathFuncs.contains(this.strVal)) {
            return Token.Type.MATH_FUNC;
        } else if (Token.seqFuncs.contains(this.strVal)) {
            return Token.Type.SEQ_FUNC;
        } else if (Token.booleans.contains(this.strVal)) {
            return Token.Type.BOOL_LITERAL;
        } else if (this.strVal.matches("[a-zA-Z_][a-zA-Z0-9_]*\\(.*\\)")) { 
            return Token.Type.FUNC_CALL;
        } else if (Character.isLetter(this.strVal.charAt(0))) {
            for (int i = 1; i < this.strVal.length(); i++) {
                if (!Character.isLetterOrDigit(this.strVal.charAt(i))) {
                    return Token.Type.UNKNOWN;
                }
            }
            return Token.Type.IDENTIFIER;
        } else if (this.strVal.charAt(0) == '"') {
            if (this.strVal.length() == 1 || this.strVal.charAt(this.strVal.length() - 1) != '"') {
                return Token.Type.UNKNOWN;
            }
            return Token.Type.STR_LITERAL;
        } else if (this.strVal.charAt(0) == '\'') {
            if (this.strVal.length() == 3 && this.strVal.charAt(2) == '\'') { 
                return Token.Type.CHAR_LITERAL;
            } else { 
                if (this.strVal.length() == 1 || this.strVal.charAt(this.strVal.length() - 1) != '\'') {
                    return Token.Type.UNKNOWN;
                }
                return Token.Type.STR_LITERAL;
            }
        } else {
            return Token.Type.UNKNOWN;
        }
    }

    /**
     * Determines when two tokens are identical.
     * @param other the other token being compared
     * @return whether the two tokens represent the same string value
     */
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Token other = (Token) obj;
    return this.strVal.equals(other.strVal);
}

    /**
     * Converts the token to its string representation.
     * @return the string representation
     */
    public String toString() {
        return this.strVal;
    }

    /**
     * Generates a hash code for a Token (based on its String hash code).
     * @return a hash code for the Token
     */
    @Override
public int hashCode() {
    return this.strVal.hashCode();
}
}

