/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.ChangeSelection;
import fr.istic.aco.minieditor.EditorEngineImpl;
import fr.istic.aco.minieditor.UIImpl;

import org.mockito.Mockito;

/**
 * @author 12001247
 *
 */
public class ChangeSelectionTest {
	ChangeSelection changeSelection;
	EditorEngineImpl editorEngine;
	UIImpl uI;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		uI = Mockito.mock(UIImpl.class);
		changeSelection = new ChangeSelection(editorEngine, uI);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.ChangeSelection#execute()}.
	 */
	@Test
	public final void testExecute() {
		Mockito.when(uI.getStart()).thenReturn(3);
		Mockito.when(uI.getEnd()).thenReturn(6);
		
		changeSelection.execute();
		Mockito.verify(editorEngine).changeSelection(3, 6);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.ChangeSelection#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Change selection", changeSelection.getName());
	}

}
