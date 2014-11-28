/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.EditorEngineImpl;
import fr.istic.aco.minieditor.Paste;

/**
 * @author 12001247
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
		editorEngine = new EditorEngineImpl();
		paste = new Paste(editorEngine);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Paste#execute()}.
	 */
	@Test
	public final void testExecute() {
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		editorEngine.changeSelection(3, 8);
		editorEngine.copy();
		paste.execute();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(8, editorEngine.getSelection().getStart());
		assertEquals(8, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		paste.execute();
		assertEquals("Hello wolo world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(13, editorEngine.getSelection().getStart());
		assertEquals(13, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(0, 1);
		paste.execute();
		assertEquals("lo woello wolo world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(5, editorEngine.getSelection().getStart());
		assertEquals(5, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Paste#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Paste", paste.getName());
	}

}
