package fr.istic.aco.minieditor.v2;

/**
 * interface pour le patron de conception Memento qui joue le rôle memento
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public interface Memento {

	/**
	 * retourne un objet qui contient l'état du memento, si aucune information a sauvegarder returnera null
	 */
	
	public Object getSavedState();

}
