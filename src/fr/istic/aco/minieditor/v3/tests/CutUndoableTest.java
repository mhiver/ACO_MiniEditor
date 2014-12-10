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
import fr.istic.aco.minieditor.v3.CutOpposite;
import fr.istic.aco.minieditor.v3.CutUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class CutUndoableTest {
	private UndoRedoManager undoRedoManager; 
	private EditorEngine editorEngine;
	private CutUndoable cutUndoable;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		editorEngine = Mockito.mock(EditorEngine.class);
		cutUndoable = new CutUndoable(editorEngine, undoRedoManager);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CutUndoable#execute()}.
	 */
	@Test
	public final void testExecute() {
		InOrder inOrder = Mockito.inOrder(undoRedoManager, editorEngine);

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		cutUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(CutOpposite.class));
		inOrder.verify(editorEngine).cut();
		
		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(false);
		cutUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(CutOpposite.class));
		inOrder.verify(editorEngine).cut();

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		cutUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(CutOpposite.class));
		inOrder.verify(editorEngine).cut();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CutUndoable#getMemento()}.
	 */
	@Test
	public final void testGetMemento() {
		assertNull(cutUndoable.getMemento().getSavedState());
		
		Memento m = Mockito.mock(Memento.class);
		Mockito.when(m.getSavedState()).thenReturn(new Object());
		cutUndoable.setMemento(m);
		assertNull(cutUndoable.getMemento().getSavedState());
	}

}
