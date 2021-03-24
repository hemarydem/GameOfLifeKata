package gameoflife;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private int sizeGrid;
    private Random rd;
    public String display = "";

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
    }

    private void generateRandomInitialState() {
        // TODO
    }

    public void generateNextState() {
        //TODO le code commenté si dessous sert checker les cellules
        // il est pas complètemnt focntionnel mais c'est lui qu'il faut débugger
        // ne pas effacer
        /*
        int count = 0;
        for (int i = 0; i < this.cells.length - 1; i++)
            for (int j = 0; j < this.cells.length - 1; j++) {
                for (int k = i - 1; k <= i + 1; k++)
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k < 0 || l < 0)
                            continue;
                        if (k > this.cells.length || l > this.cells.length)
                            continue;
                        if(this.cells[k][l].isAlive())
                                count++;
                    }
                if(this.cells[i][j].processState(this.cells[i][j].isAlive(),count))
                    this.cells[i][j].setIsAlive(true);
            }
*/
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
