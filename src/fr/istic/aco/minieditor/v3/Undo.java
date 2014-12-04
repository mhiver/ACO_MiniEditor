package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.Command;

/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete Undo
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.3
 */

public class Undo implements Command {

	/* 
	 * undoRedoManager joue le rôle de receveur dans le patron de conception Command
	 * 
	 */
	UndoRedoManager undoRedoManager;


	/**
	 * undoRedoManager doit être non nul
	 * 
	 * @param undoRedoManager
	 */
	public Undo(UndoRedoManager undoRedoManager) {
		this.undoRedoManager = undoRedoManager;
	}
	
	@Override
	public void execute() {
		undoRedoManager.undo();
		
	}

	@Override
	public String getName() {
		return "Undo";
	}

}
