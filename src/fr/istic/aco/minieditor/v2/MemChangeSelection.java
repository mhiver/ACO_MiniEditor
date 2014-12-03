package fr.istic.aco.minieditor.v2;

/**
 * Implémente l'interface Memento
 * 
 * Joue le rôle de concrete memento dans le patron de conception memento
 * 
 * Mémento associé à la commande ChangeSelectionRecordable
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class MemChangeSelection implements Memento {
	

	/*
	 * attribut qui servira à definir l'une des borne de la sélection
	 * 
	 * start >= 0
	 */
	
	private int start;
	

	/*
	 * attribut qui servira à definir l'une des borne de la sélection
	 * 
	 * end >= 0
	 */
	
	private int end;
	

	/**
	 * 
	 * paramètre qui en entrée qui vont permettre de réappliquer la commande
	 * 
	 * @param _start
	 * @param _end
	 */
	
	public MemChangeSelection(int _start, int _end) {
		start = _start;
		end = _end;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
	 */
	@Override
	public Object getSavedState() {
		int[] result = new int[2];
		result[0] = start;
		result[1] = end;
		return result;
	}

}