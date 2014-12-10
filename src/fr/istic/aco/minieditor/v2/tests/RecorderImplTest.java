/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.Command;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v2.Recordable;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 *
 */
public class RecorderImplTest {
	RecorderImpl recorderImpl;
	Recordable command, command2;
	Memento memento, memento2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		recorderImpl = new RecorderImpl();
		memento = Mockito.mock(Memento.class);
		command = Mockito.mock(Recordable.class, Mockito.withSettings().extraInterfaces(Command.class));
		Mockito.when(command.getMemento()).thenReturn(memento);
		memento2 = Mockito.mock(Memento.class);
		command2 = Mockito.mock(Recordable.class, Mockito.withSettings().extraInterfaces(Command.class));
		Mockito.when(command2.getMemento()).thenReturn(memento2);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#RecorderImpl()}.
	 */
	@Test
	public final void testRecorderImpl() {
		assertFalse(recorderImpl.getRecording());
		assertFalse(recorderImpl.getReplaying());
		assertEquals(0, recorderImpl.getMacroLength());
		assertEquals(0, recorderImpl.getMementosLength());
		assertEquals(0, recorderImpl.getNextMacroLength());
		assertEquals(0, recorderImpl.getNextMementosLength());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#record(fr.istic.aco.minieditor.v2.Recordable)}.
	 */
	@Test
	public final void testRecord() {
		recorderImpl.record(command);
		Mockito.verify(command).getMemento();
		assertEquals(1, recorderImpl.getNextMacroLength());
		assertEquals(1, recorderImpl.getNextMementosLength());
		assertEquals(command, recorderImpl.getNextMacroElement(0));
		assertEquals(memento, recorderImpl.getNextMementosElement(0));

		recorderImpl.record(command2);
		Mockito.verify(command2).getMemento();
		assertEquals(2, recorderImpl.getNextMacroLength());
		assertEquals(2, recorderImpl.getNextMementosLength());
		assertEquals(command2, recorderImpl.getNextMacroElement(1));
		assertEquals(memento2, recorderImpl.getNextMementosElement(1));
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#begin()}.
	 */
	@Test
	public final void testBegin() {
		recorderImpl.record(command);
		assertFalse(recorderImpl.getRecording());
		assertEquals(1, recorderImpl.getNextMacroLength());
		assertEquals(1, recorderImpl.getNextMementosLength());
		
		recorderImpl.begin();
		assertTrue(recorderImpl.getRecording());
		assertEquals(0, recorderImpl.getNextMacroLength());
		assertEquals(0, recorderImpl.getNextMementosLength());

		recorderImpl.record(command);
		recorderImpl.begin();
		assertTrue(recorderImpl.getRecording());
		assertEquals(1, recorderImpl.getNextMacroLength());
		assertEquals(1, recorderImpl.getNextMementosLength());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#end()}.
	 */
	@Test
	public final void testEnd() {
		recorderImpl.record(command);
		assertFalse(recorderImpl.getRecording());
		recorderImpl.end();
		assertFalse(recorderImpl.getRecording());
		assertEquals(0, recorderImpl.getMacroLength());
		assertEquals(0, recorderImpl.getMementosLength());

		recorderImpl.begin();
		recorderImpl.record(command);
		assertTrue(recorderImpl.getRecording());
		recorderImpl.end();
		assertFalse(recorderImpl.getRecording());
		assertEquals(1, recorderImpl.getMacroLength());
		assertEquals(1, recorderImpl.getMementosLength());
		assertEquals(recorderImpl.getNextMacroElement(0), recorderImpl.getMacroElement(0));
		assertEquals(recorderImpl.getNextMementosElement(0), recorderImpl.getMementosElement(0));
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.RecorderImpl#replay()}.
	 */
	@Test
	public final void testReplay() {
		recorderImpl.begin();
		recorderImpl.record(command);
		recorderImpl.record(command2);
		assertEquals(0, recorderImpl.getMacroLength());
		assertEquals(0, recorderImpl.getMementosLength());
		recorderImpl.end();
		assertEquals(2, recorderImpl.getMacroLength());
		assertEquals(2, recorderImpl.getMementosLength());

		InOrder inOrder = Mockito.inOrder(command, command2);
		recorderImpl.replay();
		inOrder.verify(command).setMemento(memento);
		inOrder.verify((Command)command).execute();
		inOrder.verify(command2).setMemento(memento2);
		inOrder.verify((Command)command2).execute();
		
		assertFalse(recorderImpl.getReplaying());

		assertEquals(2, recorderImpl.getNextMacroLength());
		assertEquals(2, recorderImpl.getNextMementosLength());
		assertEquals(command, recorderImpl.getNextMacroElement(0));
		assertEquals(memento, recorderImpl.getNextMementosElement(0));
		assertEquals(command2, recorderImpl.getNextMacroElement(1));
		assertEquals(memento2, recorderImpl.getNextMementosElement(1));
		
		assertEquals(2, recorderImpl.getMacroLength());
		assertEquals(2, recorderImpl.getMementosLength());
		assertEquals(command, recorderImpl.getMacroElement(0));
		assertEquals(memento, recorderImpl.getMementosElement(0));
		assertEquals(command2, recorderImpl.getMacroElement(1));
		assertEquals(memento2, recorderImpl.getMementosElement(1));
	}

}
