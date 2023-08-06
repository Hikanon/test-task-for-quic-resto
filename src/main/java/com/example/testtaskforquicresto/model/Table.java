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
        if (cells.isEmpty()) {
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
        String newCellValue = newCell.getValue();

        if (cell != null) {
            if (newCellValue.startsWith("=")) {
                cell.setExpression(true);
            }
            String value = newCell.getValue();
            cell.setValue(value);
        } else {
            newCell.setExpression(true);
            cells.add(newCell);
        }
    }
}
