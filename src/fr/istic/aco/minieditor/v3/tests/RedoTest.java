/**
 * 
 */
package fr.istic.aco.minieditor.v3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v3.Redo;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author 12001247
 *
 */
public class RedoTest {
	UndoRedoManager undoRedoManager;
	Redo redo;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		redo = new Redo(undoRedoManager);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.Redo#execute()}.
	 */
	@Test
	public final void testExecute() {
		redo.execute();
		Mockito.verify(undoRedoManager).redo();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.Redo#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Redo", redo.getName());
	}

}
