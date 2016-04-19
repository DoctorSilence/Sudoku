/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author hca
 */
public class IteradorArreglo<T> implements Iterator{
    private T[] coleccion;
    private int total, actual;

    public IteradorArreglo(T arreglo[], int tam) {
        this.coleccion = arreglo;
        this.total = tam;
        actual=0;
    }
    
    public boolean hasNext() {
        return actual<total; 
    }

    public T next() {
        
        if(!hasNext())
            throw new NoSuchElementException("NO existe un elemento");
        T resul=coleccion[actual];
        actual++;
        
        return resul;
    }
    
    public void remove(){
        throw new UnsupportedOperationException();
    }
    
}
