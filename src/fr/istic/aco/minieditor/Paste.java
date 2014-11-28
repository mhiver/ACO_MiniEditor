package fr.istic.aco.minieditor;


/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete Paste
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class Paste implements Command
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
	public Paste(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/**
	 * méthode qui va remplacer la selection courante par le contenu du clipboard courant
	 */

	@Override
	public void execute() {
		editorEngine.paste();
	}

	/**
	 * retourne "Paste"
	 */

	@Override
	public String getName() {
		return "Paste";
	}
	
}

