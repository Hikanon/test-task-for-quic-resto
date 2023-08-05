package com.example.testtaskforquicresto.model;

import com.example.testtaskforquicresto.enums.LexemeType;
import lombok.Data;

@Data
public class Lexeme {

    private LexemeType type;
    private String value;

    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }
}

