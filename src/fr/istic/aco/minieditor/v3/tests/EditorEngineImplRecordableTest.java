/**
 * 
 */
package fr.istic.aco.minieditor.v3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.Buffer;
import fr.istic.aco.minieditor.v1.Clipboard;
import fr.istic.aco.minieditor.v1.Selection;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v3.EditorEngineImplRecordable;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class EditorEngineImplRecordableTest {
	private EditorEngineImplRecordable spy;
	private Buffer buffer;
	private Clipboard clipboard;
	private Selection selection;
	private StringBuffer stringBuffer;
	private String clipboardText = "Hello world";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		spy = Mockito.spy(new EditorEngineImplRecordable());
		buffer = Mockito.mock(Buffer.class);
		clipboard = Mockito.mock(Clipboard.class);
		selection = Mockito.mock(Selection.class);
		Mockito.stub(spy.getBuffer()).toReturn(buffer);
		Mockito.stub(spy.getClipboard()).toReturn(clipboard);
		Mockito.stub(spy.getSelection()).toReturn(selection);
		stringBuffer = new StringBuffer("ABC def 123");
		clipboardText = "Hello world";
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.EditorEngineImplRecordable#getMemento()}.
	 */
	@Test
	public final void testGetMemento() {
		Mockito.when(buffer.getBuffer()).thenReturn(stringBuffer);
		Mockito.when(clipboard.getText()).thenReturn(clipboardText);
		Mockito.when(selection.getStart()).thenReturn(3);
		Mockito.when(selection.getEnd()).thenReturn(8);
		
		Memento m = spy.getMemento();
		Object[] ret = (Object[]) m.getSavedState();
		assertArrayEquals(new Object[] {stringBuffer, clipboardText, 3, 8}, ret);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.EditorEngineImplRecordable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public final void testSetMemento() {
		EditorEngineImplRecordable editorEngineImplRecordable = new EditorEngineImplRecordable();
		Object[] state = new Object[] {stringBuffer, clipboardText, 3, 8};
		Memento memento = Mockito.mock(Memento.class);
		Mockito.when(memento.getSavedState()).thenReturn(state);
		editorEngineImplRecordable.setMemento(memento);
		Mockito.verify(memento).getSavedState();
		assertEquals(stringBuffer.toString(), editorEngineImplRecordable.getBuffer().getBuffer().toString());
		assertEquals(clipboardText, editorEngineImplRecordable.getClipboard().getText());
		assertEquals(3, editorEngineImplRecordable.getSelection().getStart());
		assertEquals(8, editorEngineImplRecordable.getSelection().getEnd());
	}

}
