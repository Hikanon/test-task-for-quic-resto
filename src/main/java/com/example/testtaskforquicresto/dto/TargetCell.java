package com.example.testtaskforquicresto.dto;

import com.example.testtaskforquicresto.model.Cell;
import com.example.testtaskforquicresto.model.Table;
import lombok.Data;

@Data
public class TargetCell {

    private Cell cell;
    private Table table;

}
