package fr.istic.aco.minieditor;


/**
 * Classe qui représente l'état courant de du clipboard de notre editeur
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class Clipboard
{
	/**
	 * L'attribut text représente le string contenue dans le clipboard
	 */
	
	private String text;
	
		
	public Clipboard() {
		this.text = "";
	}


	/**
	 * modifie attribut text
	 * @param s un String la nouvelle valeur de text
	 */
	public void setText(String s) {
		text = s;
	}


	/**
	 * Retourne attribut start
	 * @return start >= 0
	 */
	public String getText() {
		return text;
	}

}

