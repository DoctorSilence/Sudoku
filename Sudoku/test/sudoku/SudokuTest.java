/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class SudokuTest {
    
    public SudokuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of camino method, of class Sudoku.
     */
    @Test
    public void testSolucion() {
        System.out.println("camino");
        int fila = 0;
        int col = 0;
        Sudoku instance = new Sudoku();
        boolean expResult = false;
        boolean result = instance.solucion(fila, col);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Sudoku.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Sudoku instance = new Sudoku(2);
        String expResult ="\n00\n"
                + "00\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCelda method, of class Sudoku.
     */
    @Test
    public void testSetCelda() {
        System.out.println("setCelda");
        int fila = 0;
        int col = 0;
        int dato = 5;
        Sudoku instance = new Sudoku();
        boolean expResult = true;
        boolean result = instance.setCelda(fila, col, dato);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSudoku method, of class Sudoku.
     */
    @Test
    public void testGetSudoku() {
        System.out.println("getSudoku");
        Sudoku instance = new Sudoku();
        int[][] expResult = null;
        int[][] result = instance.getSudoku();
        assertArrayEquals(expResult, result);
    }
    
}
