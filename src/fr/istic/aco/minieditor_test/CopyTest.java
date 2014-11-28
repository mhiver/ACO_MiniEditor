/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.Copy;
import fr.istic.aco.minieditor.EditorEngineImpl;

/**
 * @author 12001247
 *
 */
public class CopyTest {
	Copy copy;
	EditorEngineImpl editorEngine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = new EditorEngineImpl();
		copy = new Copy(editorEngine);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Copy#execute()}.
	 */
	@Test
	public final void testExecute() {
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		editorEngine.changeSelection(3, 8);
		copy.execute();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(3, editorEngine.getSelection().getStart());
		assertEquals(8, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(0, 5);
		copy.execute();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(5, editorEngine.getSelection().getEnd());
		assertEquals("Hello", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(4, 4);
		copy.execute();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(4, editorEngine.getSelection().getStart());
		assertEquals(4, editorEngine.getSelection().getEnd());
		assertEquals("Hello", editorEngine.getClipboard().getText());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Copy#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Copy", copy.getName());
	}

}
