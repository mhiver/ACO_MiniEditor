/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v2.Begin;
import fr.istic.aco.minieditor.v2.Recorder;
import fr.istic.aco.minieditor.v2.RecorderImpl;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 *
 */
public class BeginTest {
	private Begin begin;
	private Recorder recorder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		recorder = Mockito.mock(RecorderImpl.class);
		begin = new Begin(recorder);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.Begin#execute()}.
	 */
	@Test
	public final void testExecute() {
		begin.execute();
		Mockito.verify(recorder).begin();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.Begin#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Begin macro", begin.getName());
	}

}
