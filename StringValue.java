import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a string value.
 *  @author Santiago Lizarraga
 *   @version 2/27/25
 */
public class StringValue extends ListValue {
    /**
     * Constructs a string value.
     *   @param str the string being stored
     */
    public StringValue(String str) {
        super(convertToList(str)); 
    }

    /**
     * Converts a string into a list of CharacterValue objects.
     *   @param str the string to convert
     *   @return an ArrayList of CharacterValue objects
     */
    private static ArrayList<DataValue> convertToList(String str) {
        ArrayList<DataValue> charList = new ArrayList<>();
        for (char c : str.toCharArray()) {
            charList.add(new CharValue(c)); 
        }
        return charList;
    }

    /**
     * Identifies the actual type of the value.
     *   @return DataValue.Type.STRING
     */
    @Override
    public DataValue.Type getType() {
        return DataValue.Type.STRING;
    }

    /**
     * Converts the stored list of characters back into a string.
     *   @return a String representation of the stored value
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DataValue v : (List<DataValue>) getValue()) {
            sb.append(v.toString()); 
        }
        return sb.toString();
    }
    
 
}

