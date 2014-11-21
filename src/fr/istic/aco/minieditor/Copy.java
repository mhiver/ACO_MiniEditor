package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Copy implements Command
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
	public Copy(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void execute() {
		editorEngine.copy();
	}


	@Override
	public String getName() {
		return "Copy";
	}
	
}

