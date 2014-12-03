package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.Command;

/**
 * commande concrète qui implémente l'interface Command et joue le rôle
 * de commande concrete End
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */
public class End implements Command {

	/* 
	 * recorder joue le rôle de receveur dans le patron de conception Command spécifique à l'enregistrement de macro
	 */
	
	private Recorder recorder;
	
	
	/**
	 * recorder doit être non nul
	 * 
	 * @param recorder
	 */
	public End(Recorder _recorder) {
		this.recorder = _recorder;
	}

	/*
	 * va stopper l'enregistrementen de macro en cours
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#execute()
	 */
	@Override	
	public void execute() {
		recorder.begin();
	}

	/*
	 * @return "End macro"
	 */
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#getName()
	 */
	@Override
	public String getName() {
		return "End macro";
	}

}
