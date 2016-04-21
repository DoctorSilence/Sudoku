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
    private int[][] sudoku;
    private int[] limite = cuenta3x3(0, 0); //Checa posiciones 0,0

    public Sudoku() {
        for (int i = 1; i < 10; i++)
            madre.agrega(i);
    }

    public boolean camino(int fila, int col) {
        boolean status=false;
        
        if (valido(fila, col)) {
            if (fila > limite[0] + 2 || col > limite[1] + 2)
                limite = cuenta3x3(fila, col);
            ConjuntoA<Integer> temporal=agrega(fila, col, limite);
            ConjuntoADT<Integer> dif=madre.diferencia(temporal);
            Iterator<Integer> it=dif.iterator();
            sudoku[fila][col] = it.next();
            status= camino(fila,col+1);
        }
        else{
            if (fila == sudoku.length - 1 && col == sudoku[0].length - 1)
                status= true;
            else if(col>sudoku[0].length-1)
                    col=0;
        }
        
        return status;
    }

    private boolean valido(int fila, int col) {
        boolean status = false;

        status = fila >= 0 && col < sudoku.length && col >= 0 && col < sudoku[0].length&&sudoku[fila][col] == 0;

        return status;
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

    private void agregaFila(int fila, int col, ConjuntoA<Integer> temporal) {
        if (col < sudoku.length) {
            temporal.agrega(sudoku[fila][col]);
            agregaFila(fila, col + 1,temporal);
        }
    }
    
    private int[] cuenta3x3(int fila, int col){
        int[] resultado = null;
        
        
        return resultado;
    }
    
    private void agregaCol(int fila, int col, ConjuntoA<Integer> temporal) {
        if (fila < sudoku.length) {
            temporal.agrega(sudoku[fila][col]);
            agregaFila(fila+1, col, temporal);
        }
    }
    
    private void agrega3x3(int fila, int col,int[] limite, ConjuntoA<Integer> temporal) {
        
    }
    
    private ConjuntoA<Integer> agrega(int fila, int col, int[] limite){
        ConjuntoA<Integer> temporal=new ConjuntoA();
        
        agregaFila(fila, col,temporal);
        agregaCol(fila, col,temporal);
        agrega3x3(fila, col, limite,temporal);
        
        return temporal;
    }
}
