package fr.istic.aco.minieditor;


/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete Copy
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */


public class Copy implements Command
{
	/* 
	 * editorEngine joue le rôle de receveur dans le patron de conception Command
	 */
	
	private EditorEngine editorEngine;
	
	
	/**
	 * editorEngine doit être non nul
	 * 
	 * @param editorEngine
	 */
	public Copy(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/*
	 * méthode qui va copier la sélection courante dans le clipboard
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override
	public void execute() {
		editorEngine.copy();
	}


	/*
	 * @return "Copy"
	 */

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Copy";
	}
	
}

