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

async function handle(target) {

    let cell = new Cell(target.id, target.value);
    let calculatedTable = await calculate(cell);
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

async function setValue(target) {

    let value = await getCellValue(target);
    if (value !== undefined) {
        target.value = value;
    } else {
        target.value = '';
    }

}

async function getCellValue(target) {
    let cell = new Cell(target.id, target.value);
    return fetch("/api/cellValue?cellId=" + cell.cellId)
        .then((response) => {
            return response
                .text()
                .then((data) => {
                    console.log(data);
                    return data.toString();
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

    // cells.then(data => {
        for (let cell of cells) {
            let elementId = cell.cellId;
            let element = document.getElementById(elementId);
            element.value = cell.value;
        }
    // });
}

async function main() {
    let table = await getTable();
    fillTable(table);
}

main().then();