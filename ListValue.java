import java.util.List;
import java.util.ArrayList;

/**
 * Class that represents a list value.
 *   @author Dave Reed
 *   @version 1/20/25
 */
public class ListValue implements DataValue {
    protected List<DataValue> value;

    /**
     * Constructs a default list value (empty list).
     */
    public ListValue() {
        this.value = new ArrayList<DataValue>();
    }

    /**
     * Constructs a list value.
     *   @param vals the list values being stored
     */
    public ListValue(ArrayList<DataValue> vals) {
        this();
        for (DataValue v : vals) {
            this.value.add(v);
        }
    }
    
    /**
     * Accesses the stored list value.
     *   @return the list value (as an Object)
     */
    public Object getValue() {
        return this.value;
    }  

    /**
     * Identifies the actual type of the value.
     *   @return Token.Type.LIST
     */
    public DataValue.Type getType() {
        return DataValue.Type.LIST;
    }

    /**
 * Converts the list value to a String.
 *   @return a String representation of the list value
 */
public String toString() {
    StringBuilder message = new StringBuilder();
    for (DataValue v : this.value) {
        message.append(v);
    }
    return message.toString();
}

    /**
     * Comparison method for ListValues.
     *   @param other the value being compared with
     *   @return negative if <, 0 if ==, positive if >
     */
    public int compareTo(DataValue other) {
        return (this.getValue().toString()).compareTo(other.getValue().toString());
    }

    /**
     * Returns the size of the list.
     *   @return the number of elements in the list
     */
    public int size() {
        return this.value.size();
    }

    /**
     * Retrieves an element at a specific index.
     *   @param index the index of the element
     *   @return the DataValue at the specified index
     */
    public DataValue get(int index) {
        return this.value.get(index);
    }

    /**
     * Concatenates another ListValue.
     *   @param other the ListValue to concatenate
     */
    public void addAll(ListValue other) {
        this.value.addAll(other.value);
    }
}