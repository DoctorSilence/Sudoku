/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;

/**
 * <pre>
 * Se define la clase Sudoku con el objetivo de poder resolver un cierto sudoku
 * válido (respecto a las reglas comunes del juego) con el uso de recursividad,
 * backtracking y conjuntos.
 * </pre>
 * @author Victor Cruz, Fabian Orduña, José Francisco Laguna
 * @version 3.0
 */
public class Sudoku {
    private ConjuntoA<Integer> madre = new ConjuntoA();
    private int[][] sudoku;
    private final int MAX=9;
    private ConjuntoADT<Integer>[][] posibilidades;

    /**
     * La clase inicia con una matriz de enteros vacía (es decir, rellenada con 
     * ceros), así como con un conjunto tal que se usará para obtener los valores
     * que si son válidos para poner en una cierta casilla del sudoku.
     */
    public Sudoku() {
        for (int i = 1; i <=MAX; i++)
            madre.agrega(i);
        sudoku=new int [MAX][MAX];
        posibilidades= new ConjuntoA[MAX][MAX];
    }
    
    /**
     * 
     * @param fila: Entero, es un digito que orientará al programa para saber si
     * en la fila en la que está es válida, es decir, que está dentro de los 
     * parámetros del sudoku (existe entre el 0 y el 8).
     * @param col: Entero, es un digito que orientará al programa para saber si
     * en la columna en la que está es válida, es decir, que está dentro de los 
     * parámetros del sudoku (existe entre el 0 y el 8).
     * @return boolean<ol>
     * <li>true si el sudoku pudo encontrar exitosamente una solución al problema
     * propuesto.</li>
     * <li>false si el sudoku no encontró alguna solución al problema gracias a
     * mal posicionamiento de los números o ya no existe un número valido para 
     * cierta casilla existente en la matriz de enteros.</li>
     * </ol>
     */
    public boolean solucion(int fila, int col){
        boolean status=false;
        
        if(valido(fila,col)){
            ConjuntoA<Integer> temporal=agrega(fila, col);
            posibilidades[fila][col]= madre.diferencia(temporal);
            if(!posibilidades[fila][col].estaVacio()){
                Iterator<Integer> posibil=posibilidades[fila][col].iterator();
                if(fila==sudoku.length-1&&col==sudoku[0].length-1){
                    status= true;
                    if(sudoku[fila][col]==0)
                        sudoku[fila][col]=posibil.next();
                }
                else
                    status=camina(status,posibil,fila,col);
            }
            else
                status=false;
        }
        else{
            if(fila==sudoku.length-1&&col==sudoku[0].length-1)
                status=true;
            else{
                if (col > sudoku[0].length - 1) {
                    col = 0;
                    fila++;
                } 
                else if (sudoku[fila][col] != 0){
                    col++;
                }
                status=solucion(fila, col);
            }
                
        }
        
        return status;
    }
    
    /**
     * 
     * @param status: Boolean, que indica si la casilla en la que está no se ha
     * intentado poner alguna solución, si la solución que se intentó no fue 
     * válida o si ya acabó con una solución viable y solamente está regresando 
     * hasta el inicio del programa "verdadero" gracias al backtracking.
     * @param it: Iterador para un conjunto de posibles números enteros que pueden
     * situarse en una casilla del sudoku, el cual se va a cambiar de número si
     * resulta que el número de una casilla posterior no tuvo solución viable.
     * @param fila: Entero, que situa al programa respecto a qué fila se escribirá
     * un número para las posibilidades que tiene disponible.
     * @param col: Entero, que situa al programa respecto a qué columna se escribirá
     * un número para las posibilidades que tiene disponible.
     * @return boolean<ol>
     * <li>true si el "camino" que sigue el programa es el correcto, tal que
     * en todas las casillas por las que ha recorrido han tenido algun número
     * válido.
     * </li>
     * <li>false si en algún tramo del "camino" se encontró con una casilla sin 
     * posibilidades válidas, entonces tendrá que hacer uso del backtracking 
     * e intentar con otra posibilidad que le permita seguir "caminando" por las 
     * casillas del sudoku con normalidad o irse a la casilla anterior de donde está.
     * </li>
     * </ol>
     */
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
    
    /**
     * 
     * @param fila: Entero, que menciona en que fila se situe el programa respecto
     * a la matriz de enteros.
     * @param col: Entero, que menciona en que columna se situe el programa respecto
     * a la matriz de enteros.
     * @return boolean<ol>
     * <li>true si la fila y la columna existen entre el 0 y el 8, así como, en 
     * esa coordenada no existe algún número dado por el usuario.
     * </li>
     * <li>false si exede las dimensiones de la matriz o ya contiene algún número
     * que fue dado por el usuario.
     * </li>
     * </ol>
     */
    private boolean valido(int fila, int col) {
        return fila >= 0 && fila < sudoku.length && col >= 0 &&
                col < sudoku[0].length&&sudoku[fila][col] == 0;
    }

