// DNodo.java
package ar.edu.uns.cs.ed.tdas.tdalista;

import ar.edu.uns.cs.ed.tdas.Position;

public class DNodo<E> implements Position<E> {
    private E elem;
    private DNodo<E> prev, next;

    public DNodo(E elemento) {
        this.elem = elemento;
        this.prev = null;
        this.next = null;
    }

    public DNodo(E elemento, DNodo<E> prev, DNodo<E> next) {
        this.elem = elemento;
        this.prev = prev;
        this.next = next;
    }

    // Implementación del método de Position<E>
    @Override
    public E element() {
        return elem;
    }

    // Getters y setters auxiliares que necesita ListaDE:
    public E getElemento() {
        return elem;
    }

    public void setElemento(E elem) {
        this.elem = elem;
    }

    public DNodo<E> getAnterior() {
        return prev;
    }

    public DNodo<E> getSiguiente() {
        return next;
    }

    public void setAnterior(DNodo<E> a) {
        this.prev = a;
    }

    public void setSiguiente(DNodo<E> s) {
        this.next = s;
    }
}
