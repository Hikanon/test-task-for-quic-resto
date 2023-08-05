package com.example.testtaskforquicresto.service;


import com.example.testtaskforquicresto.model.Cell;
import com.example.testtaskforquicresto.model.Table;
import com.example.testtaskforquicresto.repository.TableRepository;
import com.example.testtaskforquicresto.util.parser.formulas.FormulaParse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public void saveCell(Cell cell) {
        tableRepository.saveCell(cell);
    }

    public Table getTable() {
        return tableRepository.getTable();
    }

    public Table calculate(Cell cell) {
        Table tempTable = new Table();
        Table table = tableRepository.getTable();
        table.setCell(cell);
        List<Cell> cells = table.getCells();
        cells.forEach(c -> {
            Cell calculateCell = calculateCell(c);
            tempTable.setCell(calculateCell);
        });
        return tempTable;
    }

    private Cell calculateCell(Cell cell) {
        Cell tempCell = new Cell(cell.getCellId(), cell.getValue());
        String value = tempCell.getValue();
        if (value.startsWith("=")) {
            try {
                value = replaceCellsIdToValue(value.substring(1));
                value = String.valueOf(FormulaParse.calculate(value));
                tempCell.setValue(value);
            } catch (Exception e) {
                tempCell.setValue("Error");
            }
        }
        return tempCell;
    }

    private String replaceCellsIdToValue(String value) {
        Table table = tableRepository.getTable();
        Pattern regex = Pattern.compile("[A-Z][0-9]");
        Matcher matcher = regex.matcher(value);
        while (matcher.find()) {
            String group = matcher.group();
            Cell matchCell = table.getCell(group);
            if (matchCell == null) {
                throw new IllegalStateException("Empty cell with id" + group);
            }
            value = value.replace(group, matchCell.getValue());
        }

        return value;
    }
}
