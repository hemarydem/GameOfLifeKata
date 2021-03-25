package gameoflife;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private int sizeGrid;
    private Random rd;
    public String display = "";
    public Cell[][] tempsCells;

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        generateRandomInitialState();
    }
    //constructeur
    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
        Cell[][] c = new Cell[sizeGrid][sizeGrid];
        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                c[i][j]=  new Cell(false);
            }
        }
        this.tempsCells = c;// ajout
    }

    private void generateRandomInitialState() {
        for (int i = 0; i < this.sizeGrid; i++) {
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
                System.out.println("------------Pour cell[" + i + "][" + j + "]------------\n");
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        System.out.println("Est analyser cell[" + k + "][" + l + "]\n");
                        if (k == i && l == j) {
                            System.out.println("jump cell himself\n");
                            continue;
                        }
                        if (k < 0 || l < 0) {
                            System.out.println("jumper 1\n");
                            continue;
                        }
                        if (k >= this.cells.length || l >= this.cells.length) {
                            System.out.println("jumper 2\n");
                            continue;
                        }
                        if (this.cells[k][l].isAlive())
                            count++;
                    }
                }
                //System.out.println("count = " + count + "\n de la cell[" + i + "][" + j + "]\n");
                if (this.cells[i][j].processState(this.cells[i][j].isAlive(), count)) {
                    this.tempsCells[i][j].setIsAlive(true);//
                } else {
                    this.tempsCells[i][j].setIsAlive(false);//
                }
                count = 0;
            }
        }
        this.cells = this.tempsCells;
        for (int i = 0; i < this.cells.length; i++)
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
