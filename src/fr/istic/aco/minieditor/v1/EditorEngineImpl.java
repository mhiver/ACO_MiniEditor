package fr.istic.aco.minieditor.v1;


/**
 * Implémentation de l'interface EditorEngine
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

/**
 * @author 12001247
 *
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
		buffer = new Buffer();
		clipboard = new Clipboard();
		selection = new Selection();
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
	@Override
	public String getSelectionText() {
		int start = selection.getStart();
		int end = selection.getEnd();
		return buffer.getContent(start, end);
	}



	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#changeSelection(int, int)
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



	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#copy()
	 */
	@Override
	public void copy() {
		String text = getSelectionText();
		
		if (!text.equals(""))
			clipboard.setText(text);
	}


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#enterText(java.lang.String)
	 */
	@Override
	public void enterText(String text) {
		setSelectionText(text);
	}
	


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#paste()
	 */
	@Override
	public void paste() {
		String text = clipboard.getText();	
		setSelectionText(text);
	}
	


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#cut()
	 */
	@Override
	public void cut() {
		String text = getSelectionText();
		
		if (!text.equals("")) {
			clipboard.setText(text);
			setSelectionText("");
		}
	}


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#printData()
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

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#getClipboardText()
	 */
	@Override
	public String getClipboardText() {
		return clipboard.getText();
	}
	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#getStartSelection()
	 */
	@Override
	public int getStartSelection() {
		return selection.getStart();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#getEndSelection()
	 */
	@Override
	public int getEndSelection() {
		return selection.getEnd();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#copyOpposite(java.lang.String)
	 */
	@Override
	public void copyOpposite(String clipBoardText) {
		clipboard.setText(clipBoardText);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#cutOpposite(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public void cutOpposite(String clipBoardText, String textInBuffer,
			int oldStartSelection, int oldEndSelection) {

		int start = selection.getStart();
		int end = selection.getEnd();
		
		clipboard.setText(clipBoardText);
		buffer.setContent(start, end, textInBuffer);
		selection.setStart(oldStartSelection);
		selection.setEnd(oldEndSelection);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#enterTextOpposite(java.lang.String, int, int)
	 */
	@Override
	public void enterTextOpposite(String textInBuffer,
			int oldStartSelection, int oldEndSelection) {
		selection.setStart(oldStartSelection);
		buffer.setContent(selection.getStart(), selection.getEnd(), textInBuffer);
		selection.setEnd(oldEndSelection);
		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#changeSelectionOpposite(int, int)
	 */
	@Override
	public void changeSelectionOpposite(int oldStartSelection,
			int oldEndSelection) {
		selection.setStart(oldStartSelection);
		selection.setEnd(oldEndSelection);
		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.EditorEngine#pasteOpposite(java.lang.String, int, int)
	 */
	@Override
	public void pasteOpposite(String textInBuffer, int oldStartSelection,
			int oldEndSelection) {
		selection.setStart(oldStartSelection);
		buffer.setContent(selection.getStart(), selection.getEnd(), textInBuffer);
		selection.setEnd(oldEndSelection);
		
	}

}

