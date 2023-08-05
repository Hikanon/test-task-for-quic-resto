package com.example.testtaskforquicresto.model;


import lombok.Data;

@Data
public class Cell {

    private String cellId;
    private String value;

    public Cell(String cellId, String value) {
        this.cellId = cellId;
        this.value = value;
    }
}
