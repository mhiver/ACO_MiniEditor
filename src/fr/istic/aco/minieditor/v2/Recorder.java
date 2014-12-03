package fr.istic.aco.minieditor.v2;

/**
 * interface pour le patron de conception Memento qui joue le rôle caretaker
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public interface Recorder {
	
	/**
	 * méthode obligatoire du patron de conception memento qui va enregistrer
	 * 
	 * @param recordable cmd
	 */
	
	public void record(Recordable cmd);	
	
}
