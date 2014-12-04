package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.v1.Copy;
import fr.istic.aco.minieditor.v1.EditorEngine;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Copy
 * 
 * Hérite de la classe Copy car le but de cette classe est spécifiquement d'enregistrer cette commande
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class CopyRecordable extends Copy implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private Recorder recorder;
	
	
	/**
	 * editorEngine doit être non nul
	 * recorder doit être non nul
	 * 
	 * 
	 * @param editorEngine
	 * @param recorder
	 */
	public CopyRecordable(EditorEngine editorEngine, Recorder recorder) {
		super(editorEngine);
		this.recorder = recorder;
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
		if (recorder.getRecording() == true) {
			super.execute();
			recorder.record(this);
		} else {
			super.execute();
		}
	}

}
