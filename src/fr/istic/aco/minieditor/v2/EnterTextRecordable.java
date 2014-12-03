package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.EditorEngine;
import fr.istic.aco.minieditor.EnterText;
import fr.istic.aco.minieditor.UI;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande EnterTexte
 * 
 * Hérite de la classe EnterText car le but de cette classe est spécifiquement d'enregistrer cette commande
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class EnterTextRecordable extends EnterText implements Recordable {

	/* 
	 * recorder joue le rôle caretaker
	 * 
	 */
	
	private Recorder recorder;
	
	/**
	 * editorEngine doit être non nul
	 * uI doit être non nul
	 * recorder doit être non nul
	 * 
	 * @param editorEngine
	 * @param uI
	 * @param recorder
	 */
	public EnterTextRecordable(EditorEngine editorEngine, UI uI, Recorder recorder) {
		super(editorEngine,uI);
		this.recorder = recorder;
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
		if (recorder.getRecording() == true) {
			super.execute();
			recorder.record(this);
		} else if (recorder.getReplaying() == true){
			editorEngine.enterText(text);
		} else {
			super.execute();
		}
	}

}
