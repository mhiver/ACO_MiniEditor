/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.EditorEngineImpl;
import fr.istic.aco.minieditor.PrintData;
import fr.istic.aco.minieditor.UIImpl;

/**
 * @author 12001247
 *
 */
public class PrintDataTest {
	PrintData printData;
	EditorEngineImpl editorEngine;
	UIImpl uI;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		uI = Mockito.mock(UIImpl.class);
		printData = new PrintData(editorEngine, uI);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.PrintData#execute()}.
	 */
	@Test
	public final void testExecute() {
		printData.execute();
		Mockito.verify(editorEngine).printData();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.PrintData#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Print data", printData.getName());
	}

}
