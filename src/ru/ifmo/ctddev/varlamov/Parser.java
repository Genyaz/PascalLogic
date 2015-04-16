package ru.ifmo.ctddev.varlamov;

import java.io.InputStream;
import java.util.Queue;

public class Parser {

    private static Node E(Queue<Token> tokens) throws ParseException {
        Node res = new Node(Node.TokenType.E, null);
        res.children.add(A(tokens));
        res.children.add(EPrime(tokens));
        return res;
    }

    private static Node EPrime(Queue<Token> tokens) throws ParseException {
        Node res = new Node(Node.TokenType.EPrime, null);
        switch (tokens.peek().type) {
            case Or:
            case Xor:
                res.children.add(new Node(Node.TokenType.Token, tokens.poll()));
                res.children.add(A(tokens));
                res.children.add(EPrime(tokens));
                break;
            case RightParenthesis:
            case End:
                break;
            default:
                throw new ParseException("Unexpected \"" + tokens.peek().type.name() + "\" token");
        }
        return res;
    }

    private static Node A(Queue<Token> tokens) throws ParseException {
        Node res = new Node(Node.TokenType.A, null);
        res.children.add(N(tokens));
        res.children.add(APrime(tokens));
        return res;
    }

    private static Node APrime(Queue<Token> tokens) throws ParseException {
        Node res = new Node(Node.TokenType.APrime, null);
        switch (tokens.peek().type) {
            case And:
                res.children.add(new Node(Node.TokenType.Token, tokens.poll()));
                res.children.add(N(tokens));
                res.children.add(APrime(tokens));
                break;
            case Or:
            case Xor:
            case RightParenthesis:
            case End:
                break;
            default:
                throw new ParseException("Unexpected \"" + tokens.peek().type.name() + "\" token");
        }
        return res;
    }

    private static Node N(Queue<Token> tokens) throws ParseException {
        Node res = new Node(Node.TokenType.N, null);
        switch (tokens.peek().type) {
            case Not:
                res.children.add(new Node(Node.TokenType.Token, tokens.poll()));
                res.children.add(N(tokens));
                break;
            case Variable:
                res.children.add(new Node(Node.TokenType.Token, tokens.poll()));
                break;
            case LeftParenthesis:
                res.children.add(new Node(Node.TokenType.Token, tokens.poll()));
                res.children.add(E(tokens));
                if (tokens.peek().type != Token.Type.RightParenthesis) {
                    throw new ParseException("Token ) expected, token \"" + tokens.peek().type.name() + "\" found");
                }
                res.children.add(new Node(Node.TokenType.Token, tokens.poll()));
                break;
        }
        return res;
    }

    public static Node parse(InputStream is) throws ParseException {
        Queue<Token> tokens = LexicalAnalyzer.parseTokens(is);
        Node result = E(tokens);
        if (tokens.peek().type == Token.Type.End) {
            return result;
        } else {
            throw new ParseException("Unexpected tokens after the end of expression: " + tokens.peek().type.name());
        }
    }
}
