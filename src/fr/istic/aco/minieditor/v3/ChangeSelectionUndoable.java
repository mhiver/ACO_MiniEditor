package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.ChangeSelection;
import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande ChangeSelection
 * 
 * 
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class ChangeSelectionUndoable extends ChangeSelection implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager; 
	
	private ChangeSelectionOpposite changeSelectionOppositeUndoable;
	
	
	/**
	 * editorEngine doit être non nul
	 * undoRedoManager doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param undoRedoManager
	 */
	public ChangeSelectionUndoable(EditorEngine editorEngine, UI uI, UndoRedoManager undoRedoManager) {
		super(editorEngine, uI);
		this.undoRedoManager = undoRedoManager;
		changeSelectionOppositeUndoable = new ChangeSelectionOpposite(editorEngine, undoRedoManager, this);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {		
		
		Memento m = new MemChangeSelection(start, end);
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		int[] state = (int[]) m.getSavedState();
		
		start = state[0];
		end = state[1];
		
	}

	/* va sauvegarder la commande pour la macro */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
		
			if (!undoRedoManager.getIsInRedo()) {
				undoRedoManager.record(changeSelectionOppositeUndoable);
				super.execute();
			} else {
				undoRedoManager.record(changeSelectionOppositeUndoable);
				editorEngine.changeSelection(start, end);
			}
			
	}

	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	
	private class MemChangeSelection implements Memento {
		
		/* start précédent */
		private int oldStart;
		
		/* end précédent */
		private int oldEnd;
		

		/**
		 * 
		 * paramètre qui en entrée qui vont permettre de réappliquer la commande
		 * 
		 * @param _start
		 * @param _end
		 */
		
		public MemChangeSelection(int _start, int _end) {
			oldStart = _start;
			oldEnd = _end;
		}

		/* (non-Javadoc)
		 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
		 */
		@Override
		public Object getSavedState() {
			int[] result = new int[2];
			result[0] = oldStart;
			result[1] = oldEnd;
			return result;
		}
		
	}

}
