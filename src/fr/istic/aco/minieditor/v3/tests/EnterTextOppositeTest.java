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
import fr.istic.aco.minieditor.v3.EnterTextOpposite;
import fr.istic.aco.minieditor.v3.EnterTextUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Matthieu
 *
 */
public class EnterTextOppositeTest {
	private EditorEngine editorEngine;
	private UndoRedoManager undoRedoManager;
	private EnterTextUndoable enterTextUndoable;
	private EnterTextOpposite enterTextOpposite;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngine.class);
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		enterTextUndoable = Mockito.mock(EnterTextUndoable.class);
		enterTextOpposite = new EnterTextOpposite(editorEngine, undoRedoManager, enterTextUndoable);
		memento = Mockito.mock(Memento.class);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.EnterTextOpposite#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Mockito.when(editorEngine.getSelectionText()).thenReturn("Buffer");
		Mockito.when(editorEngine.getStartSelection()).thenReturn(3);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(8);
		
		Memento m = enterTextOpposite.getMemento();
		Object[] ret = (Object[]) m.getSavedState();
		assertEquals("Buffer", (String) ret[0]);
		assertEquals(3, (int) ret[1]);
		assertEquals(8, (int) ret[2]);
		
		m = enterTextOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals("Buffer", (String) ret[0]);
		assertEquals(3, (int) ret[1]);
		assertEquals(8, (int) ret[2]);

		Mockito.when(editorEngine.getSelectionText()).thenReturn("Buffer2");
		Mockito.when(editorEngine.getStartSelection()).thenReturn(42);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(1337);
		
		m = enterTextOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals("Buffer2", (String) ret[0]);
		assertEquals(42, (int) ret[1]);
		assertEquals(1337, (int) ret[2]);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.EnterTextOpposite#execute()}.
	 */
	@Test
	public void testExecute() {
		Mockito.when(memento.getSavedState()).thenReturn(new Object[] {"Buffer", 3, 8});
		enterTextOpposite.setMemento(memento);

		enterTextOpposite.execute();
		enterTextOpposite.execute();

		Mockito.when(memento.getSavedState()).thenReturn(new Object[] {"Buffer2", 42, 1337});
		enterTextOpposite.setMemento(memento);

		enterTextOpposite.execute();

		Mockito.verify(undoRedoManager, Mockito.times(3)).record(enterTextUndoable);
		Mockito.verify(editorEngine, Mockito.times(2)).enterTextOpposite("Buffer", 3, 8);
		Mockito.verify(editorEngine).enterTextOpposite("Buffer2", 42, 1337);
	}

}
