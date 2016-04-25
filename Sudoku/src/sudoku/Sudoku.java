/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;

/**
 *
 * @author Victor Cruz, Fabian Orduña
 */
public class Sudoku {
    //EL TEMPORAL PUEDE CONTENER AL CERO? O DEJAMOS QUE NO PUEDA TENER AL CERO, 
    //SOLO NÚMEROS DEL 1 AL 9, PORQUE QUE PASA CUANDO HAY UN SUDOKU VACIO Y 
    //NO HAY NINGÚN NÚMERO MÁS QUE EL 0?
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
    
    public Sudoku(int max){
        for (int i = 1; i <=max; i++)
            madre.agrega(i);
        sudoku=new int [max][max];
        posibilidades=new ConjuntoA[max][max];
    }
    

//    public boolean solucion(int fila, int col) {
//        boolean status=false;
//        
//        if (valido(fila, col)) {
//            //MODIFICACION
//            sudoku[fila][col]=-1;
//            if(fila==sudoku.length-1&&col==sudoku[0].length-1)
//                status=true;
//            else{
//                status=solucion(fila,col+1);
//                if(!status)
//                    status=solucion(fila+1,0);
//            }
//            //MODIFICACION
//            if (fila > limite[0] + 2 || col > limite[1] + 2)
//                limite = cuenta3x3(fila, col);
//            ConjuntoA<Integer> temporal=agrega(fila, col, limite);
//            ConjuntoADT<Integer> dif=madre.diferencia(temporal);
//            posibilidades[fila][col]=(ConjuntoA<Integer>) dif;//Modificacion
//            if(dif.getCardinalidad()!=1){
//                Iterator<Integer> it=dif.iterator();
//                sudoku[fila][col] = it.next();
//            }
//            status= solucion(fila,col+1);
//        }
//        else{
//            if (fila == sudoku.length && col == sudoku[0].length)
//                status= true;
//            else{
//                if(col>sudoku[0].length-1){
//                    col=0;
//                    fila++;
//                }
//                else
//                    col++;
//                status=solucion(fila,col);
//            }
//        }
//        
//        return status;
//    }

    public boolean solucion2(int fila, int col){
        boolean status=false;
        
        if(valido(fila,col)){
            ConjuntoA<Integer> temporal=agrega(fila, col);
            posibilidades[fila][col]= madre.diferencia(temporal);
            if(!posibilidades[fila][col].estaVacio()){
                Iterator<Integer> posibil=posibilidades[fila][col].iterator();
                if(fila==sudoku.length-1&&col==sudoku[0].length-1){
                    status= true;
                    //sudoku[fila][col]=posibil.next();
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
            else if (sudoku[fila][col] > 0)
                col++;
            status=solucion2(fila, col);
        }
        
        return status;
    }
    
    private boolean camina(boolean status, Iterator<Integer> it, int fila, int col){
        if(!status&&it.hasNext()){
            sudoku[fila][col]=it.next();
            status=solucion2(fila,col+1);
            camina(status,it,fila,col);
        }
        else{
            if(!it.hasNext()){
                status= false;
                sudoku[fila][col]=0;
            }
            else if(status)
                status= true;
        }
        return status;
    }
    
    private boolean valido(int fila, int col) {
        return fila >= 0 && fila < sudoku.length && col >= 0 && col < sudoku[0].length&&sudoku[fila][col] <= 0;
    }

    /**
     * Returns the maze as a string.
     *
     * @return a string representation of the maze
     */
    public String toString() {
        String result = "\n";

        for (int[] s : sudoku) {
            for (int column = 0; column < s.length; column++) {
                result += s[column] + "";
            }
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
        if(valido(fila,col)){
            filaI = checa(fila);
            columI = checa(col);
            for (int i = filaI; i < filaI+3; i++) {
                for (int j = columI; j < columI+3; j++) {
                    if(sudoku[i][j]!=0)
                        temporal.agrega(sudoku[i][j]);
                }
            }  
        }
    }
    
    public int checa(int fila){
        int filaI;
        int checaF = fila%3;
        if(checaF==0)
            filaI = fila;
        else
            if(checaF==1)
                filaI = fila-1;
            else
                filaI = fila-2;
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
