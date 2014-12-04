package fr.istic.aco.minieditor.v2;

import java.util.LinkedList;
import java.util.List;

import fr.istic.aco.minieditor.v1.Command;

/**
 * Classe qui implémente l'interface Recorder
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 */

public class RecorderImpl implements Recorder{

	/* contient la dernière macro enregistrée*/

	private List<Recordable> macro;

	/* contient la macro qui est en train de s'enregistrée */

	private List<Recordable> nextMacro;

	/* contient les memento des commandes de macro */

	private List<Memento> mementos;

	/* contient les memento des commandes de nextMacro */

	private List<Memento> nextMementos;

	/* true si on est en train d'enregistrer une macro */

	private boolean recording;

	/* true si on est en train d'enregistrer une macro */

	private boolean replaying;



	/**
	 * initialise les attributs
	 * 
	 */
	public RecorderImpl() {
		this.macro = new LinkedList<Recordable>();
		this.nextMacro = new LinkedList<Recordable>();
		this.mementos = new LinkedList<Memento>();
		this.nextMementos = new LinkedList<Memento>();
		this.recording = false;
		this.replaying = false;
	}


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#record(Recordable cmd)
	 */
	@Override	
	public void record(Recordable cmd) {

		Memento m = cmd.getMemento();

		((LinkedList<Memento>) nextMementos).addLast(m);
		((LinkedList<Recordable>) nextMacro).addLast(cmd);

	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#begin()
	 */
	@Override	
	public void begin() {

		if (!recording) {
			recording = true;

			nextMacro = new LinkedList<Recordable>();
			nextMementos = new LinkedList<Memento>();
		}

	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#end()
	 */
	@Override	
	public void end() {

		if (recording) {
			recording = false;

			macro = new LinkedList<Recordable>(nextMacro);
			mementos = new LinkedList<Memento>(nextMementos);
		}

	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#replay()
	 */
	@Override	
	public void replay() {

		if (!recording) {

			replaying = true;

			nextMacro = new LinkedList<Recordable>();
			nextMementos = new LinkedList<Memento>();

			while (macro.size() + mementos.size() > 0 ) {
				Memento m = ((LinkedList<Memento>) mementos).pop();
				Recordable cmd = ((LinkedList<Recordable>) macro).pop();

				((LinkedList<Memento>) nextMementos).addLast(m);
				((LinkedList<Recordable>) nextMacro).addLast(cmd);

				cmd.setMemento(m);
				((Command) cmd).execute();
			}	

			replaying = false;

			macro = new LinkedList<Recordable>(nextMacro);
			mementos = new LinkedList<Memento>(nextMementos);

		}

	}


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#getRecording()
	 */
	@Override	
	public boolean getRecording() {
		return recording;
	}


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#getRecording()
	 */
	@Override	
	public boolean getReplaying() {
		return replaying;
	}

}
