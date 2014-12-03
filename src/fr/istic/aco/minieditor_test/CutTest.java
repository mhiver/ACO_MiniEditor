/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.Cut;
import fr.istic.aco.minieditor.EditorEngineImpl;

/**
 * @author 12001247
 *
 */
public class CutTest {
	Cut cut;
	EditorEngineImpl editorEngine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = new EditorEngineImpl();
		cut = new Cut(editorEngine);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Cut#execute()}.
	 */
	@Test
	public final void testExecute() {
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		editorEngine.changeSelection(3, 8);
		cut.execute();
		assertEquals("Helrld", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(3, editorEngine.getSelection().getStart());
		assertEquals(3, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(0, 5);
		cut.execute();
		assertEquals("d", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("Helrl", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(1, 1);
		cut.execute();
		assertEquals("d", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(1, editorEngine.getSelection().getStart());
		assertEquals(1, editorEngine.getSelection().getEnd());
		assertEquals("Helrl", editorEngine.getClipboard().getText());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Cut#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Cut", cut.getName());
	}

}
