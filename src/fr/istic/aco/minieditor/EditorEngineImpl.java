package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class EditorEngineImpl implements EditorEngine
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Buffer buffer;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Clipboard clipboard;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Selection selection;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private EnterText enterText;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private AppEditor appEditor;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ChangeSelection changeSelection;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Cut cut;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Paste paste;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Copy copy;
	
	/**
	 * @param buffer
	 * @param clipboard
	 * @param selection
	 * @param enterText
	 * @param appEditor
	 * @param changeSelection
	 * @param cut
	 * @param paste
	 * @param copy
	 */
	public EditorEngineImpl() {
		this.buffer = new Buffer(this);
		this.clipboard = new Clipboard(this);
		this.selection = new Selection(this);
		/*
		this.appEditor = appEditor;
		this.enterText = enterText;
		this.changeSelection = changeSelection;
		this.cut = cut;
		this.paste = paste;
		this.copy = copy;
		*/
	}

	private void setSelectionText(String text) {
		int start = selection.getStart();
		int end = selection.getEnd();
		buffer.setContent(start, end, text);
		selection.setLength(text.length());	
	}
	
	private String getSelectionText() {
		int start = selection.getStart();
		int end = selection.getEnd();
		return buffer.getContent(start, end);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void changeSelection(int start, int end) {
		selection.setStart(start);
		selection.setEnd(end);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void copy() {
		String text = getSelectionText();
		clipboard.setText(text);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void enterText(String text) {
		setSelectionText(text);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void paste() {
		String text = clipboard.getText();	
		setSelectionText(text);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void cut() {
		String text = getSelectionText();
		clipboard.setText(text);
		setSelectionText("");
	}
	
}

