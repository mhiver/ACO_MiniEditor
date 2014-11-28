package fr.istic.aco.minieditor;



/**
 * interface obligatoire pour le patron de conception Command qui joue le rôle Command
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public  interface Command 
{
	/**
	 * méthode obligatoire du patron de conception Command
	 */
	
	public void execute();


	/**
	 * Retourne le nom de la commande
	 * @return type string
	 */
	
	public String getName();
}

