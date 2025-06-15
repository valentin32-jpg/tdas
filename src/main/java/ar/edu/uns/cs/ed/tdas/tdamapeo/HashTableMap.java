package ar.edu.uns.cs.ed.tdas.tdamapeo;

import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
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
		capacidad = 73;

		array = new ListaDE[capacidad];
		
		for (int i = 0; i < capacidad; i++) {
			array[i] = new ListaDE<Entrada<K,V>>();
		}
	}
	
	protected int h(K key) {
    	checkKey(key); 
    	
		return Math.abs(key.hashCode() % capacidad); 
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
		ListaDE<Entrada<K,V>> lista = array[h(key)];
		
		checkKey(key);
		
		Iterator<Entrada<K, V>> ite = lista.iterator();
		boolean buscada = false;
		
		while (ite.hasNext() && !buscada) {
			Entrada<K, V> entrada = ite.next();
			
			if (entrada.getKey().equals(key)) {
				toReturn = entrada.getValue();
				buscada = true;
			}
		}
		
		 return toReturn;
	}

	public V put(K key, V value) {
		V resultado = null;
        ListaDE<Entrada<K,V>> lista = array[h(key)];
        Entrada<K,V> nueva = new Entrada(key, value);
		
		if(key==null){
            throw new InvalidKeyException("La clave del put es nula");
        } else {
            Iterator<Entrada<K,V>> ite = lista.iterator();
            boolean encontrada=false;
            
			while (ite.hasNext() && !encontrada){
                Entrada<K,V> e= ite.next();
                
				if(e.getKey().equals(key)){
                    encontrada = true;
                    resultado = e.getValue();
                    e.setValue(value);
                }
            }
            if(!encontrada) {
                lista.addLast(nueva);
                cant++;
            }
        }

        return resultado;
    }
		@Override
		public V remove(K key) {
		checkKey(key);
		

    	V toReturn = null;
		ListaDE<Entrada<K, V>> lista = array[h(key)];
		Position<Entrada<K, V>> objetivo = null;
		boolean chequeo = false;

		Iterator<Position<Entrada<K, V>>> it = lista.positions().iterator();
		
		while (it.hasNext() && !chequeo) {
			Position<Entrada<K, V>> pos = it.next();
        	Entrada<K, V> entrada = pos.element();

			if (entrada.getKey().equals(key)) {
				toReturn = entrada.getValue();
				objetivo = pos;
				chequeo = true;
			}
    	}

			if (chequeo) {
				lista.remove(objetivo); 
				cant--;         
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
