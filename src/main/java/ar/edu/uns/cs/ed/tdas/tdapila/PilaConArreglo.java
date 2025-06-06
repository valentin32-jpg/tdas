package ar.edu.uns.cs.ed.tdas.tdapila;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException;

public class PilaConArreglo<E> implements Stack<E>{
	private E[] arreglo;

	private int cant;
	
	
	
	@SuppressWarnings("unchecked")
	public PilaConArreglo(int max){
		cant = 0;
		arreglo = (E[]) new Object[max];

	}
	
	public PilaConArreglo(){
		this(20);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return cant;
	}



	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cant == 0;
	}



	@Override
	public E top() {
		// TODO Auto-generated method stub
		
		if(this.isEmpty()) {
			throw new EmptyStackException("La pila esta vacia");
		}
		
		return arreglo[cant-1];
	}



	@Override
	public void push(E element) {
		@SuppressWarnings("unchecked")
		E[] nuevoArreglo = (E[]) new Object[(arreglo.length) * 2];
		
		
		// TODO Auto-generated method stub
		if(arreglo.length == cant) {
			for(int i = 0; i < size(); i++) {
				nuevoArreglo[i] = arreglo[i];
			}
			arreglo = nuevoArreglo;
		} 
		
		arreglo[cant] = element;
		cant++;
	}



	@Override
	public E pop() {
		E elemento;
		if(this.isEmpty()) {
			throw new EmptyStackException("La pila esta vacia");
		} else {
			elemento = arreglo[cant-1];
			arreglo[cant-1] = null;
			cant--;
		}
			return elemento;
			
	}
}
