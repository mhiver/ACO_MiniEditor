package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Paste
 * 
 * Commande inverse liée à Paste
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class PasteOpposite extends CommandOpposite implements Recordable {

	/* commande inverse de this*/
	private PasteUndoable pasteUndoable;
	
	/* receveur de la commande */
	private EditorEngine editorEngine; 
	
	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager;
	
	/* texte à remettre dans le buffer */
	private String textInBuffer;
	
	/* début de sélection à restaurer */
	private int oldStartSelection;
	
	/* fin de sélection à restaurer */
	private int oldEndSelection;
	
	
	/**
	 * editorEngine doit être non nul
	 * undoRedoManager doit être non nul
	 * pasteUndoable doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param undoRedoManager
	 * @param pasteUndoable
	 */
	
	public PasteOpposite(EditorEngine editorEngine,
			UndoRedoManager undoRedoManager, PasteUndoable pasteUndoable) {
		this.editorEngine = editorEngine;
		this.undoRedoManager = undoRedoManager;
		this.pasteUndoable = pasteUndoable;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {	
		String  savedTextInBuffer= this.editorEngine.getSelectionText();
		int savedOldStartSelection = this.editorEngine.getStartSelection();
		int savedOldEndSelection = this.editorEngine.getEndSelection();
		
		Memento m = new MemPasteOpposite(savedTextInBuffer, savedOldStartSelection, savedOldEndSelection);
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		Object[] savedState = (Object[]) m.getSavedState();
		this.textInBuffer = (String) savedState[0];
		this.oldStartSelection = (int) savedState[1];
		this.oldEndSelection = (int) savedState[2]; 
	}

	/* va sauvegarder la commande pour la macro */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
		undoRedoManager.record(pasteUndoable);
		editorEngine.pasteOpposite(textInBuffer,oldStartSelection,oldEndSelection);
	}
	
	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	private class MemPasteOpposite implements Memento {
		
		private String savedTextInBuffer;
		private int savedOldStartSelection;
		private int savedOldEndSelection;
		
		public MemPasteOpposite(String savedTextInBuffer, int savedOldStartSelection, int savedOldEndSelection) {
			this.savedTextInBuffer = savedTextInBuffer;
			this.savedOldStartSelection = savedOldStartSelection;
			this.savedOldEndSelection = savedOldEndSelection;
		}
		
		/*
		 * (non-Javadoc)
		 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
		 */
		@Override
		public Object getSavedState() {
			Object[] savedState = new Object[3];
			savedState[0] = this.savedTextInBuffer;
			savedState[1] = this.savedOldStartSelection;
			savedState[2] = this.savedOldEndSelection; 
			
			return savedState;
			
		}
		
	}
}
