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
import fr.istic.aco.minieditor.v3.PasteOpposite;
import fr.istic.aco.minieditor.v3.PasteUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author 12001247
 *
 */
public class PasteUndoableTest {
	private UndoRedoManager undoRedoManager; 
	private EditorEngine editorEngine;
	private PasteUndoable pasteUndoable;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		editorEngine = Mockito.mock(EditorEngine.class);
		pasteUndoable = new PasteUndoable(editorEngine, undoRedoManager);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.PasteUndoable#exepastee()}.
	 */
	@Test
	public final void testExepastee() {
		InOrder inOrder = Mockito.inOrder(undoRedoManager, editorEngine);

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		pasteUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(PasteOpposite.class));
		inOrder.verify(editorEngine).paste();
		
		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(false);
		pasteUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(PasteOpposite.class));
		inOrder.verify(editorEngine).paste();

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		pasteUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(PasteOpposite.class));
		inOrder.verify(editorEngine).paste();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.PasteUndoable#getMemento()}.
	 */
	@Test
	public final void testGetMemento() {
		assertNull(pasteUndoable.getMemento().getSavedState());
		
		Memento m = Mockito.mock(Memento.class);
		Mockito.when(m.getSavedState()).thenReturn(new Object());
		pasteUndoable.setMemento(m);
		assertNull(pasteUndoable.getMemento().getSavedState());
	}

}
