package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
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

