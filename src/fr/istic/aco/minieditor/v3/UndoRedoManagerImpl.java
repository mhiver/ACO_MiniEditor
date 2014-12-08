package fr.istic.aco.minieditor.v3;
import java.util.Map;
import java.util.TreeMap;

import fr.istic.aco.minieditor.v1.Command;
import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

public class UndoRedoManagerImpl implements UndoRedoManager {

	/* receveur pour les commandes des listes commandBefore et commandAfter*/
	EditorEngine editorEngine;

	/* liste des états sauvegardés et positionnés avant l'état courant numberCmd */
	private Map<Integer,Memento> editorEngineStatesBefore;
	/* liste des états sauvegardés et positionnés après l'état courant numberCmd */
	private Map<Integer,Memento> editorEngineStatesAfter;

	/* liste des commandes+mementos sauvegardés et positionnés avant l'état courant numberCmd */
	private Map<Integer,Recordable> commandBefore;
	private Map<Integer,Memento> commandBeforeMemento;

	/* liste des commandes+mementos sauvegardés et positionnés après l'état courant numberCmd */
	private Map<Integer,Recordable> commandAfter;
	private Map<Integer,Memento> commandAfterMemento;
	
	/* numéro de l'état courant */
	private int numberCmd;
	
	/* true si on est en train de faire un undo*/
	private boolean isInUndo;
	
	/* true si on est en train de faire un redo*/
	private boolean isInRedo;
	
	/**
	 * initialise tout les attrbuts
	 * 
	 * editor doit être non nul
	 * 
	 * @param editorEngine
	 */
	public UndoRedoManagerImpl(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
		this.numberCmd = 0;
		
		this.editorEngineStatesBefore = new TreeMap<Integer,Memento>();
		this.editorEngineStatesAfter = new TreeMap<Integer,Memento>();
		
		this.commandBefore = new TreeMap<Integer,Recordable>();
		this.commandBeforeMemento = new TreeMap<Integer,Memento>();
		
		this.commandAfter = new TreeMap<Integer,Recordable>();
		this.commandAfterMemento = new TreeMap<Integer,Memento>();
		
		this.isInUndo = false;
		this.isInRedo = false;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#undo()
	 */
	@Override	
	public void undo() {
		if (this.numberCmd > 0) {
			this.numberCmd = this.numberCmd - 1;
			
			if (this.editorEngineStatesBefore.containsKey(this.numberCmd)) {
				Memento stateBeforeUndo = ((Recordable) this.editorEngine).getMemento();
				this.editorEngineStatesAfter.put(this.numberCmd+1, stateBeforeUndo);
				
				Memento m = this.editorEngineStatesBefore.get(this.numberCmd);			
				((Recordable) this.editorEngine).setMemento(m);			
				
				this.editorEngineStatesBefore.remove(this.numberCmd);
				
			} else {
				Memento m = this.commandBeforeMemento.get(this.numberCmd);		
				Recordable cmd = this.commandBefore.get(this.numberCmd);
				

				this.commandBeforeMemento.remove(this.numberCmd);
				this.commandBefore.remove(this.numberCmd);
				cmd.setMemento(m);
				
				this.isInUndo = true;
				((Command) cmd).execute();
				this.isInUndo = false;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#redo()
	 */
	@Override	
	public void redo() {
		
		this.numberCmd = this.numberCmd + 1;
			
		if (this.editorEngineStatesAfter.containsKey(this.numberCmd)) {		
			Memento stateBeforeRedo = ((Recordable) this.editorEngine).getMemento();
			this.editorEngineStatesBefore.put(this.numberCmd-1, stateBeforeRedo);
			
			Memento m = this.editorEngineStatesAfter.get(this.numberCmd);			
			((Recordable) this.editorEngine).setMemento(m);			
			
			this.editorEngineStatesAfter.remove(this.numberCmd);
				
		} else if (this.commandAfter.containsKey(this.numberCmd)) {
			Memento m = this.commandAfterMemento.get(this.numberCmd);		
			Recordable cmd = this.commandAfter.get(this.numberCmd);
			
			this.commandAfterMemento.remove(this.numberCmd);
			this.commandAfter.remove(this.numberCmd);
			
			cmd.setMemento(m);
			
			this.isInRedo = true;
			((Command) cmd).execute();
			this.isInRedo = false;
			
		} else {			
			this.numberCmd = this.numberCmd - 1;
			
		}
			
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#record(Recordable cmd)
	 */
	@Override	
	public void record(Recordable cmd) {
		 
		if (this.isInRedo) {
			this.numberCmd = this.numberCmd -1;
			
			if (savedEditorEngine(this.numberCmd)) {
				Memento m = ((Recordable) this.editorEngine).getMemento();
				
				this.editorEngineStatesBefore.put(this.numberCmd, m);
				
			} else {
				
				Memento m = cmd.getMemento();
	
				this.commandBefore.put(this.numberCmd, cmd);
				this.commandBeforeMemento.put(this.numberCmd, m);
			}
			
			this.numberCmd = this.numberCmd +1;
			
		} else if (this.isInUndo) {
			this.numberCmd = this.numberCmd +1;
			
			if (savedEditorEngine(this.numberCmd)) {
				Memento m = ((Recordable) this.editorEngine).getMemento();
				
				this.editorEngineStatesAfter.put(this.numberCmd, m);
				
			} else {
				
				Memento m = cmd.getMemento();
	
				this.commandAfter.put(this.numberCmd, cmd);
				this.commandAfterMemento.put(this.numberCmd, m);
			}
			
			this.numberCmd = this.numberCmd -1;
			
		} else {
			
			if (savedEditorEngine(this.numberCmd)) {
				Memento m = ((Recordable) this.editorEngine).getMemento();
				
				this.editorEngineStatesBefore.put(this.numberCmd, m);
				
			} else {
				
				Memento m = cmd.getMemento();
	
				this.commandBefore.put(this.numberCmd, cmd);
				this.commandBeforeMemento.put(this.numberCmd, m);
			}
	
				this.editorEngineStatesAfter = new TreeMap<Integer,Memento>();		
				this.commandAfter = new TreeMap<Integer,Recordable>();
				this.commandAfterMemento = new TreeMap<Integer,Memento>();
				this.numberCmd = this.numberCmd +1;
				
		}
		
	}

	/*
	 * oracle qui dit si on sauvegarde une commande ou l'état de la mémoire pour la valeur i
	 * 
	 * @param Integer i
	 * 
	 */
	private boolean savedEditorEngine(Integer i) {
		return ((i % 3) == 0);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v3.UndoRedoManager#getIsInRedo()
	 */
	@Override
	public boolean getIsInRedo() {
		return this.isInRedo;
	}
	

}
