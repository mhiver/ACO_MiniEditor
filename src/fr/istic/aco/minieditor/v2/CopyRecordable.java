package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.EditorEngine;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Copy
 * 
 * Hérite de la classe CommandRecordable
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class CopyRecordable extends CommandRecordable implements Recordable {

	/* 
	 * editorEngine joue le rôle de receveur dans le patron de conception Command
	 */
	
	private EditorEngine editorEngine;

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private Recorder recorder;
	
	
	/**
	 * editorEngine doit être non nul
	 * 
	 * @param editorEngine
	 */
	public CopyRecordable(EditorEngine editorEngine, Recorder recorder) {
		this.recorder = recorder;
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
		editorEngine.copy();
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Copy";
		
	}

}
