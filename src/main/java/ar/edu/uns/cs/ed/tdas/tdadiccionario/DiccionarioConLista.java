package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entrada;

public class DiccionarioConLista<K,V> implements Dictionary<K,V>{
	protected ListaDE<Entrada<K,V>> lista;
	protected int cant;
	protected int capacidad;
	
	public DiccionarioConLista () {
			lista = new ListaDE<Entrada<K,V>>();
		}
	
	@Override
	public int size() {
		return lista.size();
	}
	@Override
	public boolean isEmpty() {
		return lista.size() == 0;
	}
	
	//Ver metodo del porque no puedo hacer eso?//
	@SuppressWarnings("unchecked")
	@Override
	public Entry<K, V> find(K key) {
		Iterator<Entrada<K, V>> contenedor = lista.iterator();
		Entry<K, V> resultado = null;
		Entrada<K, V> cursor = null;
		
		checkKey(key);
		
		while(contenedor.hasNext()) {
			cursor = contenedor.next();
			
			if(cursor.getKey().equals(key)) {
				return resultado = (Entry<K, V>) cursor.getKey();
			}
		}
		
		return resultado;
	}
	@Override
	public Iterable<Entry<K, V>> findAll(K key) {
		PositionList<Entry<K, V>> listaDeUnSoloUso = new ListaDE<Entry<K,V>>();
		Iterator<Entrada<K, V>> cursor = lista.iterator();
		Entrada<K, V> retorna = null;
		
		checkKey(key);
		
		while(cursor.hasNext()) {
			retorna = cursor.next();
			
			if(retorna.getKey().equals(key)) {			
				listaDeUnSoloUso.addLast(retorna);
			}
		}
		
		return listaDeUnSoloUso;
	}
	@Override
	public Entry<K, V> insert(K key, V value) {
		Entrada<K, V> retorno = new Entrada<K, V>(key, value);
		
		checkKey(key);
		
		lista.addLast(retorno);	

		return retorno;
	}
	@Override
	public Entry<K, V> remove(Entry<K, V> e) {
		Entry<K, V> retorno = null;
		Position<Entrada<K,V>> pos = null;
		Iterator<Position<Entrada<K, V>>> cursor = lista.positions().iterator();
		
		if(e == null) {throw new InvalidEntryException("Este mensaje no deberia de aparece si se implementa bien el metodo");} 
			
		while(cursor.hasNext()) {
			pos = cursor.next();
			
			if(pos.element().getKey().equals(e) && pos.element().getValue().equals(e) ) {
				retorno = pos.element();
				
				lista.remove(pos);
			}
		}

		return retorno;
	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		
		return null;
	}
	
	protected void checkKey(K key) {
		if (key != null) {
			throw new InvalidKeyException("llave invalida");
		};
	}
}
