package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v2.Memento;

/**
 * Implémente l'interface Memento
 * 
 * Joue le rôle de concrete memento dans le patron de conception memento
 * 
 * Mémento associé au moteur éditeur
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class MemEditorEngineImpl implements Memento{

	
	/* attributs qui vont permettre de mémoriser l'état du moteur éditeur */
	
	private StringBuffer bufferString;
	private String clipboardText;
	private int selectionStart;
	private int selectionEnd;
	
	/**
	 * 
	 * paramètre qui en entrée qui vont permettre de se rappeler l'état du moteur éditeur
	 * 
	 * @param bufferString
	 * @param clipboardText
	 * @param selectionStart
	 * @param selectionEnd
	 */
	public MemEditorEngineImpl(StringBuffer bufferString, String clipboardText,
			int selectionStart, int selectionEnd) {
		
		this.bufferString = bufferString;
		this.clipboardText = clipboardText;
		this.selectionStart = selectionStart;
		this.selectionEnd = selectionEnd;
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
	 */
	@Override
	public Object getSavedState() {
		
		Object[] savedState = new Object[4];
		
		savedState[0] = bufferString;
		savedState[1] = clipboardText;
		savedState[2] = selectionStart;
		savedState[3] = selectionEnd;
		
		
		return savedState;
	}

}
