/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v1.EditorEngineImpl;

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
	 * Test method for {@link fr.istic.aco.minieditor.v1.EditorEngineImpl#EditorEngineImpl()}.
	 */
	@Test
	public final void testEditorEngineImpl() {
		assertEquals("", editorEngine.getBuffer().getBuffer().toString());
		assertEquals(0, editorEngine.getSelection().getStart());
		assertEquals(0, editorEngine.getSelection().getEnd());
		assertEquals("", editorEngine.getClipboard().getText());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.EditorEngineImpl#changeSelection(int, int)}.
	 */
	@Test
	public final void testChangeSelection() {
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.EditorEngineImpl#copy()}.
	 */
	@Test
	public final void testCopy() {
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.EditorEngineImpl#enterText(java.lang.String)}.
	 */
	@Test
	public final void testEnterText() {
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.EditorEngineImpl#paste()}.
	 */
	@Test
	public final void testPaste() {
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.EditorEngineImpl#cut()}.
	 */
	@Test
	public final void testCut() {
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

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.EditorEngineImpl#printData()}.
	 */
	@Test
	public final void testPrintData() {
		assertEquals("Buffer:\n[]\nClipboard:\n\n", editorEngine.printData());
		editorEngine.enterText("Hello world");
		assertEquals("Buffer:\nHello world[]\nClipboard:\n\n", editorEngine.printData());
		editorEngine.changeSelection(3, 8);
		assertEquals("Buffer:\nHel[lo wo]rld\nClipboard:\n\n", editorEngine.printData());
		editorEngine.cut();
		assertEquals("Buffer:\nHel[]rld\nClipboard:\nlo wo\n", editorEngine.printData());
		editorEngine.changeSelection(1, 3);
		assertEquals("Buffer:\nH[el]rld\nClipboard:\nlo wo\n", editorEngine.printData());
		editorEngine.copy();
		assertEquals("Buffer:\nH[el]rld\nClipboard:\nel\n", editorEngine.printData());
	}

}
