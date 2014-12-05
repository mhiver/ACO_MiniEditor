package fr.istic.aco.minieditor.v1;


/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete Change Selection
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class ChangeSelection implements Command
{
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
	public ChangeSelection(EditorEngine editorEngine, UI uI) {
		this.editorEngine = editorEngine;
		this.uI = uI;
	}


	/*
	 * va mettre à jour ses attributs start et end grâce à l'invoker uI
	 * 
	 * va changer la sélection courante grâce à start et end mis à jour
	 * 
	 * 
	 */

	/**
	 * @see fr.istic.aco.minieditor.v1.Command#execute()
	 */
	@Override	
	public void execute() {
		start = uI.getStart();
		end = uI.getEnd();
		
		editorEngine.changeSelection(start, end);
	}


	/*
	 * @return "Change selection"
	 */
	
	/**
	 * @see fr.istic.aco.minieditor.v1.Command#getName()
	 */
	@Override
	public String getName() {
		return "Change selection";
	}
	
}

