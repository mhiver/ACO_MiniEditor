/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author 12001247
 *
 */
public class RecorderImplTest {
	RecorderImpl recorderImpl;
	Recordable command;
	Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		recorderImpl = new RecorderImpl();
		memento = Mockito.mock(Memento.class);
		command = Mockito.mock(Recordable.class);
		//Mockito.when(memento.getSavedState()).thenReturn("test OK");
		//Mockito.when(command.getMemento()).thenReturn(memento);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#RecorderImpl()}.
	 */
	@Test
	public final void testRecorderImpl() {
		assertFalse(recorderImpl.getRecording());
		assertFalse(recorderImpl.getReplaying());
		recorderImpl.begin();
		assertTrue(recorderImpl.getRecording());
		recorderImpl.end();
		assertFalse(recorderImpl.getRecording());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#record(fr.istic.aco.minieditor.v2.Recordable)}.
	 */
	@Test
	public final void testRecord() {
		recorderImpl.record(command);
		Mockito.verify(command).getMemento();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#replay()}.
	 */
	@Test
	public final void testReplay() {
		
	}

}
