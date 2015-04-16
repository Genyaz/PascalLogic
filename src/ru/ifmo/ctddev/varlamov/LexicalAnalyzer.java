package ru.ifmo.ctddev.varlamov;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class LexicalAnalyzer {

    public static Queue<Token> parseTokens(InputStream is) throws ParseException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        Queue<Token> result = new ArrayDeque<Token>();
        String line;
        try {
            while ((line = in.readLine()) != null) {
                for (String s: line.split("\\s")) {
                    if (!s.isEmpty()) {
                        int prevParenthesis = -1;
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                                if (prevParenthesis < i - 1) {
                                    result.add(TokenFactory.build(s.substring(prevParenthesis + 1, i)));
                                }
                                prevParenthesis = i;
                                result.add(TokenFactory.build(s.substring(i, i + 1)));
                            }
                        }
                        if (prevParenthesis < s.length() - 1) {
                            result.add(TokenFactory.build(s.substring(prevParenthesis + 1, s.length())));
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new ParseException("IOException occurred:\"" + e.getLocalizedMessage() + "\"");
        }
        result.add(new Token(Token.Type.End));
        return result;
    }
}
