package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.Command;

public abstract class CommandOpposite implements Command {
	
	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v1.Command#getName()
	 */
	/* pas de nom car command non visible par l'utilisateur 
	 * */
	@Override
	public String getName() {
		
		return null;
	}

}
