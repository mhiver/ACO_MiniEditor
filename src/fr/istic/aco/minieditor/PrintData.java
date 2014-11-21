/**
 * 
 */
package fr.istic.aco.minieditor;

/**
 * @author 12001247
 *
 */
public class PrintData implements Command {

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
	public PrintData(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void execute() {
		editorEngine.printData();
	}
	
	@Override
	public String getName() {
		return "Print data";
	}

}
