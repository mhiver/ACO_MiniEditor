package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.Cut;
import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Cut
 * 
 * 
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class CutUndoable extends Cut implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager; 
	
	private CutOpposite cutOppositeUndoable;
	
	
	/**
	 * editorEngine doit être non nul
	 * undoRedoManager doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param undoRedoManager
	 */
	public CutUndoable(EditorEngine editorEngine, UndoRedoManager undoRedoManager) {
		super(editorEngine);
		this.undoRedoManager = undoRedoManager;
		cutOppositeUndoable = new CutOpposite(editorEngine, undoRedoManager, this);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {	
		Memento m = new MemCut();
		
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
			undoRedoManager.record(cutOppositeUndoable);
			super.execute();
	}

	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	
	private class MemCut implements Memento {

		@Override
		public Object getSavedState() {
			return null;
		}
		
	}

}
