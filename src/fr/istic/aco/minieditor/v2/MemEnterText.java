package fr.istic.aco.minieditor.v2;

/**
 * Implémente l'interface Memento
 * 
 * Joue le rôle de concrete memento dans le patron de conception memento
 * 
 * Mémento associé à la commande EnterTextRecordable
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class MemEnterText implements Memento{

	/*
	 * attribut qui servira à definir le nouveau String pour remplacer la selection courante
	 * 
	 */
	
	private String text;
	
	/**
	 * 
	 * paramètre qui en entrée qui vont permettre de réappliquer la commande sauvegardée
	 * 
	 * @param _start
	 * @param _end
	 */
	
	public MemEnterText(String _text) {
		text = _text;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
	 */
	@Override
	public Object getSavedState() {
		return text;
	}

}
