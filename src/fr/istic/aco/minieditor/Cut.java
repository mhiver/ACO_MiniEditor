package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Cut implements Command
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
	public Cut(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void execute() {
		editorEngine.cut();
	}


	@Override
	public String getName() {
		return "Cut";
	}
	
}

