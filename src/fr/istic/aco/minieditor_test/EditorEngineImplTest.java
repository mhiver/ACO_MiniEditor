/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.EditorEngineImpl;
import org.mockito.Mockito;

/**
 * @author 12001247
 *
 */
public class EditorEngineImplTest {

	EditorEngineImpl editorEngine;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = new EditorEngineImpl();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.EditorEngineImpl#changeSelection(int, int)}.
	 */
	@Test
	public final void testChangeSelection() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.EditorEngineImpl#copy()}.
	 */
	@Test
	public final void testCopy() {
		editorEngine.enterText("Hello world");
		editorEngine.changeSelection(3, 8);
		editorEngine.copy();
		
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.EditorEngineImpl#enterText(java.lang.String)}.
	 */
	@Test
	public final void testEnterText() {
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(11, editorEngine.getSelection().getStart());
		assertEquals(11, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText(" !");
		assertEquals("Hello world !", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(13, editorEngine.getSelection().getStart());
		assertEquals(13, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.EditorEngineImpl#paste()}.
	 */
	@Test
	public final void testPaste() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.EditorEngineImpl#cut()}.
	 */
	@Test
	public final void testCut() {
		fail("Not yet implemented"); // TODO
	}

}
