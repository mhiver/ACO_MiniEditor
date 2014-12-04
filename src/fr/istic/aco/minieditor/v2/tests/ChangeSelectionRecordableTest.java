/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.ChangeSelection;
import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.EditorEngineImpl;
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v1.UIImpl;
import fr.istic.aco.minieditor.v2.ChangeSelectionRecordable;
import fr.istic.aco.minieditor.v2.MemChangeSelection;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author 12001247
 *
 */
public class ChangeSelectionRecordableTest {
	ChangeSelectionRecordable changeSelectionRecordable;
	EditorEngine editorEngine;
	UI uI;
	Recorder recorder;
	Memento memChangeSelection;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		uI = Mockito.mock(UIImpl.class);
		recorder = Mockito.mock(RecorderImpl.class);
		changeSelectionRecordable = new ChangeSelectionRecordable(editorEngine, uI, recorder);
		memChangeSelection = Mockito.mock(MemChangeSelection.class);
		
		Mockito.when(uI.getStart()).thenReturn(4);
		Mockito.when(uI.getEnd()).thenReturn(7);
		Mockito.when(memChangeSelection.getSavedState()).thenReturn(new int[]{5, 8});
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.ChangeSelectionRecordable#execute()}.
	 */
	@Test
	public final void testExecute() {
		InOrder inOrder = Mockito.inOrder(recorder, editorEngine);
		
		Mockito.when(recorder.getRecording()).thenReturn(true);
		changeSelectionRecordable.execute();

		Mockito.when(recorder.getRecording()).thenReturn(false);
		Mockito.when(recorder.getReplaying()).thenReturn(true);
		changeSelectionRecordable.setMemento(memChangeSelection);
		changeSelectionRecordable.execute();

		Mockito.when(recorder.getReplaying()).thenReturn(false);
		changeSelectionRecordable.execute();

		inOrder.verify(editorEngine).changeSelection(4, 7);
		inOrder.verify(recorder).record(changeSelectionRecordable);
		inOrder.verify(editorEngine).changeSelection(5, 8);
		inOrder.verify(editorEngine).changeSelection(4, 7);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.ChangeSelectionRecordable#getMemento()}.
	 */
	@Test
	public final void testGetMemento() {
		changeSelectionRecordable.setMemento(memChangeSelection);
		
		Memento m = changeSelectionRecordable.getMemento();
		
		int[] ret = (int[]) m.getSavedState();
		
		assertArrayEquals(new int[]{5, 8}, ret);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.ChangeSelectionRecordable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public final void testSetMemento() {
		changeSelectionRecordable.setMemento(memChangeSelection);
		Mockito.verify(memChangeSelection).getSavedState();
	}

}
