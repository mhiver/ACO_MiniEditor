package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.Paste;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Paste
 * 
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class PasteUndoable extends Paste implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager; 
	
	/*
	 * commande inverse a EnterText
	 */
	
	private PasteOpposite pasteOpposite;
	
	
	/**
	 * editorEngine doit être non nul
	 * undoRedoManager doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param undoRedoManager
	 */
	public PasteUndoable(EditorEngine editorEngine, UndoRedoManager undoRedoManager) {
		super(editorEngine);
		this.undoRedoManager = undoRedoManager;
		this.pasteOpposite = new PasteOpposite(editorEngine, undoRedoManager, this);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {		
		Memento m = new MemPaste();
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		
	}

	/* va sauvegarder la commande pour la macro */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
			undoRedoManager.record(pasteOpposite);
			super.execute();
	}

	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	
	private class MemPaste implements Memento {

		/*
		 * (non-Javadoc)
		 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
		 */
		@Override
		public Object getSavedState() {
			return null;
		}
		
	}

}
