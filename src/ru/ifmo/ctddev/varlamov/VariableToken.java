package ru.ifmo.ctddev.varlamov;

public class VariableToken extends Token {
    public char variableName;

    public VariableToken(String variableName) throws ParseException {
        super(Type.Variable);
        if (variableName.length() != 1 || !Character.isLetter(variableName.charAt(0))) {
            throw new ParseException("String \"" + variableName + "\" is not an operation or correct variable name");
        }
        this.variableName = variableName.charAt(0);
    }
}
