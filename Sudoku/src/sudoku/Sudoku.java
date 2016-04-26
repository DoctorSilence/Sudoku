/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;

/**
 *
 * @author Victor Cruz, Fabian Orduña, 
 */
public class Sudoku {
    private ConjuntoA<Integer> madre = new ConjuntoA();
    private int[][] sudoku;
    private final int MAX=9; //DUDA, está bien el MAX o hacerlo final
    private ConjuntoADT<Integer>[][] posibilidades;

    public Sudoku() {
        for (int i = 1; i <=MAX; i++)
            madre.agrega(i);
        sudoku=new int [MAX][MAX];
        posibilidades= new ConjuntoA[MAX][MAX];
    }
    
    public boolean solucion(int fila, int col){
        boolean status=false;
        
        if(valido(fila,col)){
            ConjuntoA<Integer> temporal=agrega(fila, col);
            posibilidades[fila][col]= madre.diferencia(temporal);
            if(!posibilidades[fila][col].estaVacio()){
                Iterator<Integer> posibil=posibilidades[fila][col].iterator();
                if(fila==sudoku.length-1&&col==sudoku[0].length-1){
                    status= true;
                    sudoku[fila][col]=posibil.next();
                }
                else
                    status=camina(status,posibil,fila,col);
            }
            else
                status=false;
        }
        else{
            if (col > sudoku[0].length - 1) {
                col = 0;
                fila++;
            } 
            else if (sudoku[fila][col] != 0)
                col++;
            status=solucion(fila, col);
        }
        
        return status;
    }
    
    private boolean camina(boolean status, Iterator<Integer> it, int fila, int col){
        if(!status&&it.hasNext()){
            sudoku[fila][col]=it.next();
            status=solucion(fila,col+1);
            if(!status)
                status=camina(status,it,fila,col);
        }
        else if(!it.hasNext()&&!status){
            sudoku[fila][col]=0;
            posibilidades[fila][col]=new ConjuntoA();
        }
        return status;
    }
    
    private boolean valido(int fila, int col) {
        return fila >= 0 && fila < sudoku.length && col >= 0 &&
                col < sudoku[0].length&&sudoku[fila][col] <= 0;
    }

    /**
     * Regresa el sudoku como String
     *
     * @return a string representation of the maze
     */
    public String toString ()
   {
      String result = "\n";

      for (int row=0; row < sudoku.length; row++)
      {
         for (int column=0; column < sudoku[row].length; column++)
            result += sudoku[row][column] + "";
         result += "\n";
      }

      return result;
   }

    private void agregaFila(int fila, int col, ConjuntoA<Integer> temporal) {
        if (col < sudoku[0].length) {
            if(sudoku[fila][col]!=0)
                temporal.agrega(sudoku[fila][col]);
            agregaFila(fila, col + 1,temporal);
        }
    }
    
    private void agregaCol(int fila, int col, ConjuntoA<Integer> temporal) {
        if (fila < sudoku.length) {
            if(sudoku[fila][col]!=0)
                temporal.agrega(sudoku[fila][col]);
            agregaCol(fila+1, col, temporal);
        }
    }
    
    private void agrega3x3(int fila, int col, ConjuntoA<Integer> temporal) {
        int filaI, columI;
        filaI = checa(fila);
        columI = checa(col);
        for (int i = filaI; i < filaI+3; i++) {
            for (int j = columI; j < columI+3; j++) {
                if(sudoku[i][j]!=0)
                    temporal.agrega(sudoku[i][j]);
            }
        }  
    }
    
    private int checa(int fila){
        int filaI;
        int checaF = fila%3;
        if(checaF==0)
            filaI = fila;
        else{
            if(checaF==1)
                filaI = fila-1;
            else
                filaI = fila-2;
        }
        return filaI;
    }
    
    private ConjuntoA<Integer> agrega(int fila, int col){
        ConjuntoA<Integer> temporal=new ConjuntoA();
        
        agregaFila(fila, 0,temporal);
        agregaCol(0, col,temporal);
        agrega3x3(fila, col,temporal);
        
        return temporal;
    }
    
    public boolean setCelda(int fila, int col, int dato){
        boolean status=false;
        
        if(sudoku[fila][col]==0&&dato>0&&dato<=9){
            sudoku[fila][col]=dato;
            status=true;
        }
        
        return status;
    }

    public int[][] getSudoku() {
        return sudoku;
    }
    
    
}
