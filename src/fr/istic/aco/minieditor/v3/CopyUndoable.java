package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.Copy;
import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Copy
 * 
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class CopyUndoable extends Copy implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager; 
	
	/*
	 * commande inverse a copy
	 */
	
	private CopyOpposite copyOppositeUndoable;
	
	
	/**
	 * editorEngine doit être non nul
	 * undoRedoManager doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param undoRedoManager
	 */
	public CopyUndoable(EditorEngine editorEngine, UndoRedoManager undoRedoManager) {
		super(editorEngine);
		this.undoRedoManager = undoRedoManager;
		copyOppositeUndoable = new CopyOpposite(editorEngine, undoRedoManager, this);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {	
		Memento m = new MemCopy();
		
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
			undoRedoManager.record(copyOppositeUndoable);
			super.execute();
	}

	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	
	private class MemCopy implements Memento {

		@Override
		public Object getSavedState() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
