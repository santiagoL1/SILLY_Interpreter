public class Repeat extends Statement {
    private final Expression repetitionCount;
    private final Statement action;

    public Repeat(TokenStream tokens) throws Exception {
        tokens.next();
        repetitionCount = new Expression(tokens);
        action = Statement.getStatement(tokens);
    }

    @Override
    public void execute() throws Exception {
        DataValue evalResult = repetitionCount.evaluate();

        if (evalResult.getType() != DataValue.Type.NUMBER) {
            throw new Exception("Runtime Error: Repeat statement requires a numeric expression.");
        }

        double evaluatedValue = (Double) evalResult.getValue();

        if (evaluatedValue != Math.floor(evaluatedValue)) {
            throw new Exception("Runtime Error: Repeat count must be an integer value.");
        }

        int repetitions = (int) evaluatedValue;

        for (int i = 0; i < repetitions; i++) {
            action.execute();
        }
    }

    @Override
    public String toString() {
        return "repeat " + repetitionCount.toString() + " " + action.toString();
    }
}







