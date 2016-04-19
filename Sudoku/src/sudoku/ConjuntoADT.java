/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;

/**
 *
 * @author hca
 */
public interface ConjuntoADT<T> extends Iterable<T>{
    public boolean estaVacio();
    public int getCardinalidad();
    public T quita(T dato);
    public boolean agrega(T dato);
    public boolean contiene(T dato);
    public ConjuntoADT<T> union(ConjuntoADT<T> otro);
    public ConjuntoADT<T> intersección(ConjuntoADT<T> otro);
    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro);
    public Iterator<T> iterator();
}
