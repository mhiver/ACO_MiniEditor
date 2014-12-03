package fr.istic.aco.minieditor.v2;

import java.util.ArrayList;
import java.util.List;

import fr.istic.aco.minieditor.Command;

/**
 * Classe qui implémente l'interface Recorder
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class RecorderImpl implements Recorder{
	
	List<Recordable> macro = new ArrayList<Recordable>();
	
	List<Recordable> nextMacro = new ArrayList<Recordable>();
	
	
	

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#record(Recordable cmd)
	 */
	@Override	
	public void record(Recordable cmd) {
		nextMacro.add(cmd);
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#begin()
	 */
	@Override	
	public void begin() {
		nextMacro = new ArrayList<Recordable>();
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#end()
	 */
	@Override	
	public void end() {
		macro = new ArrayList<Recordable>(nextMacro);
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#replay()
	 */
	@Override	
	public void replay() {
		
		for(Recordable r:macro) {
			r.execute();
		}
		
	}

}
