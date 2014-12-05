/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.Quit;
import fr.istic.aco.minieditor.v1.UI;
import fr.istic.aco.minieditor.v1.UIImpl;

/**
 * @author 12001247
 *
 */
public class QuitTest {
	private Quit quit;
	private UI uI;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		uI = Mockito.mock(UIImpl.class);
		quit = new Quit(uI);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Quit#execute()}.
	 */
	@Test
	public final void testExecute() {
		quit.execute();
		Mockito.verify(uI).stopLoop();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Quit#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Quit", quit.getName());
	}

}
