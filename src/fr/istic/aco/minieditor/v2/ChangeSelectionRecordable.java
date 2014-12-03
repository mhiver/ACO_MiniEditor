package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.ChangeSelection;
import fr.istic.aco.minieditor.EditorEngine;
import fr.istic.aco.minieditor.UI;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande ChangeSelection
 * 
 * Hérite de la classe ChangeSelection car le but de cette classe est spécifiquement d'enregistrer cette commande
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class ChangeSelectionRecordable extends ChangeSelection implements Recordable {

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
	public ChangeSelectionRecordable(EditorEngine editorEngine, UI uI, Recorder recorder) {
		super(editorEngine,uI);
		this.recorder = recorder;
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

	/*
	 * va mettre à jour ses attributs start et end grâce à l'invoker uI
	 * 
	 * va changer la sélection courante grâce à start et end mis à jour
	 * 
	 * va sauvegarder la commande pour la macro
	 */

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	
	public void execute() {
		if (recorder.getRecording() == true) {
			super.execute();
			recorder.record(this);
		} else if (recorder.getReplaying() == true){
			editorEngine.changeSelection(start, end);
		} else {
			super.execute();
		}
	}

}
