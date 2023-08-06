package com.example.testtaskforquicresto.model;


import lombok.Data;

//TODO: Добавить isExpression для хранения выражений
@Data
public class Cell {

    private String cellId;
    private String value;
    private boolean expression;

    public Cell(String cellId, String value) {
        this.cellId = cellId;
        this.value = value;
        this.expression = false;
    }

    public void setValue(String value) {
        expression = value.startsWith("=");
        this.value = value;
    }
}
