package ar.edu.uns.cs.ed.tdas.tdamapeo;

import ar.edu.uns.cs.ed.tdas.tdalista.DNodo;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.Entry;

public class HashTableMap<K, V> implements Map<K, V> {
	protected ListaDoblementeEnlazada<Entrada<K,V>>[] array;
	protected int cant;
	protected int capacidad;
	
	
	@SuppressWarnings("unchecked")
	public HashTableMap () {
		cant = 0;
		capacidad = 13;

		array = new ListaDoblementeEnlazada[capacidad];
		
		for (int i = 0; i < capacidad; i++) {
			array[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
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
		ListaDoblementeEnlazada<Entrada<K,V>>lista=array[h(key)];
		DNodo<Entrada<K,V>> cursor = (DNodo<Entrada<K,V>>) lista.first();
		
		 for(int i = 0; i < lista.size() && toReturn == null; i++) {
			 if(cursor.element().getKey().equals(key)) { toReturn = cursor.element().getValue(); }
			 	else cursor = cursor.getSiguiente();		 
		 } 
		 return toReturn;
	}
	public V put(K key, V value) {
		V toReturn = null;
		Entrada<K, V> toInsert = new Entrada<K, V>(key, value);
		ListaDoblementeEnlazada<Entrada<K,V>>lista=array[h(key)];
			checkKey(key);
			
			DNodo<Entrada<K,V>> cursor = (DNodo<Entrada<K,V>>) lista.first();
			
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
			ListaDoblementeEnlazada<Entrada<K,V>>lista=array[h(key)];
			DNodo<Entrada<K,V>> cursor = (DNodo<Entrada<K,V>>) lista.first();
			
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
			
			Iterable<K> toReturn =  new ListaDoblementeEnlazada<K>();
			
			 for(int i = 0; i < capacidad; i++) {
				 for(Entrada<K, V> cursor: array[i])
				 ((ListaDoblementeEnlazada<K>)toReturn).addLast(cursor.getKey());		 
			 } 
			return toReturn;
		}


		@Override
		public Iterable<V> values() {
			Iterable<V> toReturn =  new ListaDoblementeEnlazada<V>();

			 for(int i = 0; i < capacidad; i++) {
				 for(Entrada<K, V> cursor: array[i])
				 ((ListaDoblementeEnlazada<V>)toReturn).addLast(cursor.getValue());		 
			 } 
			return toReturn;
		}


		@Override
		public Iterable<Entry<K, V>> entries() {
			Iterable<Entry<K, V>> toReturn =  new ListaDoblementeEnlazada<Entry<K, V>>();
			
			 for(int i = 0; i < capacidad; i++) {
				 for(Entrada<K, V> cursor: array[i])
				 ((ListaDoblementeEnlazada<Entry<K, V>>)toReturn).addLast(cursor);		 
			 } 
			return toReturn;
		}
		 
		protected void checkKey(K key) {
			if (key != null) {
				throw new InvalidKeyException("llave invalida");
			};
		}

	}
