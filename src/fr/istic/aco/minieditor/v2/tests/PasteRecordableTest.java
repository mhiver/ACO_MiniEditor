/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.EditorEngineImpl;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.PasteRecordable;
import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 *
 */
public class PasteRecordableTest {
	private PasteRecordable pasteRecordable;
	private EditorEngine editorEngine;
	private Recorder recorder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		recorder = Mockito.mock(RecorderImpl.class);
		pasteRecordable = new PasteRecordable(editorEngine, recorder);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.PasteRecordable#execute()}.
	 */
	@Test
	public void testExecute() {
		InOrder inOrder = Mockito.inOrder(recorder, editorEngine);
		
		Mockito.when(recorder.getRecording()).thenReturn(true);
		pasteRecordable.execute();

		Mockito.when(recorder.getRecording()).thenReturn(false);
		pasteRecordable.execute();
		
		inOrder.verify(editorEngine).paste();
		inOrder.verify(recorder).record(pasteRecordable);
		inOrder.verify(editorEngine).paste();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.PasteRecordable#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Memento m = pasteRecordable.getMemento();
		
		assertNull(m.getSavedState());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.PasteRecordable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public void testSetMemento() {
		// Nothing to test here
	}

}
