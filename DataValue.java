/**
 * Interface that defines the data types for the SILLY language. 
 *   @author Dave Reed
 *   @version 1/20/25
 */
public interface DataValue extends Comparable<DataValue> {   
    public static enum Type { NUMBER, BOOLEAN, LIST, CHAR, STRING}
    
    public Object getValue();
    public DataValue.Type getType();
    public String toString(); 
}