    /**
     * @return String, tal que es la representación impresa del sudoku con o sin
     * solución.
     * Nota: Esté método solo es usado para probar en un main simple el método: 
     * "solución(int, int)".
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

    /**
     * 
     * @param fila: Entero, que situa al programa respecto a qué fila se añadirá
     * un número distinto de cero y existene en un conjunto temporal que los 
     * contendrá y tomar en cuenta que estos números no serán una posibilidad 
     * para cierta casilla en el sudoku.
     * @param col: Entero, que situa al programa respecto a qué columna se moverá
     * para poder agregar los números de cierta fila al conjunto temporal.
     * @param temporal:Conjunto de enteros que contendrá los números que no 
     * serán una posibilidad para cierta casilla en el sudoku.
     */
    private void agregaFila(int fila, int col, ConjuntoA<Integer> temporal) {
        if (col < sudoku[0].length) {
            if(sudoku[fila][col]!=0)
                temporal.agrega(sudoku[fila][col]);
            agregaFila(fila, col + 1,temporal);
        }
    }
    
    /**
     * 
     * @param fila: Entero, que situa al programa respecto a qué fila se moverá
     * para poder agregar los números de cierta fila al conjunto temporal.
     * @param col: Entero, que situa al programa respecto a qué columna se añadirá
     * un número distinto de cero y existene en un conjunto temporal que los 
     * contendrá y tomar en cuenta que estos números no serán una posibilidad 
     * para cierta casilla en el sudoku.
     * @param temporal:Conjunto de enteros que contendrá los números que no 
     * serán una posibilidad para cierta casilla en el sudoku.
     */
    private void agregaCol(int fila, int col, ConjuntoA<Integer> temporal) {
        if (fila < sudoku.length) {
            if(sudoku[fila][col]!=0)
                temporal.agrega(sudoku[fila][col]);
            agregaCol(fila+1, col, temporal);
        }
    }
    
    /**
     * 
     * @param fila: Entero, que situa al programa respecto a qué fila se moverá
     * para poder agregar los números de cierto espacio de 3x3 al conjunto temporal.
     * @param col: Entero, que situa al programa respecto a qué columna se moverá
     * para poder agregar los números de cierto espacio de 3x3 al conjunto temporal.
     * @param temporal: Conjunto de enteros que contendrá los números que no 
     * serán una posibilidad para cierta casilla en el sudoku.
     */
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
    
    /**
     * 
     * @param coordenada: Entero que checa tanto fila o columna a que corresponda
     * a un cierto espacio 3x3 respecto a la distribución básica del sudoku.
     * @return Entero para notar si ha cambiado la coordenada inicial de dicho 
     * espacio.
     */
    private int checa(int coordenada){
        int coordenadaI;
        int checaC = coordenada%3;
        if(checaC==0)
            coordenadaI = coordenada;
        else{
            if(checaC==1)
                coordenadaI = coordenada-1;
            else
                coordenadaI = coordenada-2;
        }
        return coordenadaI;
    }
    
    /**
     * 
     * @param fila: Entero que es la fila idéntica a la del método solución() 
     * para situarse bajo que fila agregar números que no serán solución para 
     * la casilla en la que se está.
     * @param col: Entero que es la columna idéntica a la del método solución() 
     * para situarse bajo que columna agregar números que no serán solución para 
     * la casilla en la que se está.
     * @return Conjunto de enteros que contendrá todos aquellos números que no 
     * serán solución para la casilla en la que se está.
     */
    private ConjuntoA<Integer> agrega(int fila, int col){
        ConjuntoA<Integer> temporal=new ConjuntoA();
        
        agregaFila(fila, 0,temporal);
        agregaCol(0, col,temporal);
        agrega3x3(fila, col,temporal);
        
        return temporal;
    }
    
    /**
     * 
     * @param fila: Entero que situa en una fila al programa, el cual agregará 
     * un número dado por el usuario en dicha fila.
     * @param col: Entero que situa en una columna al programa, el cual agregará 
     * un número dado por el usuario en dicha columna.
     * @param dato: Entero tal que será aquel que se pondrá en la coordenada 
     * (fila, col) respecto al sudoku.
     * @return boolean<ol>
     * <li>true si agregó existosamente el dato en la coordenada propuesta.
     * </li>
     * <li>false si falló al agregar el dato en la coordenada propuesta.
     * </li>
     * </ol>  
     */
    public boolean setCelda(int fila, int col, int dato){
        boolean status=false;
        
        if(sudoku[fila][col]==0&&dato>0&&dato<=9){
            sudoku[fila][col]=dato;
            status=true;
        }
        
        return status;
    }

    /**
     * 
     * @return Matriz de enteros que será el sudoku con o sin solución.
     */
    public int[][] getSudoku() {
        return sudoku;
    }
    
    
}
