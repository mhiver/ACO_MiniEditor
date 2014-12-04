package fr.istic.aco.minieditor;


/**
 * Implémentation de l'interface EditorEngine
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class EditorEngineImpl implements EditorEngine
{
	/* Joue le rôle de buffer qui contiendra le texte de l'éditeur */
	
	protected Buffer buffer;
	

	/* Joue le rôle de presse papier qui contiendra le texte du presse papier */
	
	protected Clipboard clipboard;
	

	/* Joue le rôle de selection qui le début et la fin de la sélection */
	
	protected Selection selection;
	
	
	/**
	 * @return le buffer
	 */
	public Buffer getBuffer() {
		return buffer;
	}

	/**
	 * @return le clipboard
	 */
	public Clipboard getClipboard() {
		return clipboard;
	}

	/**
	 * @return la selection
	 */
	public Selection getSelection() {
		return selection;
	}

	/**
	 * créer des nouveaux objets Buffer, Clipboard et Selection
	 * 
	 */
	public EditorEngineImpl() {
		this.buffer = new Buffer();
		this.clipboard = new Clipboard();
		this.selection = new Selection();
	}

	/**
	 * Remplace le texte défini par la sélection dans le buffer par text
	 * 
	 * Modifie ensuite la sélection pour la positionnée à la fin du texte entré
	 * 
	 * @param String text
	 */
	private void setSelectionText(String text) {
		int start = selection.getStart();
		int end = selection.getEnd();
		buffer.setContent(start, end, text);
		
		int newPosition = start + text.length();
		selection.setStart(newPosition);
		selection.setEnd(newPosition);	
	}

	/**
	 * Retourne le texte défini par la sélection dans le buffer
	 * 
	 * @return String text
	 */
	private String getSelectionText() {
		int start = selection.getStart();
		int end = selection.getEnd();
		return buffer.getContent(start, end);
	}



	/**
	 * @see fr.istic.aco.minieditor.EditorEngine#changeSelection(int start, int end)
	 */
	@Override
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
	 * @see fr.istic.aco.minieditor.EditorEngine#copy()
	 */
	@Override
	public void copy() {
		String text = getSelectionText();
		
		if (!text.equals(""))
			clipboard.setText(text);
	}


	/**
	 * @see fr.istic.aco.minieditor.EditorEngine#enterText(String text)
	 */
	@Override
	public void enterText(String text) {
		setSelectionText(text);
	}
	


	/**
	 * @see fr.istic.aco.minieditor.EditorEngine#paste()
	 */
	@Override
	public void paste() {
		String text = clipboard.getText();	
		setSelectionText(text);
	}
	


	/**
	 * @see fr.istic.aco.minieditor.EditorEngine#cut()
	 */
	@Override
	public void cut() {
		String text = getSelectionText();
		
		if (!text.equals("")) {
			clipboard.setText(text);
			setSelectionText("");
		}
	}


	/**
	 * @see fr.istic.aco.minieditor.EditorEngine#printData()
	 */
	@Override
	public String printData() {
		int start = selection.getStart();
		int end = selection.getEnd();
		StringBuffer buff = buffer.getBuffer();
		buff.insert(end, ']');
		buff.insert(start, '[');
		
		buff.insert(0, "Buffer:\n");
		buff.append("\nClipboard:\n");
		buff.append(clipboard.getText());
		buff.append("\n");
		
		return buff.toString();
	}

}

