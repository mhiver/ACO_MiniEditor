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
import fr.istic.aco.minieditor.v2.CutRecordable;
import fr.istic.aco.minieditor.v2.MemCopy;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author Matthieu
 *
 */
public class CutRecordableTest {
	CutRecordable cutRecordable;
	EditorEngine editorEngine;
	Recorder recorder;
	Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		recorder = Mockito.mock(RecorderImpl.class);
		cutRecordable = new CutRecordable(editorEngine, recorder);
		memento = Mockito.mock(MemCopy.class);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.CutRecordable#execute()}.
	 */
	@Test
	public void testExecute() {
		InOrder inOrder = Mockito.inOrder(recorder, editorEngine);
		
		Mockito.when(recorder.getRecording()).thenReturn(true);
		cutRecordable.execute();

		Mockito.when(recorder.getRecording()).thenReturn(false);
		cutRecordable.execute();
		
		inOrder.verify(editorEngine).cut();
		inOrder.verify(recorder).record(cutRecordable);
		inOrder.verify(editorEngine).cut();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.CutRecordable#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Memento m = cutRecordable.getMemento();
		
		assertNull(m.getSavedState());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.CutRecordable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public void testSetMemento() {
		// Nothing to test here
	}

}
