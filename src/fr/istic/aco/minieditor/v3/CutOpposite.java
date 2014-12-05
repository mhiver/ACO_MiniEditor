package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande cut
 * 
 * commande inverse liée à cut
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class CutOpposite extends CommandOpposite implements Recordable {

	/* commande inverse de this*/
	private CutUndoable cutUndoable;
	
	/* receveur de la commande */
	private EditorEngine editorEngine; 
	
	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager;
	
	/* text à remettre dans le clipBoard */
	private String clipBoardText;
	
	/* text à remettre dans le clipBoard */
	private String textInBuffer;
	
	/* text à remettre dans le clipBoard */
	private int oldStartSelection;
	
	/* text à remettre dans le clipBoard */
	private int oldEndSelection;
	
	/**
	 * editorEngine doit être non nul
	 * undoRedoManager doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param undoRedoManager
	 */
	
	public CutOpposite(EditorEngine editorEngine,
			UndoRedoManager undoRedoManager, CutUndoable cutUndoable) {
		this.editorEngine = editorEngine;
		this.undoRedoManager = undoRedoManager;
		this.cutUndoable = cutUndoable;
		
		this.clipBoardText = "";
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {	
		String savedClipBoardText = this.editorEngine.getClipboardText();
		String  savedTextInBuffer= this.editorEngine.getSelectionText();
		int savedOldStartSelection = this.editorEngine.getStartSelection();
		int savedOldEndSelection = this.editorEngine.getEndSelection();
		
		Memento m = new MemCutOpposite(savedClipBoardText, savedTextInBuffer, savedOldStartSelection, savedOldEndSelection);
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		Object[] savedState = (Object[]) m.getSavedState();
		this.clipBoardText = (String) savedState[0];
		this.textInBuffer = (String) savedState[1];
		this.oldStartSelection = (int) savedState[2];
		this.oldEndSelection = (int) savedState[3]; 
	}

	/* va sauvegarder la commande pour la macro */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
		undoRedoManager.record(cutUndoable);
		editorEngine.cutOpposite(clipBoardText,textInBuffer,oldStartSelection,oldEndSelection);
	}
	
	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	private class MemCutOpposite implements Memento {

		
		/* text à remettre dans le clipBoard */
		private String savedClipBoardText;
		
		/* text à remettre dans le clipBoard */
		private String savedTextInBuffer;
		
		/* text à remettre dans le clipBoard */
		private int savedOldStartSelection;
		
		/* text à remettre dans le clipBoard */
		private int savedOldEndSelection;

		public MemCutOpposite(String savedClipBoardText, String savedTextInBuffer,
				int savedOldStartSelection, int savedOldEndSelection) {
			this.savedClipBoardText = savedClipBoardText;
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
			Object[] savedState = new Object[4];
			savedState[0] = this.savedClipBoardText;
			savedState[1] = this.savedTextInBuffer;
			savedState[2] = this.savedOldStartSelection;
			savedState[3] = this.savedOldEndSelection; 
			
			return savedState;
		}
		
	}
}
