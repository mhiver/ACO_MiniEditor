package fr.istic.aco.minieditor;


/**
 * Classe qui représente l'état courant de la sélection de notre editeur
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class Clipboard
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String text;
	
		
	/**
	 * @param editorEngineImpl
	 * @param text
	 */
	public Clipboard() {
		this.text = "";
	}

	public void setText(String s) {
		text = s;
	}
	
	public String getText() {
		return text;
	}

}

