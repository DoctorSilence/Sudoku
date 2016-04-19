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
public class Sudoku {
    private ConjuntoA<Integer> madre=new ConjuntoA();
    private ConjuntoA<Integer> temporal=new ConjuntoA();
    private int[][] sudoku;
    private int[] limite=cuenta3x3(0,0); //CHECAR POSICIONES 0,0

    public Sudoku() {
        for(int i=1;i<10;i++)
            madre.agrega(i);
    }
    
   public boolean camino (int fila, int col)
   {
    boolean status = false;
      
    if (valido (fila, col)){
        if(fila>limite[0]+2||col>limite[1]+2)
           limite=cuenta3x3(fila,col);
        agrega(fila,col,limite);
        ConjuntoA<Integer> dif=madre.diferencia(temporal);
        Iterator<Integer> it=dif.iterator();
        sudoku[fila][col] = it.next();

        if (fila == sudoku.length-1 && col == sudoku[0].length-1)
            status = true;
        else{
            status = camino (fila+1, col);     //abajo
            if (!status)
               status = camino (fila, col+1);  //derecha
            if (!status)
               status = camino (fila-1, col);  //arriba
            if (!status)
               status = camino (fila, col-1);  //izquierda
        }

        if (status){
            if(fila>limite[0]+2||col>limite[1]+2)
                limite=cuenta3x3(fila,col);
            agrega(fila,col,limite);
            ConjuntoA<Integer> dif=madre.diferencia(temporal);
            Iterator<Integer> it=dif.iterator();
            sudoku[fila][col] = it.next();
        }
    }
    return done;
   }
    private boolean valido (int row, int column)
   {
      boolean result = false;
 
      /** check if cell is in the bounds of the matrix */
      if (row >= 0 && row < grid.length &&
          column >= 0 && column < grid[row].length)

         /**  check if cell is not blocked and not previously tried */
         if (grid[row][column] == 1)
            result = true;

      return result;
   }

   /**
    * Returns the maze as a string.
    * 
    * @return  a string representation of the maze
    */
   public String toString ()
   {
      String result = "\n";

      for (int row=0; row < grid.length; row++)
      {
         for (int column=0; column < grid[row].length; column++)
            result += grid[row][column] + "";
         result += "\n";
      }
      int h=0;

      return result;
   }
}
