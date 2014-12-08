/**
 * 
 */
package fr.istic.aco.minieditor.v3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v3.Undo;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author 12001247
 *
 */
public class UndoTest {
	UndoRedoManager undoRedoManager;
	Undo undo;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		undo = new Undo(undoRedoManager);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.Undo#execute()}.
	 */
	@Test
	public final void testExecute() {
		undo.execute();
		Mockito.verify(undoRedoManager).undo();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.Undo#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Undo", undo.getName());
	}

}
