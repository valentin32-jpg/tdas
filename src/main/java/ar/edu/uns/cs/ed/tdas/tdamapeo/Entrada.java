package ar.edu.uns.cs.ed.tdas.tdamapeo;

import ar.edu.uns.cs.ed.tdas.Entry;

public class Entrada<K, V> implements Entry<K, V> {
	protected K key;
	protected V value;
	
	public Entrada(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public void setValue(V v) {
		value = v;
	}
	
	public void setKey(K k) {
		key = k;
	}


}
