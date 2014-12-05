/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.EditorEngineImpl;
import fr.istic.aco.minieditor.v1.PrintData;
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v1.UIImpl;

/**
 * @author 12001247
 *
 */
public class PrintDataTest {
	private PrintData printData;
	private EditorEngine editorEngine;
	private UI uI;

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
	 * Test method for {@link fr.istic.aco.minieditor.v1.PrintData#execute()}.
	 */
	@Test
	public final void testExecute() {
		printData.execute();
		Mockito.verify(editorEngine).printData();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.PrintData#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Print data", printData.getName());
	}

}
