package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.EditorEngineImpl;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié au moteur éditeur
 * 
 * Hérite de la classe EditorEngineImpl car le but de cette classe est spécifiquement d'enregistrer un état du moteur éditeur
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class EditorEngineImplRecordable extends EditorEngineImpl implements Recordable {
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {

		StringBuffer bufferString = this.getBuffer().getBuffer();
		String clipboardText = this.getClipboard().getText();
		int selectionStart = this.getSelection().getStart();
		int selectionEnd = this.getSelection().getEnd();
		
		Memento m = new MemEditorEngineImpl(bufferString, clipboardText, selectionStart, selectionEnd);
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		
		Object[] savedState = (Object[]) m.getSavedState();
		
		StringBuffer bufferString = (StringBuffer) savedState[0];
		String clipboardText = (String) savedState[1];
		int selectionStart = (int) savedState[2];
		int selectionEnd = (int) savedState[3];
		
		this.buffer.setContent(0, this.buffer.getLength(), bufferString.toString());
		this.clipboard.setText(clipboardText);
		this.selection.setStart(selectionStart);
		this.selection.setEnd(selectionEnd);
		
	}

}
