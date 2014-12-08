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
	 * ecrit dans la sortie un message lié à la valeur end
	 * 
	 * si valeur entrée par l'utilisateur non valide, retourne -1
	 * 
	 * @return n > -2
	 */
	
	public int getEnd();

	/**
	 * retourne la valeur entrer par l'utilisateur à travers l'uI
	 * 
	 * ecrit dans la sortie un message lié à la valeur start
	 * 
	 * si valeur entrée par l'utilisateur non valide, retourne -1
	 * 
	 * @return n > -2
	 */	
	
	public int getStart();

	/**
	 * retourne la valeur entrer par l'utilisateur à travers l'uI
	 * 
	 * ecrit dans la sortie un message lié à la valeur text
	 * 
	 * 
	 * @return s String
	 */	
	
	public String getText();
	
	 /**
	* Commence la lecture sur les entrées utilisateur par l'opération setReadStream
	*/
	public void runInvokerLoop();
	
	/**
	* Arrête la boucle de lecture des entrée utilisateur quand appelé
	*/
	public void stopLoop();
	
	/**
	* Change le flux de lecture qui sera utilisé par la boucle de l'invoqueur
	*
	* @param inputStream the read stream
	* @throws IllegalArgumentException if inputStream is null
	*/
	public void setReadStream(InputStream inputStream);
	
	/**
	* Change le flux qui affiche les messages de sortie
	*
	* @param printStream the read stream
	* @throws IllegalArgumentException if printStream is null
	*/
	public void setPrintStream(PrintStream printStream);
	
	/**
	* Enregistre une nouvelle paire mot-clé/commande
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

