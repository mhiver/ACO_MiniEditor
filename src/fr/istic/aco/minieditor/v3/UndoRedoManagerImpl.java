package fr.istic.aco.minieditor.v3;
import java.util.Map;
import java.util.TreeMap;

import fr.istic.aco.minieditor.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;

public class UndoRedoManagerImpl implements UndoRedoManager {

	EditorEngine editorEngine;

	private Map<Integer,Memento> editorEngineStates;

	private Map<Integer,Recordable> CommandBefore;

	private Map<Integer,Recordable> CommandAfter;
	
	int numberCmd;
	
	public UndoRedoManagerImpl(EditorEngine editorEngine) {
		this.editorEngine = editorEngine;
		this.numberCmd = 0;
		
		this.editorEngineStates = new TreeMap<Integer,Memento>();
		this.CommandBefore = new TreeMap<Integer,Recordable>();
		this.CommandAfter = new TreeMap<Integer,Recordable>();
		
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.Command#undo()
	 */
	@Override	
	public void undo() {
		
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
			this.editorEngineStates.put(this.numberCmd, ((Recordable) this.editorEngine).getMemento());
		} else {
			
		}
	}
	
	private boolean savedEditorEngine(Integer i) {
		return true;
	}
	

}
