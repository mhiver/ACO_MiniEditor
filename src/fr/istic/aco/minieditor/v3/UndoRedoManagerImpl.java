package fr.istic.aco.minieditor.v3;
import java.util.Map;
import java.util.TreeMap;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

public class UndoRedoManagerImpl implements UndoRedoManager {

	EditorEngine editorEngine;

	private Map<Integer,Memento> editorEngineStates;

	private Map<Integer,Recordable> commandBefore;
	private Map<Integer,Memento> commandBeforeMemento;

	private Map<Integer,Recordable> commandAfter;
	private Map<Integer,Memento> commandAfterMemento;
	
	int numberCmd;
	
	public UndoRedoManagerImpl(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
		this.numberCmd = 0;
		
		this.editorEngineStates = new TreeMap<Integer,Memento>();
		
		this.commandBefore = new TreeMap<Integer,Recordable>();
		this.commandBeforeMemento = new TreeMap<Integer,Memento>();
		
		this.commandAfter = new TreeMap<Integer,Recordable>();
		this.commandAfterMemento = new TreeMap<Integer,Memento>();
		
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#undo()
	 */
	@Override	
	public void undo() {
		
		this.numberCmd = this.numberCmd - 1;
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#redo()
	 */
	@Override	
	public void redo() {
		
		
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.v2.Recorder#record(Recordable cmd)
	 */
	@Override	
	public void record(Recordable cmd) {
		this.numberCmd = this.numberCmd +1;
		if (savedEditorEngine(this.numberCmd)) {
			Memento m = ((Recordable) this.editorEngine).getMemento();
			
			this.editorEngineStates.put(this.numberCmd, m);
			
			this.commandAfter = new TreeMap<Integer,Recordable>();
			this.commandAfterMemento = new TreeMap<Integer,Memento>();
		} else {
			
			Memento m = cmd.getMemento();

			this.commandBefore.put(this.numberCmd, cmd);
			this.commandBeforeMemento.put(this.numberCmd, m);
			
			this.commandAfter = new TreeMap<Integer,Recordable>();
			this.commandAfterMemento = new TreeMap<Integer,Memento>();
		}
	}
	
	private boolean savedEditorEngine(Integer i) {
		return true;
	}
	

}
