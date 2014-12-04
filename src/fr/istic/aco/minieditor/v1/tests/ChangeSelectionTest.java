/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v1.ChangeSelection;
import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.EditorEngineImpl;
import fr.istic.aco.minieditor.v1.UIImpl;

import org.mockito.Mockito;

/**
 * @author 12001247
 *
 */
public class ChangeSelectionTest {
	ChangeSelection changeSelection;
	EditorEngine editorEngine;
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.ChangeSelection#execute()}.
	 */
	@Test
	public final void testExecute() {
		Mockito.when(uI.getStart()).thenReturn(3);
		Mockito.when(uI.getEnd()).thenReturn(6);
		
		changeSelection.execute();
		Mockito.verify(editorEngine).changeSelection(3, 6);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.ChangeSelection#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Change selection", changeSelection.getName());
	}

}
