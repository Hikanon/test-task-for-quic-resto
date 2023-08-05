class Table {

    cells

    constructor(cells) {
        this.cells = cells;
    }

    get cells() {
        return this.cells;
    }
}


class Cell {

    cellId;
    value;

    constructor(cellId, value) {

        this.cellId = cellId;
        this.value = value;
    }
}

function handleKeyUp(event) {

    if (event.keyCode === 13) {
        event.target.blur();
    }
}

function handle(target) {

    let cell = new Cell(target.id, target.value);
    let calculatedTable = calculate(cell);
    fillTable(calculatedTable);
}

function calculate(cell) {

    return fetch("/api/calculate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(cell)
    }).then((response) => {
        return response.json()
            .then((data) => {
                console.log(data);
                return data.cells;
            }).catch((err) => {
                console.log(err);
            })
    });
}

function getTable() {

    return fetch("/api/table")
        .then((response) => {
            return response.json()
                .then((data) => {
                    console.log(data);
                    return data.cells;
                }).catch((err) => {
                    console.log(err);
                })
        });
}

function fillTable(cells) {

    cells.then(data => {
        for (let cell of data) {
            let elementId = cell.cellId;
            let element = document.getElementById(elementId);
            element.value = cell.value;
        }
    });
}

let table = getTable();
fillTable(table);
