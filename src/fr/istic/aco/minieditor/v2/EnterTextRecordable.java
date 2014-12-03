package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.EditorEngine;
import fr.istic.aco.minieditor.UI;

/**
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande EnterTexte
 * 
 * Hérite de la classe CommandRecordable 
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class EnterTextRecordable extends CommandRecordable implements Recordable {

	/*
	 * attribut qui servira à definir le nouveau String pour remplacer la selection courante
	 * 
	 */
	
	private String text;

	
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
	public EnterTextRecordable(EditorEngine editorEngine, UI uI) {
		this.editorEngine = editorEngine;
		this.uI = uI;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recordable#getMemento()
	 */
	@Override
	public Memento getMemento() {
		text = uI.getText();
		
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

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override
	public void execute() {
		editorEngine.enterText(text);
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Enter text";
	}

}
