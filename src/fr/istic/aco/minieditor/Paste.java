package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Paste implements Command
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private EditorEngine editorEngine;
	
	/**
	 * @param editorEngine
	 */
	public Paste(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void execute() {
		editorEngine.paste();
	}


	@Override
	public String getName() {
		return "Paste";
	}
	
}

