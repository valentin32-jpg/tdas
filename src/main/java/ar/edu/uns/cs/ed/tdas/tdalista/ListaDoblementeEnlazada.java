// ListaDE.java
package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
    private int cant;
    private DNodo<E> primero, ultimo;  // centinelas: primero <-> ... <-> ultimo

    public ListaDoblementeEnlazada() {
        this.cant = 0;
        primero = new DNodo<>(null);
        ultimo  = new DNodo<>(null);
        primero.setSiguiente(ultimo);
        ultimo.setAnterior(primero);
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new EmptyListException("Lista vacía");
        }
    }

    @SuppressWarnings("unchecked")
    private DNodo<E> checkPosition(Position<E> p) {
        if (p == null) {
            throw new InvalidPositionException("Posición nula");
        }
        if (!(p instanceof DNodo)) {
            throw new InvalidPositionException("Posición inválida");
        }
        DNodo<E> nodo = (DNodo<E>) p;
        // Opcional: podrías chequear que nodo != primero y nodo != ultimo
        return nodo;
    }

    private void linkBetween(DNodo<E> prev, DNodo<E> next, E elemento) {
        DNodo<E> nuevo = new DNodo<>(elemento, prev, next);
        prev.setSiguiente(nuevo);
        next.setAnterior(nuevo);
        cant++;
    }

    @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty() {
        return cant == 0;
    }

    @Override
    public Position<E> first() {
        checkEmpty();
        return primero.getSiguiente();
    }

    @Override
    public Position<E> last() {
        checkEmpty();
        return ultimo.getAnterior();
    }

    @Override
    public Position<E> next(Position<E> p) {
        DNodo<E> nodo = checkPosition(p);
        DNodo<E> sig = nodo.getSiguiente();
        if (sig == ultimo) {
            throw new BoundaryViolationException("No hay siguiente, es el último");
        }
        return sig;
    }

    @Override
    public Position<E> prev(Position<E> p) {
        DNodo<E> nodo = checkPosition(p);
        DNodo<E> ant = nodo.getAnterior();
        if (ant == primero) {
            throw new BoundaryViolationException("No hay anterior, es el primero");
        }
        return ant;
    }

    @Override
    public void addFirst(E element) {
        linkBetween(primero, primero.getSiguiente(), element);
    }

    @Override
    public void addLast(E element) {
        linkBetween(ultimo.getAnterior(), ultimo, element);
    }

    @Override
    public void addBefore(Position<E> p, E element) {
        DNodo<E> nodo = checkPosition(p);
        DNodo<E> ant = nodo.getAnterior();
        if (ant == null) {
            throw new InvalidPositionException("Posición inválida para addBefore");
        }
        linkBetween(ant, nodo, element);
    }

    @Override
    public void addAfter(Position<E> p, E element) {
        DNodo<E> nodo = checkPosition(p);
        DNodo<E> sig = nodo.getSiguiente();
        if (sig == null) {
            throw new InvalidPositionException("Posición inválida para addAfter");
        }
        linkBetween(nodo, sig, element);
    }

    @Override
    public E remove(Position<E> p) {
        checkEmpty();
        DNodo<E> nodo = checkPosition(p);
        DNodo<E> ant = nodo.getAnterior();
        DNodo<E> sig = nodo.getSiguiente();
        // Desenlazar
        ant.setSiguiente(sig);
        sig.setAnterior(ant);
        cant--;
        return nodo.getElemento();
    }

    @Override
    public E set(Position<E> p, E element) {
        DNodo<E> nodo = checkPosition(p);
        E viejo = nodo.getElemento();
        nodo.setElemento(element);
        return viejo;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private DNodo<E> actual = primero.getSiguiente();

            @Override
            public boolean hasNext() {
                return actual != ultimo;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E elem = actual.getElemento();
                actual = actual.getSiguiente();
                return elem;
            }
        };
    }

    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> snapshot = new ArrayList<>();
        DNodo<E> current = primero.getSiguiente();
        while (current != ultimo) {
            snapshot.add(current);
            current = current.getSiguiente();
        }
        return snapshot;
    }
}
