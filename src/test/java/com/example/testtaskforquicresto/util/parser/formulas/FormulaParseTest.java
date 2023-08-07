package com.example.testtaskforquicresto.util.parser.formulas;

import com.example.testtaskforquicresto.enums.LexemeType;
import com.example.testtaskforquicresto.model.Lexeme;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormulaParseTest {


    @Test
    public void testLexemeAnalyze () {
        String expression = "15+ 5+4.2/45    -7 /7 (4/8) *   2";

        List<Lexeme> test = new ArrayList<>();
        test.add(new Lexeme(LexemeType.NUMBER, "15"));
        test.add(new Lexeme(LexemeType.PLUS, "+"));
        test.add(new Lexeme(LexemeType.NUMBER, "5"));
        test.add(new Lexeme(LexemeType.PLUS, "+"));
        test.add(new Lexeme(LexemeType.NUMBER, "4.2"));
        test.add(new Lexeme(LexemeType.DIV, "/"));
        test.add(new Lexeme(LexemeType.NUMBER, "45"));
        test.add(new Lexeme(LexemeType.MINUS, "-"));
        test.add(new Lexeme(LexemeType.NUMBER, "7"));
        test.add(new Lexeme(LexemeType.DIV, "/"));
        test.add(new Lexeme(LexemeType.NUMBER, "7"));
        test.add(new Lexeme(LexemeType.LEFT_BRACKET, "("));
        test.add(new Lexeme(LexemeType.NUMBER, "4"));
        test.add(new Lexeme(LexemeType.DIV, "/"));
        test.add(new Lexeme(LexemeType.NUMBER, "8"));
        test.add(new Lexeme(LexemeType.RIGHT_BRACKET, ")"));
        test.add(new Lexeme(LexemeType.MUL, "*"));
        test.add(new Lexeme(LexemeType.NUMBER, "2"));
        test.add(new Lexeme(LexemeType.EOF, ""));

        List<Lexeme> lexemes = FormulaParse.lexemeAnalyze(expression);
        assertArrayEquals(lexemes.toArray(), test.toArray());

    }

    @Test
    void expression() {
        String expression = "2+2 *  -2";
        List<Lexeme> test = FormulaParse.lexemeAnalyze(expression);
        LexemeBuffer buffer = new LexemeBuffer(test);
        double result = FormulaParse.expression(buffer);
        assertEquals(result, -2.0);
    }

}