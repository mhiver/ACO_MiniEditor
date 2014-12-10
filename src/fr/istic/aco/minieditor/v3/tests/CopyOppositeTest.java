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
import fr.istic.aco.minieditor.v3.CopyOpposite;
import fr.istic.aco.minieditor.v3.CopyUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class CopyOppositeTest {
	private EditorEngine editorEngine;
	private UndoRedoManager undoRedoManager;
	private CopyUndoable copyUndoable;
	private CopyOpposite copyOpposite;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngine.class);
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		copyUndoable = Mockito.mock(CopyUndoable.class);
		copyOpposite = new CopyOpposite(editorEngine, undoRedoManager, copyUndoable);
		memento = Mockito.mock(Memento.class);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CopyOpposite#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Mockito.when(editorEngine.getClipboardText()).thenReturn("Hello world");
		
		Memento m = copyOpposite.getMemento();
		String ret = (String) m.getSavedState();
		assertEquals("Hello world", ret);
		
		m = copyOpposite.getMemento();
		ret = (String) m.getSavedState();
		assertEquals("Hello world", ret);
		
		Mockito.when(editorEngine.getClipboardText()).thenReturn("abc DEF 123");
		
		m = copyOpposite.getMemento();
		ret = (String) m.getSavedState();
		assertEquals("abc DEF 123", ret);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.CopyOpposite#execute()}.
	 */
	@Test
	public void testExecute() {
		Mockito.when(memento.getSavedState()).thenReturn("Hello world");
		copyOpposite.setMemento(memento);

		copyOpposite.execute();
		copyOpposite.execute();

		Mockito.when(memento.getSavedState()).thenReturn("abc DEF 123");
		copyOpposite.setMemento(memento);

		copyOpposite.execute();

		Mockito.verify(undoRedoManager, Mockito.times(3)).record(copyUndoable);
		Mockito.verify(editorEngine, Mockito.times(2)).copyOpposite("Hello world");
		Mockito.verify(editorEngine).copyOpposite("abc DEF 123");
	}

}
