package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.Paste;

/**
 * 
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Paste
 * 
 * 
 * Hérite de la classe Paste car le but de cette classe est spécifiquement d'enregistrer cette commande
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class PasteRecordable extends Paste implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private Recorder recorder;
	
	
	/**
	 * editorEngine doit être non nul
	 * recorder doit être non nul
	 * 
	 * @param editorEngine
	 * @param recorder
	 */
	public PasteRecordable(EditorEngine editorEngine, Recorder recorder) {
		super(editorEngine);
		this.recorder = recorder;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {
		Memento m = new MemPaste();
		return m;
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#setMemento(Memento m)
	 */
	@Override
	public void setMemento(Memento m) {
		
	}

	/*
	 * méthode qui va remplacer la selection courante par le contenu du clipboard courant
	 * va sauvegarder la commande pour la macro 
	 */

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
