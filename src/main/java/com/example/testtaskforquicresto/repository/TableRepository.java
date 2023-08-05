package com.example.testtaskforquicresto.repository;


import com.example.testtaskforquicresto.model.Cell;
import com.example.testtaskforquicresto.model.Table;
import org.springframework.stereotype.Repository;
/**
 * Заглушка
*/
@Repository
public class TableRepository {

    private final Table table;

    public TableRepository() {
        this.table = new Table();
    }

    public void saveCell(Cell cell) {
        table.setCell(cell);
    }
    public Table getTable() {
        return table;
    }
}
