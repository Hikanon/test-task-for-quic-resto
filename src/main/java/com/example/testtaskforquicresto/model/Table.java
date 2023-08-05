package com.example.testtaskforquicresto.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Table {

    private List<Cell> cells;

    public Table() {
        this.cells = new ArrayList<>();
    }

    public Cell getCell(String cellId) {
        if(cells.isEmpty()) {
            return null;
        }
        return cells.stream()
                .filter(cell ->
                        cell.getCellId().equals(cellId))
                .findFirst()
                .orElse(null);
    }

    public void setCell(Cell newCell) {
        Cell cell = getCell(newCell.getCellId());
        if (cell != null) {
            String value = newCell.getValue();
            cell.setValue(value);
        } else {
            cells.add(newCell);
        }
    }
}
