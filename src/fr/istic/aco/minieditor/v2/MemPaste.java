package fr.istic.aco.minieditor.v2;

/**
 * Implémente l'interface Memento
 * 
 * Joue le rôle de concrete memento dans le patron de conception memento
 * 
 * Mémento associé à la commande PasteRecordable
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class MemPaste implements Memento{

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
	 */
	@Override
	public Object getSavedState() {
		
		return null;
	}

}
