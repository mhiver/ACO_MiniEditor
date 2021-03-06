package fr.istic.aco.minieditor.v1;


/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete Cut
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class Cut implements Command
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
	public Cut(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/*
	 * méthode qui va copier la sélection courante dans le clipboard puis 
	 * remplacer la sélection par ""
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.Command#execute()
	 */
	@Override
	public void execute() {
		editorEngine.cut();
	}


	/*
	 * @return "Cut"
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.Command#getName()
	 */
	@Override
	public String getName() {
		return "Cut";
	}
	
}

