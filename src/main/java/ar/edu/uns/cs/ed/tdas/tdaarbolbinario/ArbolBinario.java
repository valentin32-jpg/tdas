package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;

public class ArbolBinario<E> implements BinaryTree<E>{

    private BTNodo<E> raiz;
    private int size;
    
    public ArbolBinario() {
        this.raiz = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private BTNodo<E> checkPosition (Position<E> p) {
		BTNodo<E> resultado = null;
		if (p == null) {
			throw new InvalidPositionException("Posición nula.");
		}

		if (this.isEmpty()) {
			throw new InvalidPositionException("Posición inválida");
		}

		try {
			resultado = (BTNodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Posición inválida");
		}

		return resultado;
	}

    public Iterator<E> iterator() {
            return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'positions'");
    }

    @Override
    public E replace(Position<E> v, E e) {
        BTNodo<E> nodo = checkPosition(v);

        E retorno = e;

        nodo.setElement(e);

        return retorno;
    }

    public Position<E> root() {
        if(size == 0)
            throw new EmptyTreeException( "El esta vacio fijate que estas haciendo");
        return this.raiz;
    }
//NO es necesario implementar los metdos de aca al siguiente comentario
    @Override
    public Position<E> parent(Position<E> v) {
        throw new UnsupportedOperationException("Unimplemented method 'children'");
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'children'");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInternal'");
    }

    @Override
    public boolean isExternal(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExternal'");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isRoot'");
    }

    @Override
    public void createRoot(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRoot'");
    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFirstChild'");
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLastChild'");
    }

    @Override
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBefore'");
    }

    @Override
    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public void removeExternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeExternalNode'");
    }

    @Override
    public void removeInternalNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeInternalNode'");
    }

    @Override
    public void removeNode(Position<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNode'");
    }

    @Override
    public Position<E> left(Position<E> v) {
        BTNodo<E> n = checkPosition(v);

        if (n.getLeft() != null) {
            throw new InvalidOperationException("Error: La posición ya tiene un hijo izquierdo.");
        }
        return v;
    }
//Hasta aca no es necesario son triviales
    @Override
    public Position<E> right(Position<E> v) {
        BTNodo<E> n = checkPosition(v);

        if (n.getLeft() != null) {
            throw new InvalidOperationException("Error: La posición ya tiene un hijo izquierdo.");
        }
        return v;
    }

    public boolean hasLeft(Position<E> v) {
        BTNodo<E> nodo = checkPosition(v);

        return nodo.getLeft() != null;
    }

    public boolean hasRight(Position<E> v) {
        BTNodo<E> nodo = checkPosition(v);

        return nodo.getRight() != null;
    }

    public Position<E> addLeft(Position<E> v, E r) {
        BTNodo<E> nodo = checkPosition(v);

        if (nodo.getLeft()!=null) {
            throw new InvalidOperationException("Error: La posición ya tiene un hijo izquierdo.");
        }

        BTNodo<E> nodo2 = new BTNodo<>(r, null, null, nodo);
        nodo.setLeft(nodo2);

        return nodo;
    }

    @Override
    public Position<E> addRight(Position<E> v, E r) {
        BTNodo<E> nodo = checkPosition(v);

        if (nodo.getRight() != null) {
            throw new InvalidOperationException("Error: La posición ya tiene un hijo izquierdo.");
        }

        BTNodo<E> nodo2 = new BTNodo<>(r, null, null, nodo);
        nodo.setRight(nodo2);

        return nodo;
    }

    @Override
    public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) {

    }




}
