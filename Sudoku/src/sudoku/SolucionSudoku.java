/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Daniel
 */
public class SolucionSudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sudoku s=new Sudoku();
        System.out.println("Sudoku no resuelto"+s);
        s.solucion2(0, 0);
        System.out.println("Sudoku resuelto"+s);
    }
    
}
