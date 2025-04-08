public class CharValue implements DataValue {
    private final char character;

    public CharValue(char character) {
        this.character = character;
    }

    @Override
    public Object getValue() {
        return character;
    }

    @Override
    public DataValue.Type getType() {
        return DataValue.Type.CHAR;
    }

    @Override
    public String toString() {
        return Character.toString(character);
    }

    @Override
    public int compareTo(DataValue other) {
        if (other.getType() != DataValue.Type.CHAR) {
            throw new IllegalArgumentException("Expected a CHAR type.");
        }
        return Character.compare(this.character, ((CharValue) other).character);
    }
}
