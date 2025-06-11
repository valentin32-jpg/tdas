package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import ar.edu.uns.cs.ed.tdas.Position;

public class BTNodo<E> implements Position<E> {

    private E element;
    private BTNodo<E> left, right, parent;

    public BTNodo(E elem, BTNodo<E> hi, BTNodo<E> hd, BTNodo<E> padre) {
        this.element = elem;
        this.left = hi;
        this.right = hd;
        this.parent = padre;
    }

    public E element() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public BTNodo<E> getLeft() {
        return left;
    }

    public void setLeft(BTNodo<E> left) {
        this.left = left;
    }

    public BTNodo<E> getRight() {
        return right;
    }

    public void setRight(BTNodo<E> right) {
        this.right = right;
    }

    public BTNodo<E> getParent() {
        return parent;
    }

    public void setParent(BTNodo<E> parent) {
        this.parent = parent;
    }
}
