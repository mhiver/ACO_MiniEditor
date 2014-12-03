package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.EditorEngine;
import fr.istic.aco.minieditor.UI;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande ChangeSelection
 * 
 * Hérite de la classe CommandRecordable 
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class ChangeSelectionRecordable extends CommandRecordable implements Recordable {

	/*
	 * attribut qui servira à definir l'une des borne de la sélection
	 * 
	 * start >= 0
	 */
	
	private int start;
	

	/*
	 * attribut qui servira à definir l'une des borne de la sélection
	 * 
	 * end >= 0
	 */
	
	private int end;
	
	/* 
	 * editorEngine joue le rôle de receveur dans le patron de conception Command
	 */
	
	private EditorEngine editorEngine;
	
	/*
	 * UI joue le rôle de d'invoqueur dans le patron de conception Command
	 */
	
	private UI uI;
	
	/**
	 * editorEngine doit être non nul
	 * uI doit être non nul
	 * 
	 * @param editorEngine
	 * @param uI
	 */
	public ChangeSelectionRecordable(EditorEngine editorEngine, UI uI) {
		this.editorEngine = editorEngine;
		this.uI = uI;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {		
		start = uI.getStart();
		end = uI.getEnd();
		
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
	 * 
	 */

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override	
	public void execute() {
		editorEngine.changeSelection(start, end);
		
	}


	/*
	 * @return "Change selection"
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Change selection";
	}

}
