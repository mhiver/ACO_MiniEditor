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
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v3.EnterTextOpposite;
import fr.istic.aco.minieditor.v3.EnterTextUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class EnterTextUndoableTest {
	private UndoRedoManager undoRedoManager; 
	private EditorEngine editorEngine;
	private UI uI;
	private EnterTextUndoable enterTextUndoable;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		editorEngine = Mockito.mock(EditorEngine.class);
		uI = Mockito.mock(UI.class);
		memento = Mockito.mock(Memento.class);
		enterTextUndoable = new EnterTextUndoable(editorEngine, uI, undoRedoManager);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.EnterTextUndoable#execute()}.
	 */
	@Test
	public final void testExecute() {
		InOrder inOrder = Mockito.inOrder(undoRedoManager, editorEngine);

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		enterTextUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(EnterTextOpposite.class));
		inOrder.verify(editorEngine).enterText("");
		
		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(false);
		Mockito.when(uI.getText()).thenReturn("Hello world");
		enterTextUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(EnterTextOpposite.class));
		inOrder.verify(editorEngine).enterText("Hello world");

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		Mockito.when(uI.getText()).thenReturn("Blabla");
		enterTextUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(EnterTextOpposite.class));
		inOrder.verify(editorEngine).enterText("Hello world");
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.EnterTextUndoable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public final void testSetMemento() {
		String data = "Hello world";
		Mockito.when(memento.getSavedState()).thenReturn(data);

		String ret = (String) (enterTextUndoable.getMemento().getSavedState());
		assertEquals("", ret);
		
		enterTextUndoable.setMemento(memento);
		Mockito.verify(memento).getSavedState();
		
		ret = (String) (enterTextUndoable.getMemento().getSavedState());
		assertEquals(data, ret);
	}

}
