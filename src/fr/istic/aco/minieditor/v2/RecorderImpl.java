package fr.istic.aco.minieditor.v2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.istic.aco.minieditor.Buffer;
import fr.istic.aco.minieditor.Clipboard;
import fr.istic.aco.minieditor.Command;
import fr.istic.aco.minieditor.Selection;

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
	


	/**
	 * créer des nouveaux objets Buffer, Clipboard et Selection
	 * 
	 */
	public RecorderImpl() {
		this.macro = new LinkedList<Recordable>();
		this.nextMacro = new LinkedList<Recordable>();
		this.mementos = new LinkedList<Memento>();
		this.nextMementos = new LinkedList<Memento>();
		this.recording = false;
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
		
		if (recording == true) {
			throw new IllegalArgumentException("Begin when recording");
		}
		
		recording = true;
		
		nextMacro = new LinkedList<Recordable>();
		nextMementos = new LinkedList<Memento>();
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#end()
	 */
	@Override	
	public void end() {
		
		if (recording == false) {
			throw new IllegalArgumentException("End when not recording");
		}
		
		recording = false;
		
		macro = new LinkedList<Recordable>(nextMacro);
		mementos = new LinkedList<Memento>(nextMementos);
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#replay()
	 */
	@Override	
	public void replay() {
		
		if (recording == true) {
			throw new IllegalArgumentException("Replay when recording");
		}
		
		while (macro.size() + mementos.size() > 0 ) {
			Memento m = ((LinkedList<Memento>) mementos).pop();
			Recordable cmd = ((LinkedList<Recordable>) macro).pop();
			
			cmd.setMemento(m);
			((Command) cmd).execute();
		}		
	}


	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#getRecording()
	 */
	@Override	
	public boolean getRecording() {
		return recording;
	}

}
