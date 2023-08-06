package com.example.testtaskforquicresto.controller;


import com.example.testtaskforquicresto.model.Cell;
import com.example.testtaskforquicresto.model.Table;
import com.example.testtaskforquicresto.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/cellValue")
    public ResponseEntity<String> getCellValue(@RequestParam(value = "cellId") String cellId) {
        String value = tableService.getCellValueById(cellId);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @GetMapping("/table")
    public ResponseEntity<Table> getTable() {
        Table table = tableService.getTable();
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @PostMapping("/calculate")
    private ResponseEntity<Table> calculate(@RequestBody Cell cell) {
        tableService.saveCell(cell);
        Table table = tableService.calculate(cell);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

}
