package ar.edu.uns.cs.ed.tdas.tdamapeo;

import ar.edu.uns.cs.ed.tdas.tdalista.DNode;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;

public class HashTableMap<K, V> implements Map<K, V> {
	protected ListaDE<Entrada<K,V>>[] array;
	protected int cant;
	protected int capacidad;
	
	
	@SuppressWarnings("unchecked")
	public HashTableMap () {
		cant = 0;
		capacidad = 13;

		array = new ListaDE[capacidad];
		
		for (int i = 0; i < capacidad; i++) {
			array[i] = new ListaDE<Entrada<K,V>>();
		}
	}
	
	protected int h(K key) {
		return key.hashCode() % (capacidad);
	}
	@Override
	public int size() {
		return cant;
	}

	@Override
	public boolean isEmpty() {
		return cant == 0;
	}
	
	public V get(K key) {
		V toReturn = null;
		
		checkKey(key);
		ListaDE<Entrada<K,V>> lista = array[h(key)];
		
		Iterator<Entrada<K, V>> ite = lista.iterator();

		while (ite.hasNext()) {
			Entrada<K, V> entrada = ite.next();
			
			if(entrada.equals(key)) {
			toReturn = entrada.getValue();		
			}
		}
		
		 return toReturn;
	}

	public V put(K key, V value) {
		checkKey(key);
		V toReturn = null;
		
		Entrada<K, V> toInsert = new Entrada<K, V>(key, value);
		ListaDE<Entrada<K,V>>lista = array[h(key)];
		
		DNode<Entrada<K,V>> cursor = (DNode<Entrada<K,V>>) lista.first();
		
		for(int i = 0; i < lista.size() && toReturn == null; i++) {
			if(cursor.element().getKey().equals(key)) { 
				toReturn = cursor.element().getValue(); 
				cursor.element().setValue(value);
			}
			else cursor = cursor.getSiguiente();		 
		} 
			
		if(toReturn == null) {
			lista.addLast(toInsert);
			cant++;
		}
		
			return toReturn;
	}

		@Override
		public V remove(K key) {
			V toReturn = null;
			
			checkKey(key);
			ListaDE<Entrada<K,V>>lista = array[h(key)];
			DNode<Entrada<K,V>> cursor = (DNode<Entrada<K,V>>) lista.first();
			
			 for(int i = 0; i < lista.size() && toReturn == null; i++) {
				 if(cursor.element().getKey().equals(key)) { 
					 toReturn = cursor.element().getValue();
					 lista.remove(cursor);
					 cant--;
					 }
				 	else cursor = cursor.getSiguiente();		 
			 } 
			 
			 return toReturn;
		}


		@Override
		public Iterable<K> keys() {
			
			Iterable<K> toReturn =  new ListaDE<K>();
			
			 for(int i = 0; i < capacidad; i++) {
				 for(Entrada<K, V> cursor: array[i])
				 ((ListaDE<K>)toReturn).addLast(cursor.getKey());		 
			 } 
			return toReturn;
		}


		@Override
		public Iterable<V> values() {
			Iterable<V> toReturn =  new ListaDE<V>();

			 for(int i = 0; i < capacidad; i++) {
				 for(Entrada<K, V> cursor: array[i])
				 ((ListaDE<V>)toReturn).addLast(cursor.getValue());		 
			 } 
			return toReturn;
		}


		@Override
		public Iterable<Entry<K, V>> entries() {
			Iterable<Entry<K, V>> toReturn =  new ListaDE<Entry<K, V>>();
			
			 for(int i = 0; i < capacidad; i++) {
				 for(Entrada<K, V> cursor: array[i])
				 ((ListaDE<Entry<K, V>>)toReturn).addLast(cursor);		 
			 } 
			return toReturn;
		}
		 
		protected void checkKey(K key) {
			if (key == null) {
				throw new InvalidKeyException("llave invalida");
			};
		}

	}
