/**
 * 
 */
package fr.istic.aco.minieditor.v3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v3.PasteOpposite;
import fr.istic.aco.minieditor.v3.PasteUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class PasteOppositeTest {
	private EditorEngine editorEngine;
	private UndoRedoManager undoRedoManager;
	private PasteUndoable pasteUndoable;
	private PasteOpposite pasteOpposite;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngine.class);
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		pasteUndoable = Mockito.mock(PasteUndoable.class);
		pasteOpposite = new PasteOpposite(editorEngine, undoRedoManager, pasteUndoable);
		memento = Mockito.mock(Memento.class);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.PasteOpposite#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Mockito.when(editorEngine.getSelectionText()).thenReturn("Buffer");
		Mockito.when(editorEngine.getStartSelection()).thenReturn(3);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(8);
		
		Memento m = pasteOpposite.getMemento();
		Object[] ret = (Object[]) m.getSavedState();
		assertEquals("Buffer", (String) ret[0]);
		assertEquals(3, (int) ret[1]);
		assertEquals(8, (int) ret[2]);
		
		m = pasteOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals("Buffer", (String) ret[0]);
		assertEquals(3, (int) ret[1]);
		assertEquals(8, (int) ret[2]);

		Mockito.when(editorEngine.getSelectionText()).thenReturn("Buffer2");
		Mockito.when(editorEngine.getStartSelection()).thenReturn(42);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(1337);
		
		m = pasteOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals("Buffer2", (String) ret[0]);
		assertEquals(42, (int) ret[1]);
		assertEquals(1337, (int) ret[2]);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.PasteOpposite#execute()}.
	 */
	@Test
	public void testExecute() {
		Mockito.when(memento.getSavedState()).thenReturn(new Object[] {"Buffer", 3, 8});
		pasteOpposite.setMemento(memento);

		pasteOpposite.execute();
		pasteOpposite.execute();

		Mockito.when(memento.getSavedState()).thenReturn(new Object[] {"Buffer2", 42, 1337});
		pasteOpposite.setMemento(memento);

		pasteOpposite.execute();

		Mockito.verify(undoRedoManager, Mockito.times(3)).record(pasteUndoable);
		Mockito.verify(editorEngine, Mockito.times(2)).pasteOpposite("Buffer", 3, 8);
		Mockito.verify(editorEngine).pasteOpposite("Buffer2", 42, 1337);
	}

}
