package ru.ifmo.ctddev.varlamov.test;

import org.junit.Test;
import ru.ifmo.ctddev.varlamov.ParseException;
import ru.ifmo.ctddev.varlamov.Parser;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testFromExample() throws ParseException {
        String s = "(a and b) or not (c xor (a or not b))";
        try {
            Parser.parse(new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8)));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test (expected = ParseException.class)
    public void missedRightParenthesis() throws ParseException {
        String s = "(a and b) or not (c xor (a or not b)";
        Parser.parse(new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8)));
    }

    @Test (expected = ParseException.class)
    public void missedLeftParenthesis() throws ParseException {
        String s = "a and b) or not (c xor (a or not b))";
        Parser.parse(new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8)));
    }

}