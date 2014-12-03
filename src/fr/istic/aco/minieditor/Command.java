package fr.istic.aco.minieditor;



/**
 * interface pour le patron de conception Command qui joue le rôle Command
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public  interface Command 
{
	/**
	 * méthode obligatoire du patron de conception Command qui execute l'action associée à la commande
	 */
	
	public void execute();


	/**
	 * Retourne le nom de la commande
	 * @return type string
	 */
	
	public String getName();
}

