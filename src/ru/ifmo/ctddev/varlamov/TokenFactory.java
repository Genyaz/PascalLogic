package ru.ifmo.ctddev.varlamov;

public class TokenFactory {
    public static Token build(String s) throws ParseException {
        if (s.equals("and")) {
            return new Token(Token.Type.And);
        } else if (s.equals("or")) {
            return new Token(Token.Type.Or);
        } else if (s.equals("xor")) {
            return new Token(Token.Type.Xor);
        } else if (s.equals("not")) {
            return new Token(Token.Type.Not);
        } else if (s.equals("(")) {
            return new Token(Token.Type.LeftParenthesis);
        } else if (s.equals(")")) {
            return new Token(Token.Type.RightParenthesis);
        } else {
            return new VariableToken(s);
        }
    }
}
