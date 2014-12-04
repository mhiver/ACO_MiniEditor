/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;
import fr.istic.aco.minieditor.v2.Replay;

/**
 * @author 12001247
 *
 */
public class ReplayTest {
	Replay replay;
	Recorder recorder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		recorder = Mockito.mock(RecorderImpl.class);
		replay = new Replay(recorder);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.Replay#execute()}.
	 */
	@Test
	public final void testExecute() {
		replay.execute();
		Mockito.verify(recorder).replay();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.Replay#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Replay macro", replay.getName());
	}

}
