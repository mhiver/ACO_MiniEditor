/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.EditorEngineImpl;
import fr.istic.aco.minieditor.v1.EnterText;
import fr.istic.aco.minieditor.v1.UIImpl;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 *
 */
public class EnterTextTest {
	EnterText enterText;
	EditorEngineImpl editorEngine;
	UIImpl uI;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		uI = Mockito.mock(UIImpl.class);
		enterText = new EnterText(editorEngine, uI);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.EnterText#execute()}.
	 */
	@Test
	public final void testExecute() {
		Mockito.when(uI.getText()).thenReturn("abc TEST");
		enterText.execute();
		Mockito.verify(editorEngine).enterText("abc TEST");
		
		Mockito.when(uI.getText()).thenReturn("");
		enterText.execute();
		Mockito.verify(editorEngine).enterText("");
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.EnterText#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Enter text", enterText.getName());
	}

}
