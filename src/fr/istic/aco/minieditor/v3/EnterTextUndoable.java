package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.EnterText;
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande EnterText
 * 
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class EnterTextUndoable extends EnterText implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private UndoRedoManager undoRedoManager; 
	
	/*
	 * commande inverse a EnterText
	 */
	
	private EnterTextOpposite enterTextOpposite;
	
	
	/**
	 * editorEngine doit être non nul
	 * uI doit être non nul
	 * undoRedoManager doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param uI 
	 * @param undoRedoManager
	 */
	public EnterTextUndoable(EditorEngine editorEngine, UI uI, UndoRedoManager undoRedoManager) {
		super(editorEngine, uI);
		this.undoRedoManager = undoRedoManager;
		enterTextOpposite = new EnterTextOpposite(editorEngine, undoRedoManager, this);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {
		
		Memento m = new MemEnterText(text);
		
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		text = (String) m.getSavedState();
		
	}

	/* va sauvegarder la commande pour la macro */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
			
		if (!undoRedoManager.getIsInRedo()) {
			undoRedoManager.record(enterTextOpposite);
			super.execute();
		} else {
			undoRedoManager.record(enterTextOpposite);
			editorEngine.enterText(text);
		}
	}

	/**
	 * classe qui joue le rôle de memento concret
	 *
	 *
	 */
	
	private class MemEnterText implements Memento {
		
		private String text;
		
		public MemEnterText(String _text) {
			text = _text;
		}

		/*
		 * (non-Javadoc)
		 * @see fr.istic.aco.minieditor.v2.Memento#getSavedState()
		 */
		@Override
		public Object getSavedState() {
			return this.text;
		}
		
	}

}
