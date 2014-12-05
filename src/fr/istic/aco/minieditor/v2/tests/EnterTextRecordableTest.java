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
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v1.UIImpl;
import fr.istic.aco.minieditor.v2.EnterTextRecordable;
import fr.istic.aco.minieditor.v2.MemChangeSelection;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author Matthieu
 *
 */
public class EnterTextRecordableTest {
	EnterTextRecordable enterTextRecordable;
	EditorEngine editorEngine;
	UI uI;
	Recorder recorder;
	Memento memento;
	String mementoState;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		uI = Mockito.mock(UIImpl.class);
		recorder = Mockito.mock(RecorderImpl.class);
		enterTextRecordable = new EnterTextRecordable(editorEngine, uI, recorder);
		memento = Mockito.mock(MemChangeSelection.class);
		mementoState = "test ABC 123";
		
		Mockito.when(uI.getText()).thenReturn("Hello world");
		Mockito.when(memento.getSavedState()).thenReturn(mementoState);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.EnterTextRecordable#execute()}.
	 */
	@Test
	public void testExecute() {
		InOrder inOrder = Mockito.inOrder(recorder, editorEngine);
		
		Mockito.when(recorder.getRecording()).thenReturn(true);
		enterTextRecordable.execute();

		Mockito.when(recorder.getRecording()).thenReturn(false);
		Mockito.when(recorder.getReplaying()).thenReturn(true);
		enterTextRecordable.setMemento(memento);
		enterTextRecordable.execute();

		Mockito.when(recorder.getReplaying()).thenReturn(false);
		enterTextRecordable.execute();

		inOrder.verify(editorEngine).enterText("Hello world");
		inOrder.verify(recorder).record(enterTextRecordable);
		inOrder.verify(editorEngine).enterText(mementoState);
		inOrder.verify(editorEngine).enterText("Hello world");
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.EnterTextRecordable#getMemento()}.
	 */
	@Test
	public void testGetMemento() {
		enterTextRecordable.setMemento(memento);
		
		Memento m = enterTextRecordable.getMemento();
		
		String ret = (String) m.getSavedState();
		
		assertEquals(mementoState, ret);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.EnterTextRecordable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public void testSetMemento() {
		enterTextRecordable.setMemento(memento);
		Mockito.verify(memento).getSavedState();
	}

}
