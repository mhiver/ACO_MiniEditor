package fr.istic.aco.minieditor.v2;

/**
 * permet d'enregistrer une macro et de la rejouer
 * 
 * interface pour le patron de conception Memento qui joue le rôle caretaker
 * 
 * interface pour le patron de conception Command qui joue le rôle receveur pour les commandes Begin et Replay
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
	
	/**
	 * Commence l'enregistrement de la macro 
	 */
	
	public void begin();
	
	/**
	 * Arrête l'enregistrement de la macro 
	 */
	
	public void end();
	
	/**
	 * Rejoue la macro enregistrée
	 */
	
	public void replay();
	
}
