package ru.ifmo.ctddev.varlamov;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public enum TokenType {
        E,
        EPrime,
        A,
        APrime,
        N,
        Token,
    }

    public List<Node> children;
    public Token token;
    public TokenType tokenType;

    public Node(TokenType tokenType, Token token) {
        this.tokenType = tokenType;
        this.token = token;
        this.children = new ArrayList<Node>();
    }
}
