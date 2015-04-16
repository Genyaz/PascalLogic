package ru.ifmo.ctddev.varlamov;

public class Token {
    public enum Type {
        LeftParenthesis,
        RightParenthesis,
        Not,
        And,
        Or,
        Xor,
        Variable,
        End,
    }

    public Type type;

    public Token(Type type) {
        this.type = type;
    }
}
