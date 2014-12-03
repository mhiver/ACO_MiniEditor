package fr.istic.aco.minieditor;


/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete Quit
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class Quit implements Command
{	
	/*
	 * UI joue les rôles d'invoqueur et de receveur
	 *  dans le patron de conception Command
	 */
	
	private UI uI;
	
	
	/**
	 * uI doit être non nul
	 * 
	 * @param uI
	 */
	public Quit(UI uI) {
		this.uI = uI;
	}

	/*
	 * va stoper la boucle de l'invoker, ici l'uI
	 * 
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override
	public void execute() {
		uI.stopLoop();
	}


	/*
	 * @return "Quit"
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "Quit";
	}
	
}

