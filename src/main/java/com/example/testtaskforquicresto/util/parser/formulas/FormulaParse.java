package com.example.testtaskforquicresto.util.parser.formulas;

import com.example.testtaskforquicresto.enums.LexemeType;
import com.example.testtaskforquicresto.model.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class FormulaParse {

    public static double calculate(String expression) {
        try {
            List<Lexeme> lexemes = lexemeAnalyze(expression);
            LexemeBuffer buffer = new LexemeBuffer(lexemes);
            return expression(buffer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + " Invalid enter");
        }

    }

    public static List<Lexeme> lexemeAnalyze(String expression) {
        List<Lexeme> lexemes = new ArrayList<>();
        int position = 0;
        while (position < expression.length()) {
            char c = expression.charAt(position);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    position++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    position++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.PLUS, c));
                    position++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.MINUS, c));
                    position++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.MUL, c));
                    position++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.DIV, c));
                    position++;
                    continue;
                default:
                    if ((c <= '9' && c >= '0')) {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            position++;
                            if (position >= expression.length()) {
                                break;
                            }
                            c = expression.charAt(position);
                        } while (c <= '9' && c >= '0' || c == '.');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            throw new NumberFormatException("Wrong char " + c);
                        }
                        position++;
                    }

            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static double expression(LexemeBuffer buffer) {
        Lexeme lexeme = buffer.next();
        if (lexeme.getType() == LexemeType.EOF) {
            return 0;
        } else {
            buffer.back();
            return plusMinus(buffer);
        }
    }

    private static double plusMinus(LexemeBuffer buffer) {
        double value = mulDiv(buffer);
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.getType()) {
                case PLUS:
                    value += mulDiv(buffer);
                    break;
                case MINUS:
                    value -= mulDiv(buffer);
                    break;
                default:
                    buffer.back();
                    return value;
            }
        }
    }

    private static double mulDiv(LexemeBuffer buffer) {
        double value = factor(buffer);
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.getType()) {
                case MUL:
                    value *= factor(buffer);
                    break;
                case DIV:
                    value /= factor(buffer);
                    break;
                default:
                    buffer.back();
                    return value;
            }
        }
    }

    private static double factor(LexemeBuffer buffer) {
        Lexeme lexeme = buffer.next();
        double value;
        switch (lexeme.getType()) {
            case MINUS:
                value = factor(buffer);
                return -value;
            case NUMBER:
                return Double.parseDouble(lexeme.getValue());
            case LEFT_BRACKET:
                value = expression(buffer);
                lexeme = buffer.next();
                if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                    throw new NumberFormatException("Missing right bracket at position " + buffer.getPosition());
                }
                return value;
            default:
                throw new NumberFormatException("Syntax error at position " + buffer.getPosition());
        }
    }

}
