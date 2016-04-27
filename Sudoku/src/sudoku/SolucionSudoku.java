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
        //Este main prueba como con un sudoku del cual existe una solución en el
        //periodico obtiene la misma solución que éste publica.
        //Sudoku no vacio, poniendo los numeros en las casillas simulando GUI
        Sudoku s=new Sudoku();
        s.setCelda(0, 1, 3);
        s.setCelda(0, 2, 2);
        s.setCelda(0, 4, 6);
        s.setCelda(0, 5, 4);
        s.setCelda(0, 8, 1);
        s.setCelda(1, 0, 4);
        s.setCelda(1, 1, 6);
        s.setCelda(1, 2, 8);
        s.setCelda(1, 4, 7);
        s.setCelda(1, 6, 3);
        s.setCelda(1, 7, 5);
        s.setCelda(1, 8, 2);
        s.setCelda(2, 1, 5);
        s.setCelda(2, 4, 2);
        s.setCelda(2, 8, 4);
        s.setCelda(3, 2, 7);
        s.setCelda(3, 8, 8);
        s.setCelda(4, 2, 3);
        s.setCelda(4, 3, 4);
        s.setCelda(4, 4, 1);
        s.setCelda(4, 5, 8);
        s.setCelda(4, 6, 5);
        s.setCelda(5, 0, 8);
        s.setCelda(5, 6, 2);
        s.setCelda(6, 0, 1);
        s.setCelda(6, 4, 5);
        s.setCelda(6, 7, 9);
        s.setCelda(7, 0, 5);
        s.setCelda(7, 1, 8);
        s.setCelda(7, 2, 6);
        s.setCelda(7, 4, 4);
        s.setCelda(7, 6, 7);
        s.setCelda(7, 7, 1);
        s.setCelda(7, 8, 3);
        s.setCelda(8, 0, 3);
        s.setCelda(8, 3, 6);
        s.setCelda(8, 4, 8);
        s.setCelda(8, 6, 4);
        s.setCelda(8, 7, 2);
        System.out.println("Sudoku no resuelto"+s);
        System.out.println(s.solucion(0, 0));
        System.out.println("Sudoku resuelto"+s);
        //Para un sudoku vacío
        Sudoku s1=new Sudoku();
        System.out.println(s1);
        s1.solucion(0,0);
        System.out.println(s1);
    }
    
}
