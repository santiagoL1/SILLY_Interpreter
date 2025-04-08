/**
 * Class that represents a number value.
 *   @author Dave Reed
 *   @version 1/20/25
 */
public class NumberValue implements DataValue {
    protected Double value;

    /**
     * Constructs a default number value (0).
     */
    public NumberValue() {
        this(0);
    }
    
    /**
     * Constructs a number value.
     *   @param num the number being stored
     */
    public NumberValue(double num) {
        this.value = (Double) num;
    }

    /**
     * Accesses the stored number value.
     *   @return the number value (as an Object)
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Identifies the actual type of the value.
     *   @return Token.Type.NUMBER
     */
    public DataValue.Type getType() {
        return DataValue.Type.NUMBER;
    }

    /**
     * Converts a number value to a String.
     *   @return a String representation of a number value
     */
    public String toString() {
    	if (this.value == Math.round(this.value)) {
    		return "" + (int)(this.value.doubleValue());
    	}
        return "" + this.value;
    }

    /**
     * Comparison method for NumberValues.
     *   @param other the value being compared with
     *   @return negative if <, 0 if ==, positive if >
     */
    public int compareTo(DataValue other) {
         return ((Double)this.getValue()).compareTo((Double)other.getValue());
    }
}