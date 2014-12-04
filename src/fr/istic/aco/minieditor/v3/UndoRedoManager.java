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
	 * méthode obligatoire du patron de conception memento qui va ajouter une commande à la macro à enregistrer
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
	
}
