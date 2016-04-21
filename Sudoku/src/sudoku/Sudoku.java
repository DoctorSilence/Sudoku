/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;

/**
 *
 * @author Daniel
 */
public class Sudoku {

    private ConjuntoA<Integer> madre = new ConjuntoA();
    private ConjuntoA<Integer> temporal = new ConjuntoA();
    
    private int[][] sudoku;
    private int[] limite = cuenta3x3(0, 0); //CHECAR POSICIONES 0,0

    public Sudoku() {
        for (int i = 1; i < 10; i++) {
            madre.agrega(i);
        }
    }

    public boolean camino(int fila, int col) {
        boolean status = false;

        if (valido(fila, col)) {
            if (fila > limite[0] + 2 || col > limite[1] + 2) {
                limite = cuenta3x3(fila, col);
            }
            agrega(fila, col, limite);
            ConjuntoADT<Integer> dif=madre.diferencia(temporal);
            Iterator<Integer> it=dif.iterator();
            sudoku[fila][col] = it.next();

            if (fila == sudoku.length - 1 && col == sudoku[0].length - 1) {
                status = true;
            } else {
                status = camino(fila + 1, col);     //abajo
                if (!status) {
                    status = camino(fila, col + 1);  //derecha
                }
                if (!status) {
                    status = camino(fila - 1, col);  //arriba
                }
                if (!status) {
                    status = camino(fila, col - 1);  //izquierda
                }
            }

            if (status) {
                if (fila > limite[0] + 2 || col > limite[1] + 2) {
                    limite = cuenta3x3(fila, col);
                }
                agrega(fila, col, limite);
                dif = madre.diferencia(temporal);
                it = dif.iterator();
                sudoku[fila][col] = it.next();
            }
        }
        return status;
    }

    private boolean valido(int row, int column) {
        boolean result = false;

        /**
         * check if cell is in the bounds of the matrix
         */
        if (row >= 0 && row < sudoku.length
                && column >= 0 && column < sudoku[row].length) /**
         * check if cell is not blocked and not previously tried
         */
        {
            if (sudoku[row][column] == 1) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Returns the maze as a string.
     *
     * @return a string representation of the maze
     */
    public String toString() {
        String result = "\n";

        for (int row = 0; row < sudoku.length; row++) {
            for (int column = 0; column < sudoku[row].length; column++) {
                result += sudoku[row][column] + "";
            }
            result += "\n";
        }

        return result;
    }

    private void agregaFila(int fila, int col) {
        if (col < sudoku.length) {
            temporal.agrega(sudoku[fila][col]);
            agregaFila(fila, col + 1);
        }
    }
    
    private int[] cuenta3x3(int fila, int col){
        int[] resultado = null;
        
        
        return resultado;
    }
    
    private void agrega(int fila, int col, int[] limite){
    
    }
}
