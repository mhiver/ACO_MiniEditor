/**
 * 
 */
package fr.istic.aco.minieditor.v3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v3.ChangeSelectionOpposite;
import fr.istic.aco.minieditor.v3.ChangeSelectionUndoable;
import fr.istic.aco.minieditor.v3.UndoRedoManager;

/**
 * @author 12001247
 *
 */
public class ChangeSelectionUndoableTest {
	private UndoRedoManager undoRedoManager; 
	private EditorEngine editorEngine;
	private UI uI;
	private ChangeSelectionUndoable changeSelectionUndoable;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		undoRedoManager = Mockito.mock(UndoRedoManager.class);
		editorEngine = Mockito.mock(EditorEngine.class);
		uI = Mockito.mock(UI.class);
		memento = Mockito.mock(Memento.class);
		changeSelectionUndoable = new ChangeSelectionUndoable(editorEngine, uI, undoRedoManager);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.ChangeSelectionUndoable#execute()}.
	 */
	@Test
	public final void testExecute() {
		InOrder inOrder = Mockito.inOrder(undoRedoManager, editorEngine);

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		changeSelectionUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(ChangeSelectionOpposite.class));
		inOrder.verify(editorEngine).changeSelection(0, 0);
		
		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(false);
		Mockito.when(uI.getStart()).thenReturn(10);
		Mockito.when(uI.getEnd()).thenReturn(20);
		changeSelectionUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(ChangeSelectionOpposite.class));
		inOrder.verify(editorEngine).changeSelection(10, 20);

		Mockito.when(undoRedoManager.getIsInRedo()).thenReturn(true);
		Mockito.when(uI.getStart()).thenReturn(30);
		Mockito.when(uI.getEnd()).thenReturn(40);
		changeSelectionUndoable.execute();
		inOrder.verify(undoRedoManager).record(Mockito.any(ChangeSelectionOpposite.class));
		inOrder.verify(editorEngine).changeSelection(10, 20);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.ChangeSelectionUndoable#setMemento(fr.istic.aco.minieditor.v2.Memento)}.
	 */
	@Test
	public final void testSetMemento() {
		int[] data = new int[]{42, 1337};
		Mockito.when(memento.getSavedState()).thenReturn(data);

		int[] ret = (int[]) (changeSelectionUndoable.getMemento().getSavedState());
		assertArrayEquals(new int[]{0, 0}, ret);
		
		changeSelectionUndoable.setMemento(memento);
		Mockito.verify(memento).getSavedState();
		
		ret = (int[]) (changeSelectionUndoable.getMemento().getSavedState());
		assertArrayEquals(data, ret);
	}

}
