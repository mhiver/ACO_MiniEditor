/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.EditorEngineImpl;
import fr.istic.aco.minieditor.v1.Paste;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 *
 */
public class PasteTest {
	Paste paste;
	EditorEngineImpl editorEngine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		paste = new Paste(editorEngine);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Paste#execute()}.
	 */
	@Test
	public final void testExecute() {
		paste.execute();
		Mockito.verify(editorEngine).paste();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Paste#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Paste", paste.getName());
	}

}
