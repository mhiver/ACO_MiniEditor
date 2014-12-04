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

public class CutOppositeUndoable extends CommandOpposite implements Recordable {

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
	
	public CutOppositeUndoable(EditorEngine editorEngine,
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
		Memento m = new MemCopyOpposite(this.clipBoardText);
		
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
		editorEngine.copyOppositeUndoable();
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
	public class MemCopyOpposite implements Memento {
		
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
