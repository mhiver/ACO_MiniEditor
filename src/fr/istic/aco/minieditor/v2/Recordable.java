package fr.istic.aco.minieditor.v2;

/**
 * interface pour le patron de conception Memento qui joue le rôle originator
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public interface Recordable {

	
	/**
	 * méthode obligatoire du patron de conception memento qui revoit un objet memento associé à l'objet enregistrable
	 * 
	 * @return memento m
	 */
	
	public Memento getMemento();
	
	/**
	 * méthode obligatoire du patron de conception memento qui change un objet memento associé à l'objet enregistrable
	 * 
	 * @param memento m
	 */
	public void setMemento(Memento m);
	
}
