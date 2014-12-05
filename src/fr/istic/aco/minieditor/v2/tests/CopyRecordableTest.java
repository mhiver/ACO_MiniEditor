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
import fr.istic.aco.minieditor.v2.CopyRecordable;
import fr.istic.aco.minieditor.v2.MemCopy;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author Matthieu
 *
 */
public class CopyRecordableTest {
	CopyRecordable copyRecordable;
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
		copyRecordable = new CopyRecordable(editorEngine, recorder);
		memento = Mockito.mock(MemCopy.class);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.CopyRecordable#execute()}.
	 */
	@Test
	public void testExecute() {
		InOrder inOrder = Mockito.inOrder(recorder, editorEngine);
		
		Mockito.when(recorder.getRecording()).thenReturn(true);
		copyRecordable.execute();

		Mockito.when(recorder.getRecording()).thenReturn(false);
		copyRecordable.execute();
		
		inOrder.verify(editorEngine).copy();
		inOrder.verify(recorder).record(copyRecordable);
		inOrder.verify(editorEngine).copy();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.CopyRecordable#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		Memento m = copyRecordable.getMemento();
		
		assertNull(m.getSavedState());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.CopyRecordable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public void testSetMemento() {
		// Nothing to test here
	}

}
