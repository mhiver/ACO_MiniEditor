/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.EditorEngineImpl;
import fr.istic.aco.minieditor.EnterText;
import fr.istic.aco.minieditor.UIImpl;

/**
 * @author 12001247
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
	 * Test method for {@link fr.istic.aco.minieditor.EnterText#execute()}.
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
	 * Test method for {@link fr.istic.aco.minieditor.EnterText#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Enter text", enterText.getName());
	}

}
