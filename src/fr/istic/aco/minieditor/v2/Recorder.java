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
	 * méthode obligatoire du patron de conception memento qui va ajouter une commande à la macro à enregistrer
	 * 
	 * @param recordable cmd
	 */
	
	public void record(Recordable cmd);	
	
	/**
	 * Commence l'enregistrement de la macro (role receveur du patron de conception Command)
	 */
	
	public void begin();
	
	/**
	 * Arrête l'enregistrement de la macro (role receveur du patron de conception Command)
	 */
	
	public void end();
	
	/**
	 * Rejoue la macro enregistrée, pas autoriser pendant un enregistrement de macro (role receveur du patron de conception Command)
	 */
	
	public void replay();
	
	/**
	 * indique si l'enregistreur est en train d'enregistrer
	 * 
	 * @return l'attribut recording
	 */
	public boolean getRecording();
	
}
