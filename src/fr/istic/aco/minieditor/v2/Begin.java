package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.Command;
import fr.istic.aco.minieditor.EditorEngine;

/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete PrintData
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */
public class Begin implements Command {

	/* 
	 * editorEngine joue le rôle de receveur dans le patron de conception Command
	 */
	
	private EditorEngine editorEngine;
	
	
	/**
	 * editorEngine doit être non nul
	 * 
	 * @param editorEngine
	 */
	public Begin(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
	}

	/*
	 * va afficher l'etat courant des donnees de l'editeur de texte
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override	
	public void execute() {
		editorEngine.printData();
	}

	/*
	 * @return "Print data"
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Print data";
	}

}