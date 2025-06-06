package ar.edu.uns.cs.ed.tdas.tdaarbol;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TNodo<E> implements Position<E>{
	protected E elemento;
    protected TNodo<E> padre;
    protected PositionList<TNodo<E>> hijos;
    
    
    public TNodo (E dato, TNodo<E> papa){
    	elemento = dato;
    	padre = papa;
    	hijos = new ListaDoblementeEnlazada<>();
    }
    
	
	public E element() {
		return elemento;
	}
	
	 public void setElemento(E elemento) {
	        this.elemento = elemento;
	    }

	    public TNodo<E> getPadre() {
	        return padre;
	    }

	    public void setPadre(TNodo<E> papa) {
	        this.padre = papa;
	    }

	    public PositionList<TNodo<E>> Hijos(){
			return hijos;
		}
}
