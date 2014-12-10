/**
 * 
 */
package fr.istic.aco.minieditor.v3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v3.CopyOpposite;
import fr.istic.aco.minieditor.v3.CopyUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class CopyUndoableTest {
	private UndoRedoManager undoRedoManager; 
	private EditorEngine editorEngine;
	private CopyUndoable copyUndoable;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		editorEngine = Mockito.mock(EditorEngine.class);
		copyUndoable = new CopyUndoable(editorEngine, undoRedoManager);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CopyUndoable#execute()}.
	 */
	@Test
	public final void testExecute() {
		InOrder inOrder = Mockito.inOrder(undoRedoManager, editorEngine);

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		copyUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(CopyOpposite.class));
		inOrder.verify(editorEngine).copy();
		
		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(false);
		copyUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(CopyOpposite.class));
		inOrder.verify(editorEngine).copy();

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		copyUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(CopyOpposite.class));
		inOrder.verify(editorEngine).copy();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CopyUndoable#getMemento()}.
	 */
	@Test
	public final void testGetMemento() {
		assertNull(copyUndoable.getMemento().getSavedState());
		
		Memento m = Mockito.mock(Memento.class);
		Mockito.when(m.getSavedState()).thenReturn(new Object());
		copyUndoable.setMemento(m);
		assertNull(copyUndoable.getMemento().getSavedState());
	}

}
