package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.EditorEngine;

/**
 * 
 * Implémente l'interface Recordable afin de sauvegarder un memento lié à la commande Paste
 * 
 * 
 * Hérite de la classe CommandRecordable
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class PasteRecordable extends CommandRecordable implements Recordable {

	/* 
	 * editorEngine joue le rôle de receveur dans le patron de conception Command
	 */
	
	private EditorEngine editorEngine;
	
	
	/**
	 * editorEngine doit être non nul
	 * 
	 * @param editorEngine
	 */
	public PasteRecordable(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
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
	 */

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override
	public void execute() {
		editorEngine.paste();
	}

	/*
	 * 
	 * @return "Paste"
	 */

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Paste";
	}

}
