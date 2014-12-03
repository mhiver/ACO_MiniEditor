package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.EditorEngine;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Cut
 * 
 * Hérite de la classe CommandRecordable
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class CutRecordable extends CommandRecordable implements Recordable {
	
	/* 
	 * editorEngine joue le rôle de receveur dans le patron de conception Command
	 */
	
	private EditorEngine editorEngine;
	
	
	/**
	 * editorEngine doit être non nul
	 * 
	 * @param editorEngine
	 */
	public CutRecordable(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
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

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override
	public void execute() {
		editorEngine.cut();
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Cut";
	}
	
}
