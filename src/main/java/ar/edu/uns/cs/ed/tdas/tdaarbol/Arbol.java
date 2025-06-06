package ar.edu.uns.cs.ed.tdas.tdaarbol;


import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class Arbol<E> implements Tree<E>{
	
	protected TNodo<E> root;
	protected int size;
	
	public Arbol() {
		size = 0;
		root = null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> resultado = new ListaDoblementeEnlazada<E>();
		
		preordenElementos(root, resultado);
		
		return resultado.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<E> resultado = new ListaDoblementeEnlazada<E>();
		
		preordenElementos(root, resultado);
		
		return resultado.positions();
	}

	@Override
	public E replace(Position<E> v, E e) {

		TNodo<E> nodo = checkPosition(v);
		E resultado = nodo.element();
		
		nodo.setElemento(e);
		
		return resultado;
	}
	
	private TNodo<E> checkPosition(Position<E> v) {
		TNodo<E> resultado = null;
		if( v == null) {throw new InvalidPositionException("Posicion es nula");}
		
		if( this.isEmpty()) {throw new InvalidPositionException("El árbol está vacío");}
		
		try {
			resultado = (TNodo<E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException("La posición no es del tipo correcto");
		}
		
		return resultado;
	}


	@Override
	public Position<E> root() {
		if(this.isEmpty()) {throw new EmptyTreeException("El árbol está vacío");}
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
	
		return nodo.padre;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
		
		for(TNodo<E> puntero : nodo.hijos) {
			resultado.addLast(nodo);
		}
		
		return resultado;
	}

	@Override
	public boolean isInternal(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		
		return  !nodo.Hijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) {
		return isInternal(v) == false;
	}

	@Override
	public boolean isRoot(Position<E> v) {
		TNodo<E> nodo = checkPosition(v);
		return root == nodo;
	}

	@Override
	public void createRoot(E e) {
		if(this.root != null) {throw new InvalidOperationException("El árbol ya tiene una raíz");}
		else {
			this.root = new TNodo<E>(e, null);
			this.size++;
		}
		
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> hijo = new TNodo<E>(e, nodo);
		nodo.Hijos().addFirst(hijo);
		
		return hijo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> hijo = new TNodo<E>(e, nodo);
		
		nodo.Hijos().addLast(hijo);
		
		return null;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hder = checkPosition(rb);
		
		if(hder.getPadre() != padre ) 
			throw new InvalidPositionException("El padre no es el verdadero padre");
		
		TNodo<E> nuevoNodo = new TNodo<>(e, padre);
		
		Iterator<Position<TNodo<E>>> ite = padre.Hijos().positions().iterator();
		Position<TNodo<E>> posBuscada = null;
		
		while(ite.hasNext() && posBuscada == null) {
			Position<TNodo<E>> pos = ite.next();
			
			if(pos.element() == hder ) 
				posBuscada = pos;
		}
		
		padre.Hijos().addBefore(posBuscada, nuevoNodo);
		size++;
		
		return nuevoNodo;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hiqz = checkPosition(lb);
		
		if(hiqz.getPadre() != padre ) 
			throw new InvalidPositionException("El padre no es el verdadero padre");
		
		TNodo<E> nuevoNodo = new TNodo<>(e, padre);
		
		Iterator<Position<TNodo<E>>> ite = padre.Hijos().positions().iterator();
		Position<TNodo<E>> posBuscada = null;
		
		while(ite.hasNext() && posBuscada == null) {
			Position<TNodo<E>> pos = ite.next();
			
			if(pos.element() == hiqz ) 
				posBuscada = pos;
		}
		
		padre.Hijos().addAfter(posBuscada, nuevoNodo);
		size++;
		
		return nuevoNodo;
	}

	@Override
	public void removeExternalNode(Position<E> p) {
		TNodo<E> nodo = checkPosition(p);		
		
		if(isInternal(nodo)) 
			throw new InvalidPositionException("El nodo no es externo");
		
		if(root == nodo) {
			root = null;
			size = 0;
			} 
		
			Iterator<Position<TNodo<E>>> ite = nodo.getPadre().Hijos().positions().iterator();
			Position<TNodo<E>> posBuscada = null;
			
			while(ite.hasNext() && posBuscada == null) {
				Position<TNodo<E>> pos = ite.next();
				
				if(pos.element() == nodo ) 
					posBuscada = pos;
			}
			
			nodo.getPadre().Hijos().remove(posBuscada);
			size--;
	}

	@Override
	public void removeInternalNode(Position<E> p) {
		TNodo<E> nodo = checkPosition(p);		
		
		if(isExternal(nodo)) 
			throw new InvalidPositionException("El nodo no es externo");
		
		if(root == nodo) {
			if(root.Hijos().size() > 1)
				throw new InvalidPositionException("No puedo eliminar la raiz porque tiene muchos hijos");
			
			root = root.Hijos().first().element();
			root.setPadre(null);
			size--;
			} 
		
			Iterator<Position<TNodo<E>>> ite = nodo.getPadre().Hijos().positions().iterator();
			Position<TNodo<E>> posBuscada = null;
			
			while(ite.hasNext() && posBuscada == null) {
				Position<TNodo<E>> pos = ite.next();
				
				if(pos.element() == nodo ) 
					posBuscada = pos;
			}
			
			for(TNodo<E> hijo : nodo.Hijos()) {
				nodo.getPadre().Hijos().addBefore(posBuscada, hijo);
				hijo.setPadre(nodo.getPadre());
			}
			
			nodo.getPadre().Hijos().remove(posBuscada);
			nodo.setPadre(null);
			size--;
		
	}

	@Override
	public void removeNode(Position<E> p) {
		if(isExternal(p))
			removeExternalNode(p);
		else if(isInternal(p))
			removeInternalNode(p);
	}
	
	protected void preordenElementos(TNodo<E> nodo, PositionList<E> resultado) {
		resultado.addLast(nodo.element()); 
		
		for(TNodo<E> n : nodo.Hijos()) {
			preordenElementos(n, resultado);
		}
	}
	

}
