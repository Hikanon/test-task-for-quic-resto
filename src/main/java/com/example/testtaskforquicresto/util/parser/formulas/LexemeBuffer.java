package com.example.testtaskforquicresto.util.parser.formulas;

import com.example.testtaskforquicresto.model.Lexeme;
import lombok.Data;

import java.util.List;

@Data
public class LexemeBuffer {

    private int position;
    public List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Lexeme next(){
        return lexemes.get(position++);
    }

    public void back(){
        position--;
    }
}
