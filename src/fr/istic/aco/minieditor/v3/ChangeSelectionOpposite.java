package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande ChangeSelection
 * 
 * commande inverse liée à ChangeSelection
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class ChangeSelectionOpposite extends CommandOpposite implements Recordable {

	/* commande inverse de this*/
	private ChangeSelectionUndoable changeSelectionUndoable;
	
	/* receveur de la commande */
	private EditorEngine editorEngine; 
	
	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager;
	
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
	
	public ChangeSelectionOpposite(EditorEngine editorEngine,
			UndoRedoManager undoRedoManager, ChangeSelectionUndoable changeSelectionUndoable) {
		this.editorEngine = editorEngine;
		this.undoRedoManager = undoRedoManager;
		this.changeSelectionUndoable = changeSelectionUndoable;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {	
		int savedOldStartSelection = this.editorEngine.getStartSelection();
		int savedOldEndSelection = this.editorEngine.getEndSelection();
		
		Memento m = new MemChangeSelectionOpposite(savedOldStartSelection, savedOldEndSelection);
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		Object[] savedState = (Object[]) m.getSavedState();
		this.oldStartSelection = (int) savedState[0];
		this.oldEndSelection = (int) savedState[1]; 
	}

	/* va sauvegarder la commande pour la macro */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
		if(!undoRedoManager.getIsInRedoAll()) {
			undoRedoManager.record(changeSelectionUndoable);
			editorEngine.changeSelectionOpposite(oldStartSelection,oldEndSelection);
		} else {
			undoRedoManager.record(changeSelectionUndoable);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.Command#getName()
	 */
	/* pas de nom car command non visible par l'utilisateur 
	 * */
	@Override
	public String getName() {
		
		return null;
	}
	
	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	private class MemChangeSelectionOpposite implements Memento {
		
		/* ancien debut de selection */
		private int savedOldStartSelection;
		
		/* ancienne fin de selection */
		private int savedOldEndSelection;

		public MemChangeSelectionOpposite(int savedOldStartSelection, int savedOldEndSelection) {
			this.savedOldStartSelection = savedOldStartSelection;
			this.savedOldEndSelection = savedOldEndSelection;
		}

		/*
		 * (non-Javadoc)
		 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
		 */
		@Override
		public Object getSavedState() {
			Object[] savedState = new Object[2];
			savedState[0] = this.savedOldStartSelection;
			savedState[1] = this.savedOldEndSelection; 
			
			return savedState;
		}
		
	}
}
