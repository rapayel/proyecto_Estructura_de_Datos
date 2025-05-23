/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

/**
 * clase de implementacion de una lista enlazada circular
 * @author Camila Zubía
 * @param <T> tipo generico del elemento almacenado en la lista enlaada circular
 */
public class ListaEnlazadaCircular<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;
    /**
     * clase generica del nodo de la lista enlazada
     * @param <T> tipo generico del nodo
     */
    public static class Nodo<T> {

        private T dato;
        private Nodo<T> sig;
        //getters y setters
        public Nodo(T dato) {
            this.dato = dato;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getSig() {
            return sig;
        }

        public void setSig(Nodo<T> sig) {
            this.sig = sig;
        }
    }
    
    // getters and setters
    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public Nodo<T> getFin() {
        return fin;
    }

    public void setFin(Nodo<T> fin) {
        this.fin = fin;
    }

    public int getTamaño() {
        return tamaño;
    }
    
    /**
     * agregar un nodo en la lista
     * 
     * @param dato valor del nodo
     */
    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            nuevo.sig = inicio;
        } else {
            fin.sig= nuevo;
            fin = nuevo;
            fin.sig = inicio;
        }
        tamaño++;
    }
    
    /**
     * elimina el primer nodo de la lista
     */
    public void eliminarPrimero() {
        if (inicio == null) {
            return;
        }

        if (inicio == fin) {
            inicio = null;
            fin = null;
        } else {
            inicio = inicio.sig;
            fin.sig = inicio;
        }
        tamaño--;
    }

    /**
     * Recorre la lista y elimina el primer dato que coincida.
     *
     * @param dato elemento a eliminar
     */
    public void eliminar(T dato) {
        if (inicio == null) {
            return;
        }

        if (inicio == fin && inicio.getDato().equals(dato)) {
            inicio = null;
            fin = null;
            tamaño--;
            return;
        }

        Nodo<T> actual = inicio;
        Nodo<T> anterior = fin;

        do {
            if (actual.getDato().equals(dato)) {
                if (actual == inicio) {
                    inicio = inicio.getSig();
                    fin.setSig(inicio);
                } else if (actual == fin) {
                    fin = anterior;
                    fin.setSig(inicio);
                } else {
                    anterior.setSig(actual.getSig());
                }
                tamaño--;
                return;
            }
            anterior = actual;
            actual = actual.getSig();
        } while (actual != inicio);
    }

    /**
     * verifica si la lista esta vacia.
     * @return esta vacio
     */
    public boolean vacia() {
        return tamaño == 0;
    }
}
