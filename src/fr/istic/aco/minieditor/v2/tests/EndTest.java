/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v2.End;
import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 *
 */
public class EndTest {
	End end;
	Recorder recorder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		recorder = Mockito.mock(RecorderImpl.class);
		end = new End(recorder);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.End#execute()}.
	 */
	@Test
	public final void testExecute() {
		end.execute();
		Mockito.verify(recorder).end();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.End#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("End macro", end.getName());
	}

}
