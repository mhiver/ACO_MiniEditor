/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.Quit;
import fr.istic.aco.minieditor.UIImpl;

/**
 * @author 12001247
 *
 */
public class QuitTest {
	Quit quit;
	UIImpl uI;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		uI = Mockito.mock(UIImpl.class);
		quit = new Quit(uI);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Quit#execute()}.
	 */
	@Test
	public final void testExecute() {
		quit.execute();
		Mockito.verify(uI).stopLoop();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Quit#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Quit", quit.getName());
	}

}
