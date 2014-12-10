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
import fr.istic.aco.minieditor.v3.CutOpposite;
import fr.istic.aco.minieditor.v3.CutUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class CutOppositeTest {
	private EditorEngine editorEngine;
	private UndoRedoManager undoRedoManager;
	private CutUndoable cutUndoable;
	private CutOpposite cutOpposite;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngine.class);
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		cutUndoable = Mockito.mock(CutUndoable.class);
		cutOpposite = new CutOpposite(editorEngine, undoRedoManager, cutUndoable);
		memento = Mockito.mock(Memento.class);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CutOpposite#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Mockito.when(editorEngine.getClipboardText()).thenReturn("Clipboard");
		Mockito.when(editorEngine.getSelectionText()).thenReturn("Buffer");
		Mockito.when(editorEngine.getStartSelection()).thenReturn(3);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(8);
		
		Memento m = cutOpposite.getMemento();
		Object[] ret = (Object[]) m.getSavedState();
		assertEquals("Clipboard", (String) ret[0]);
		assertEquals("Buffer", (String) ret[1]);
		assertEquals(3, (int) ret[2]);
		assertEquals(8, (int) ret[3]);
		
		m = cutOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals("Clipboard", (String) ret[0]);
		assertEquals("Buffer", (String) ret[1]);
		assertEquals(3, (int) ret[2]);
		assertEquals(8, (int) ret[3]);

		Mockito.when(editorEngine.getClipboardText()).thenReturn("Clipboard2");
		Mockito.when(editorEngine.getSelectionText()).thenReturn("Buffer2");
		Mockito.when(editorEngine.getStartSelection()).thenReturn(42);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(1337);
		
		m = cutOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals("Clipboard2", (String) ret[0]);
		assertEquals("Buffer2", (String) ret[1]);
		assertEquals(42, (int) ret[2]);
		assertEquals(1337, (int) ret[3]);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CutOpposite#execute()}.
	 */
	@Test
	public void testExecute() {
		Mockito.when(memento.getSavedState()).thenReturn(new Object[] {"Clipboard", "Buffer", 3, 8});
		cutOpposite.setMemento(memento);

		cutOpposite.execute();
		cutOpposite.execute();

		Mockito.when(memento.getSavedState()).thenReturn(new Object[] {"Clipboard2", "Buffer2", 42, 1337});
		cutOpposite.setMemento(memento);

		cutOpposite.execute();

		Mockito.verify(undoRedoManager, Mockito.times(3)).record(cutUndoable);
		Mockito.verify(editorEngine, Mockito.times(2)).cutOpposite("Clipboard", "Buffer", 3, 8);
		Mockito.verify(editorEngine).cutOpposite("Clipboard2", "Buffer2", 42, 1337);
	}

}
