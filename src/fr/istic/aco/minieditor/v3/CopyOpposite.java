package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Copy
 * 
 * Commande inverse liée à copy
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class CopyOpposite extends CommandOpposite implements Recordable {

	/* commande inverse de this*/
	private CopyUndoable copyUndoable;
	
	/* receveur de la commande */
	private EditorEngine editorEngine; 
	
	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager;
	
	/* texte à remettre dans le clipBoard */
	private String clipBoardText;
	
	/**
	 * editorEngine doit être non nul
	 * undoRedoManager doit être non nul
	 * copyUndoable doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param undoRedoManager
	 * @param copyUndoable
	 */
	
	public CopyOpposite(EditorEngine editorEngine,
			UndoRedoManager undoRedoManager, CopyUndoable copyUndoable) {
		this.editorEngine = editorEngine;
		this.undoRedoManager = undoRedoManager;
		this.copyUndoable = copyUndoable;
		
		this.clipBoardText = "";
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {	
		String clipboardText = this.editorEngine.getClipboardText();
		Memento m = new MemCopyOpposite(clipboardText);
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		String text = (String) m.getSavedState();
		
		this.clipBoardText = text;
	}

	/* va sauvegarder la commande pour la macro */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
		undoRedoManager.record(copyUndoable);
		editorEngine.copyOpposite(this.clipBoardText);
	}
	
	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	private class MemCopyOpposite implements Memento {
		
		private String savedClipBoardText;
		
		public MemCopyOpposite(String savedClipBoardText) {
			this.savedClipBoardText = savedClipBoardText;
		}
		
		/*
		 * (non-Javadoc)
		 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
		 */
		@Override
		public Object getSavedState() {
			return this.savedClipBoardText;
			
		}
		
	}
}
