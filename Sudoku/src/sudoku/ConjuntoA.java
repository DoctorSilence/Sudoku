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
public class ConjuntoA<T> implements ConjuntoADT<T>{
    private T[] conjunto;
    private int cardinalidad;
    private final int MAX=50;

    public ConjuntoA() {
        conjunto= (T[]) new Object[MAX];
        cardinalidad=0;
    }
    
    public boolean estaVacio(){
        return cardinalidad==0;
    }
    
    public int getCardinalidad() {
        return cardinalidad;
    }
    
    public boolean contiene(T dato){
        boolean status=false;
        Iterator<T> it=iterator();
        T aux;
        
        while(it.hasNext()&&!status){
            aux=it.next();
            status=aux.equals(dato);
        }
        
        return status;
    }
    
    public boolean agrega(T dato){
        boolean status=false;
        
        if(dato!=null&&!contiene(dato)){
            status=true;
            if(cardinalidad==conjunto.length-1)
                expande(conjunto.length*2);
            conjunto[cardinalidad]=dato;
            cardinalidad++;
        }
        
        return status;
    }
    
    public T quita(T dato){
        T resul=null;
        int i=0;
        
        while(i<cardinalidad&&!conjunto[i].equals(dato)){
            i++;
        }
        if(i<cardinalidad){
            resul=conjunto[i];
            cardinalidad--;
            conjunto[i]=conjunto[cardinalidad];
            conjunto[cardinalidad]=null;
        }
        
        return resul;
    }
    
    private void expande(int tam){
        T nuevo[]=(T[])new Object[tam];
        
        for(int i=0;i<cardinalidad;i++)
            nuevo[i]=conjunto[i];
        conjunto=nuevo;
    }
    
    public Iterator<T> iterator(){
        return new IteradorArreglo(conjunto,cardinalidad);
    }
    
    public String toString(){
        StringBuilder str=new StringBuilder();
        Iterator<T> it=iterator();
        
        /*
        while(it.hasNext())
            str.append(it.next()+"\n");
        
        return str.toString();
        */
        
        if(!it.hasNext())
            return "";
        else
            return toString(it,str);
        
    }
    private String toString(Iterator<T> it, StringBuilder str){
        
        if(!it.hasNext())
            return str.toString();
        else{
            str.append(it.next()+" ");
            return toString(it,str);
        }
        
    }

    public ConjuntoADT<T> union(ConjuntoADT<T> otro) {
        ConjuntoADT<T> c=new ConjuntoA();
        Iterator<T> it=iterator();
        
        while (it.hasNext())
            c.agrega(it.next());
        if (otro != null) {
            Iterator<T> itOtro=otro.iterator();
            while (itOtro.hasNext())
                c.agrega(itOtro.next());
        }
        
        return c;
    }

    public ConjuntoADT<T> intersección(ConjuntoADT<T> otro) {
        ConjuntoADT<T> c=new ConjuntoA();
        Iterator<T> it=iterator();
        T dato;
        
        if (otro != null) {
            while (it.hasNext()) {
                dato=it.next();
                if(otro.contiene(dato))
                    c.agrega(dato);
            }
        }

        return c;
    }

    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro) {
        ConjuntoADT<T> c=new ConjuntoA();
        Iterator<T> it=iterator();
        T dato;
        
        if (otro != null) {
            while (it.hasNext()) {
                dato=it.next();
                if(!otro.contiene(dato))
                    c.agrega(dato);
            }
        }
        
        return c;
    }
    
    @Override
    public boolean equals(Object otro){
        boolean status=false;
        
        if(otro!=null)
            if(otro instanceof ConjuntoA){
                ConjuntoA<T> temp=((ConjuntoA<T>) otro);
                if (cardinalidad == temp.cardinalidad)
                    status= equals(temp.iterator());
            }
        
        return status;
    }
    private boolean equals(Iterator<T> it){
        if(!it.hasNext())
            return true;
        else {
            T dato=it.next();
            if (contiene(dato))
                return equals(it);
            else
                return false;
        }
    }
    
}
