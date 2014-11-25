/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.EditorEngineImpl;

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
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		assertEquals(11, editorEngine.getSelection().getStart());
		assertEquals(11, editorEngine.getSelection().getEnd());
		editorEngine.changeSelection(3, 8);
		assertEquals(3, editorEngine.getSelection().getStart());
		assertEquals(8, editorEngine.getSelection().getEnd());
		editorEngine.changeSelection(1, 1);
		assertEquals(1, editorEngine.getSelection().getStart());
		assertEquals(1, editorEngine.getSelection().getEnd());
		editorEngine.changeSelection(7, 2);
		assertEquals(2, editorEngine.getSelection().getStart());
		assertEquals(7, editorEngine.getSelection().getEnd());
		editorEngine.changeSelection(2, 20);
		assertEquals(2, editorEngine.getSelection().getStart());
		assertEquals(11, editorEngine.getSelection().getEnd());
		editorEngine.changeSelection(-5, 5);
		assertEquals(2, editorEngine.getSelection().getStart());
		assertEquals(5, editorEngine.getSelection().getEnd());
		editorEngine.changeSelection(1, -2);
		assertEquals(1, editorEngine.getSelection().getStart());
		assertEquals(5, editorEngine.getSelection().getEnd());
		editorEngine.changeSelection(20, 1);
		assertEquals(1, editorEngine.getSelection().getStart());
		assertEquals(11, editorEngine.getSelection().getEnd());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.EditorEngineImpl#copy()}.
	 */
	@Test
	public final void testCopy() {
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		editorEngine.changeSelection(3, 8);
		editorEngine.copy();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(3, editorEngine.getSelection().getStart());
		assertEquals(8, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(0, 5);
		editorEngine.copy();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(5, editorEngine.getSelection().getEnd());
		assertEquals("Hello", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(4, 4);
		editorEngine.copy();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(4, editorEngine.getSelection().getStart());
		assertEquals(4, editorEngine.getSelection().getEnd());
		assertEquals("Hello", editorEngine.getClipboard().getText());
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
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		editorEngine.changeSelection(3, 8);
		editorEngine.copy();
		editorEngine.paste();
		assertEquals("Hello world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(8, editorEngine.getSelection().getStart());
		assertEquals(8, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		editorEngine.paste();
		assertEquals("Hello wolo world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(13, editorEngine.getSelection().getStart());
		assertEquals(13, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(0, 1);
		editorEngine.paste();
		assertEquals("lo woello wolo world", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(5, editorEngine.getSelection().getStart());
		assertEquals(5, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.EditorEngineImpl#cut()}.
	 */
	@Test
	public final void testCut() {
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
		editorEngine.enterText("Hello world");
		editorEngine.changeSelection(3, 8);
		editorEngine.cut();
		assertEquals("Helrld", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(3, editorEngine.getSelection().getStart());
		assertEquals(3, editorEngine.getSelection().getEnd());
		assertEquals("lo wo", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(0, 5);
		editorEngine.cut();
		assertEquals("d", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("Helrl", editorEngine.getClipboard().getText());
		editorEngine.changeSelection(1, 1);
		editorEngine.cut();
		assertEquals("d", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(1, editorEngine.getSelection().getStart());
		assertEquals(1, editorEngine.getSelection().getEnd());
		assertEquals("Helrl", editorEngine.getClipboard().getText());
	}

}
