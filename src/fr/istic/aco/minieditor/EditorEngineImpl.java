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
	 * @return the buffer
	 */
	public Buffer getBuffer() {
		return buffer;
	}

	/**
	 * @return the clipboard
	 */
	public Clipboard getClipboard() {
		return clipboard;
	}

	/**
	 * @return the selection
	 */
	public Selection getSelection() {
		return selection;
	}

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
		this.buffer = new Buffer();
		this.clipboard = new Clipboard();
		this.selection = new Selection();
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
		
		int newPosition = start + text.length();
		selection.setStart(newPosition);
		selection.setEnd(newPosition);	
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
		int length = buffer.getLength();

		// If the used entered an invalid number, assume they want to keep the old value 
		if (start < 0)
			start = selection.getStart();
		if (end < 0)
			end = selection.getEnd();
		
		if (start > length)
			start = length;
		if (end > length)
			end = length;
		
		if (start > end) {
			int tmp = start;
			start = end;
			end = tmp;
		}
		
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
		
		if (!text.equals(""))
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
		
		if (!text.equals("")) {
			clipboard.setText(text);
			setSelectionText("");
		}
	}
	
	public void printData() {
		int start = selection.getStart();
		int end = selection.getEnd();
		StringBuffer buff = buffer.getBuffer();
		buff.insert(end, ']');
		buff.insert(start, '[');
		
		buff.insert(0, "Buffer:\n");
		buff.append("\nClipboard:\n");
		buff.append(clipboard.getText());
		buff.append("\n");
		
		System.out.println(buff.toString());
	}

}

