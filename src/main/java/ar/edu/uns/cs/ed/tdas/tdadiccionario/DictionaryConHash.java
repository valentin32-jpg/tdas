package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entrada;

public class DictionaryConHash <K,V> implements Dictionary<K,V> {

    protected ListaDE<Entrada<K,V>> []array;
    protected int cant;

    @SuppressWarnings("unchecked")
    public DictionaryConHash(){
        array = (ListaDE<Entrada<K,V>>[]) new ListaDE[73];
        for(int i = 0; i < array.length;i++){
            array[i] = new ListaDE<>();
        }
        cant = 0;
    }
    public int hash(K key ){
        return key.hashCode() % 73;
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
    public Entry<K, V> find(K key) {
        if (key == null) 
            throw new InvalidKeyException("Clave inválida");
        
        Entry<K,V> resultado = null;
        
        int index = hash(key);
       
        for (Entry<K, V> entry : array[index]) {
            if (entry.getKey() == (key)) {
                resultado= entry;
            }
        }
        return resultado;
    }

    @SuppressWarnings("unused")
    @Override
    public Iterable<Entry<K, V>> findAll(K key) {
         if (key == null) 
            throw new InvalidKeyException("Clave inválida");
    
        ListaDE<Entry<K, V>> resultado = new ListaDE<>();

        int index = hash(key);
        ListaDE<Entrada<K, V>> lista = array[index];
       
        for (Entry<K, V> entry : array[index]) {
            if (entry.getKey() == (key)) {
                resultado.addLast(entry);
            }
        }

        return resultado;
    }

    @Override
   public Entry<K, V> insert(K key, V value) {
        checkKey(key);

        Entrada<K, V> entrada = new Entrada<>(key, value);

        int index = hash(key);
        ListaDE<Entrada<K, V>> bucket = array[index];

        bucket.addLast(entrada); 
        return entrada;
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) {
        checkKey(e.getKey());
        Entry<K, V> eliminado =  null;


        int index = hash(e.getKey());

        ListaDE<Entrada<K, V>> bucket = array[index];

        for (Position<Entrada<K, V>> pos : bucket.positions()) {
            if (pos.element().equals(e)) {
                eliminado = bucket.remove(pos); 
                cant--;
            }
        }
       return eliminado;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
       Iterable<Entry<K, V>> toReturn =  new ListaDE<Entry<K, V>>();
			
			 for(int i = 0; i < cant; i++) {
				 for(Entrada<K, V> cursor: array[i])
				 ((ListaDE<Entry<K, V>>)toReturn).addLast(cursor);		 
			 } 
			return toReturn;
		}

    protected int h(K key) {
    	checkKey(key); 
    	
		return Math.abs(key.hashCode() % cant); 
	}

    protected void checkKey(K key) {
			if (key == null) {
				throw new InvalidKeyException("llave invalida");
			};
		}

    
}
