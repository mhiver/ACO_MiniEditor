package fr.istic.aco.minieditor;


/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete EnterText
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class EnterText implements Command
{
	/*
	 * attribut qui servira à definir le nouveau String pour remplacer la selection courante
	 * 
	 */
	
	protected String text;

	
	/* 
	 * editorEngine joue le rôle de receveur dans le patron de conception Command
	 */
	
	protected EditorEngine editorEngine;
	
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
	public EnterText(EditorEngine editorEngine, UI uI) {
		this.editorEngine = editorEngine;
		this.uI = uI;
	}

	/*
	 * va mettre à jour son attribut text grâce à l'invoker uI
	 * 
	 * va remplacer la sélection courante par text mis à jour puis va positionner
	 * la selection courante à la fin du du texte juste insérer telle que sa taille
	 * sera nulle
	 * 
	 */
	
	/**
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override
	public void execute() {
		text = uI.getText();
		editorEngine.enterText(text);
	}


	/*
	 * @return "Enter text"
	 */
	
	/**
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Enter text";
	}
	
}

