package gameoflife;

import java.util.Random;

public class Grid {
    private Cell[][] cells;                 // cells array
    private int sizeGrid;                   //size of cells array
    private Random rd;
    public String display = "";             // string representation cells array
    public Cell[][] tempsCells;             // temporary cells array for next state

    public Grid(int sizeGrid) {
        Cell[][] x = new Cell[sizeGrid][sizeGrid];
        Cell[][] y = new Cell[sizeGrid][sizeGrid];
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        this.cells = y;
        for (int i = 0; i < sizeGrid; i++) {        //loop to init in cells the cells array
            for (int j = 0; j < sizeGrid; j++) {
                x[i][j]=  new Cell(false);
            }
        }
        this.tempsCells = x;
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
        Cell[][] c = new Cell[sizeGrid][sizeGrid];
        for (int i = 0; i < sizeGrid; i++) {        //like line 10
            for (int j = 0; j < sizeGrid; j++) {
                c[i][j]=  new Cell(false);
            }
        }
        this.tempsCells = c;
    }

    private void generateRandomInitialState() {
        for (int i = 0; i < this.sizeGrid; i++) {                   //generate a random array
            for (int j = 0; j < this.sizeGrid; j++) {
                if(this.rd.nextInt() % 4 == 0 ) {
                    this.cells[i][j] =  new Cell(false);
                } else {
                    this.cells[i][j] =  new Cell(true);
                }
            }
        }
    }

    public void generateNextState() {
        int count = 0;
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k == i && l == j) {                         //check if we are not checking the cell herself
                            continue;
                        }
                        if (k < 0 || l < 0) {                            //check if we are out of bound
                            continue;
                        }
                        if (k >= this.cells.length || l >= this.cells.length) { //check if we are out of bound
                            continue;
                        }
                        if (this.cells[k][l].isAlive())                     // counting alive cells
                            count++;
                    }
                }
                if (this.cells[i][j].processState(this.cells[i][j].isAlive(), count)) {
                    this.tempsCells[i][j].setIsAlive(true);                             //sst the array for the next state
                } else {
                    this.tempsCells[i][j].setIsAlive(false);
                }
                count = 0;
            }
        }
        this.cells = this.tempsCells;
        for (int i = 0; i < this.cells.length; i++)                         // double loop is building the string to print
            for (int j = 0; j < this.cells.length; j++) {
                display = display + this.cells[i][j].toString();
                if(j < this.cells.length - 1)
                    display = display + " ";
                if (j == this.cells.length - 1 && i < this.cells.length - 1)
                    display = display + "\n";
            }
    }

    public String toString() {
        return display;
    }
}
