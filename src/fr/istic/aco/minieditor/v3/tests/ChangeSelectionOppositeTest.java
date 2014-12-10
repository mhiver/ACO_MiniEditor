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
import fr.istic.aco.minieditor.v3.ChangeSelectionOpposite;
import fr.istic.aco.minieditor.v3.ChangeSelectionUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class ChangeSelectionOppositeTest {
	private EditorEngine editorEngine;
	private UndoRedoManager undoRedoManager;
	private ChangeSelectionUndoable changeSelectionUndoable;
	private ChangeSelectionOpposite changeSelectionOpposite;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngine.class);
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		changeSelectionUndoable = Mockito.mock(ChangeSelectionUndoable.class);
		changeSelectionOpposite = new ChangeSelectionOpposite(editorEngine, undoRedoManager, changeSelectionUndoable);
		memento = Mockito.mock(Memento.class);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.ChangeSelectionOpposite#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Mockito.when(editorEngine.getStartSelection()).thenReturn(3);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(8);
		
		Memento m = changeSelectionOpposite.getMemento();
		Object[] ret = (Object[]) m.getSavedState();
		assertEquals(3, (int) ret[0]);
		assertEquals(8, (int) ret[1]);
		
		m = changeSelectionOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals(3, (int) ret[0]);
		assertEquals(8, (int) ret[1]);
		
		Mockito.when(editorEngine.getStartSelection()).thenReturn(42);
		Mockito.when(editorEngine.getEndSelection()).thenReturn(1337);
		
		m = changeSelectionOpposite.getMemento();
		ret = (Object[]) m.getSavedState();
		assertEquals(42, (int) ret[0]);
		assertEquals(1337, (int) ret[1]);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.ChangeSelectionOpposite#execute()}.
	 */
	@Test
	public void testExecute() {
		Mockito.when(memento.getSavedState()).thenReturn(new Object[]{3, 8});
		changeSelectionOpposite.setMemento(memento);

		changeSelectionOpposite.execute();
		changeSelectionOpposite.execute();

		Mockito.when(memento.getSavedState()).thenReturn(new Object[]{42, 1337});
		changeSelectionOpposite.setMemento(memento);

		changeSelectionOpposite.execute();

		Mockito.verify(undoRedoManager, Mockito.times(3)).record(changeSelectionUndoable);
		Mockito.verify(editorEngine, Mockito.times(2)).changeSelectionOpposite(3, 8);
		Mockito.verify(editorEngine).changeSelectionOpposite(42, 1337);
	}

}
