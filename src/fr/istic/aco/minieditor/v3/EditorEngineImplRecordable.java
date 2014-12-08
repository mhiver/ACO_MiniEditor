package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.EditorEngineImpl;
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

		StringBuffer bufferString = getBuffer().getBuffer();
		String clipboardText = getClipboard().getText();
		int selectionStart = getSelection().getStart();
		int selectionEnd = getSelection().getEnd();
		
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
		
		buffer.setContent(0, buffer.getLength(), bufferString.toString());
		clipboard.setText(clipboardText);
		selection.setStart(selectionStart);
		selection.setEnd(selectionEnd);
		
	}

}
