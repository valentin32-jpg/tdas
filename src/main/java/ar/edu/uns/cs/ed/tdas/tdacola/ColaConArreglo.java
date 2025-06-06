package ar.edu.uns.cs.ed.tdas.tdacola;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaConArreglo<E> implements Queue<E>{
	private E[] arreglo;
	
	private int cant;
	
	private int frente;
	
	private int fin;
	
	@SuppressWarnings("unchecked")
	public ColaConArreglo(int cant) {
		arreglo = (E[]) new Object[cant];
		cant = 0;
		frente = 0;
		fin = 0;
	}
	
	public ColaConArreglo() {
		this(20);
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
	public E front() {
		if(cant == 0) {
			throw new EmptyQueueException("La cola esta vacia");
		} 
		
		return arreglo[frente];
	}

	@Override
	public void enqueue(E elemento) {
		if(cant == arreglo.length) {
			redimensionar();
		} 
		
		arreglo[fin] = elemento;
		cant++;
		fin = (fin + 1) % (arreglo.length);
				
	}
	
	private void redimensionar() {
		int aux = 0;
		 @SuppressWarnings("unchecked")
		 E[] nuevoArreglo = (E[]) new Object[arreglo.length * 2];

	      
	        for (int i = frente; i < arreglo.length; i++) {
	        	nuevoArreglo[aux] = arreglo[i];
	        	aux++;
	            
	        }
	        
	        if(aux <= cant) {
	        	for(int j = 0; j < frente; j++) {
	        		nuevoArreglo[aux] = arreglo[j];
	        		aux++;
	        	}
	        }

	        arreglo = nuevoArreglo;
	        frente = 0;
	        fin = aux;
	}

	@Override
	public E dequeue() {
		E elemento = null;
		
		if(this.isEmpty()) {
			throw new EmptyQueueException("La cola esta vacia");
		} else {
			elemento = this.arreglo[frente];
			arreglo[frente] = null;
			cant--;
			frente = (frente + 1) % (arreglo.length);
		}
		return elemento;
}
}