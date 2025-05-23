/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementaciones;

import java.util.function.Function;

/**
 * clase de implementacion de un arbol binario de busqueda 
 * 
 * @author Camila Zubía
 * @param <T> Tipo genérico del elemento que se almacenará en el arbol binario de busqueda
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> {
    
    /**
     * Clase interna que representa un nodo dentro del árbol.
     * @param <T> Tipo genérico del elemento que se almacenará en los nodos
     */
    public static class Nodo<T>{
        public T dato;
        public Nodo<T> izq, der;
        public int altura;
        
        /**
         * Crea un nuevo nodo con el dato dado e inicializa sus hijos como null y su altura como 1.
         * @param dato valor almacenado en el nodo.
         */
        public Nodo(T dato) {
            this.dato = dato;
            this.izq = null;
            this.der = null;
            this.altura = 1;
        }
        //getters y setters
        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getIzq() {
            return izq;
        }

        public void setIzq(Nodo<T> izq) {
            this.izq = izq;
        }

        public Nodo<T> getDer() {
            return der;
        }

        public void setDer(Nodo<T> der) {
            this.der = der;
        }
    }
    
    /**
     * Raíz del árbol binario de búsqueda.
     */
    public Nodo<T> raiz;
    
    /**
     * Inserta un nuevo valor en el árbol respetando las reglas de orden de un ABB.
     * @param dato el valor a insertar.
     */
    public void insertar(T dato) {
        raiz = insertar(raiz, dato);
    }
    
    /**
     * Inserta recursivamente un nuevo nodo en el lugar correspondiente del subárbol.
     * @param nodo nodo actual del subárbol.
     * @param dato dato a insertar.
     * @return Nodo actualizado con el nuevo dato insertado.
     */
    private Nodo<T> insertar(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<>(dato);
        }
        if (dato.compareTo(nodo.dato) < 0) {
            nodo.izq = insertar(nodo.izq, dato);
        } else if (dato.compareTo(nodo.dato) > 0) {
            nodo.der = insertar(nodo.der, dato);
        }
        return nodo;
    }
    
    /**
     * Sirve como base para ser sobrescrito en subclases
     * @param dato 
     */
    public void eliminar(T dato){
        raiz = eliminar(raiz, dato);
    }
    
    /**
     * Se planea que este método implemente la lógica de eliminación, devolviendo el árbol actualizado tras eliminar un nodo.
     * @param nodo nodo actual del subárbol.
     * @param dato dato a insertar.
     * @return 
     */
    private Nodo<T> eliminar(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }

        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) {
            nodo.izq = eliminar(nodo.izq, dato);
        } else if (cmp > 0) {
            nodo.der = eliminar(nodo.der, dato);
        } else {
            if (nodo.izq == null && nodo.der == null) {
                return null;
            }
            else if (nodo.izq == null) {
                return nodo.der;
            } else if (nodo.der == null) {
                return nodo.izq;
            }
            else {
                Nodo<T> sucesor = encontrarMinimo(nodo.der);
                nodo.dato = sucesor.dato;
                nodo.der = eliminar(nodo.der, sucesor.dato);
            }
        }
        return nodo;
    }

    /**
     * Regresa el Nodo con el elemento minimo.
     * @param nodo
     * @return 
     */
    private Nodo<T> encontrarMinimo(Nodo<T> nodo) {
        while (nodo.izq != null) {
            nodo = nodo.izq;
        }
        return nodo;
    }
    
    /**
     * Busca un elemento dentro del árbol.Si el elemento no existe, lanza una
     * excepción.
     *
     * @param extractor
     * @param valorBuscado
     * @return El elemento encontrado.
     */
    public T buscarPorAtributo(Function<T, Comparable> extractor, Comparable valorBuscado) {
        return buscarPorAtributo(raiz, extractor, valorBuscado);
    }

    /**
     * Búsqueda recursiva del dato en el subárbol.
     *
     * @param nodo nodo actual en la búsqued
     * @param dato valor a buscar.
     * @return Dato encontrado si existe.
     */
    private T buscarPorAtributo(Nodo<T> nodo, Function<T, Comparable> extractor, Comparable valorBuscado) {
        if (nodo == null) {
            return null;
        }

        Comparable valorNodo = extractor.apply(nodo.dato);
        int cmp = valorBuscado.compareTo(valorNodo);

        if (cmp == 0) {
            return nodo.dato;
        } else if (cmp < 0) {
            return buscarPorAtributo(nodo.izq, extractor, valorBuscado);
        } else {
            return buscarPorAtributo(nodo.der, extractor, valorBuscado);
        }
    }

    /**
     * regresa un string con los valores del arbol organizados en orden
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        recorridoInOrden(raiz, sb);
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * recorre el arbol en orden
     * @param nodo
     * @param sb 
     */
    private void recorridoInOrden(Nodo<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            recorridoInOrden(nodo.izq, sb);
            sb.append(nodo.dato).append(", ");
            recorridoInOrden(nodo.der, sb);
        }
    }
}
