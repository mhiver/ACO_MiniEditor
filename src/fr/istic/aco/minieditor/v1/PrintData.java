package fr.istic.aco.minieditor.v1;


/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete PrintData
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */
public class PrintData implements Command {

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
	 * 
	 * @param editorEngine
	 */
	public PrintData(EditorEngine editorEngine, UI uI) {
		this.editorEngine = editorEngine;
		this.uI = uI;
	}

	/*
	 * va afficher l'etat courant des donnees de l'editeur de texte
	 */
	
	/**
	 * @see fr.istic.aco.minieditor.v1.Command#execute()
	 */
	@Override	
	public void execute() {
		uI.printText(editorEngine.printData());
	}

	/*
	 * @return "Print data"
	 */
	
	/**
	 * @see fr.istic.aco.minieditor.v1.Command#getName()
	 */
	@Override
	public String getName() {
		return "Print data";
	}

}
