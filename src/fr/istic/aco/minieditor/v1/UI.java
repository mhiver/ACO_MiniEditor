package fr.istic.aco.minieditor.v1;

import java.io.InputStream;
import java.io.PrintStream;



/**
 * Classe qui joue le rôle d'invoqueur dans le patron de conception Command
 * Classe qui joue le rôle d'invoqueur et de receveur pour la commande "Quit"
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public  interface UI 
{

	/**
	 * retourne la valeur entrer par l'utilisateur à travers l'uI
	 * 
	 * ecris dans la sortie un message lié à la valeur end
	 * 
	 * si valeur entrée par l'utilisateur non valide, retourne -1
	 * 
	 * @return n > -2
	 */
	
	public int getEnd();

	/**
	 * retourne la valeur entrer par l'utilisateur à travers l'uI
	 * 
	 * ecris dans la sortie un message lié à la valeur start
	 * 
	 * si valeur entrée par l'utilisateur non valide, retourne -1
	 * 
	 * @return n > -2
	 */	
	
	public int getStart();

	/**
	 * retourne la valeur entrer par l'utilisateur à travers l'uI
	 * 
	 * ecris dans la sortie un message lié à la valeur text
	 * 
	 * 
	 * @return s String
	 */	
	
	public String getText();
	
	 /**
	* Starts the reading of the read stream set by setReadStream operation
	* 
	* Commence la lecture sur les entrées utilisateur par la méthode setReadStream
	*/
	public void runInvokerLoop();
	
	/**
	* Stops the read stream loop now.
	* 
	* Va stopper la boucle de lecture des entrée utilisateur quand appelé
	*/
	public void stopLoop();
	
	/**
	* Sets the read stream that will be used by runInvokerLoop
	* 
	* Change le flux de lecture qui sera utilisé par la boucle de l'invoqueur
	*
	* @param inputStream the read stream
	* @throws IllegalArgumentException if inputStream is null
	*/
	public void setReadStream(InputStream inputStream);
	
	/**
	* Sets the print stream that will be used to display output and messages
	* 
	* Change la sortie qui va afficher les messages de sorties
	*
	* @param printStream the read stream
	* @throws IllegalArgumentException if printStream is null
	*/
	public void setPrintStream(PrintStream printStream);
	
	/**
	* Registers a new keyword/command pair
	*
	* Enregistre une nouvelle paire keyword/command
	*
	* @param keyword a non-null String
	* @param cmd a non-null Command reference
	* @throws java.lang.IllegalArgumentException for null parameters
	*/
	public void addCommand(String keyword, Command cmd);
	
	/**
	* Demande à l'interface utilisateur d'afficher un texte
	*
	* @param text le texte à afficher
	*/
	public void printText(String text);
	
}

