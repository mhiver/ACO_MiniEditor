package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Selection
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private EditorEngineImpl editorEngineImpl;

	private int start;
	private int end;
		
	/**
	 * @param editorEngineImpl
	 */
	public Selection(EditorEngineImpl editorEngineImpl) {
		this.editorEngineImpl = editorEngineImpl;
		this.start = 0;
		setLength(0);
	}

	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setStart(int s) {
		start = s;
	}
	
	public void setEnd(int e) {
		end = e;
	}
	
	// FIXME: -1 ou pas ?
	public void setLength(int l) {
		end = start + l - 1;
	}

}

