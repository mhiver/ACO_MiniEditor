package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v2.Recordable;

/**
 * permet d'annuler des actions ou de rétablir des actions
 * 
 * Interface qui joue le rôle de receveur dans le patron de conception Command pour Undo et Redo
 * 
 * interface pour le patron de conception Memento qui joue le rôle caretaker
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */


public interface UndoRedoManager {
	
	/**
	 * méthode obligatoire du patron de conception memento qui va:
	 * 
	 * 	- soit mémoriser l'état du moteur éditeur
	 *  - soit mémoriser la commande dans l'ensemble des commandes déjà jouées
	 * 
	 * @param recordable cmd
	 */
	
	public void record(Recordable cmd);	
	
	/**
	 * Va retourner l'éditeur de texte dans l'état précédent
	 * 
	 */
	
	public void undo();
	
	/**
	 * Va mettre l'éditeur de texte dans le dernier état annulé (ssi rien a été fait depuis)
	 * 
	 */
	
	public void redo();

	/** 
	 * Retourne dans l'état le plus avancé enregistré, revient à faire redo en boucle jusqu'à un point fixe
	 * 
	 */
	public void redoAll();

	/**
	 * Dis si on est en train d'effectuer un redo ou non
	 * 
	 * @return boolean
	 */
	public boolean getIsInRedo();


	/**
	 * Dis si on est en train d'effectuer un redoAll ou non
	 * 
	 * @return boolean
	 */
	boolean getIsInRedoAll();
	
}
