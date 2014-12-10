/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v2.MemEnterText;
import fr.istic.aco.minieditor.v2.Memento;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 *
 */
public class MemEnterTextTest {
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		memento = new MemEnterText("Hello world");
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.MemEnterText#getSavedState()}.
	 */
	@Test
	public void testGetSavedState() {
		String ret = (String) memento.getSavedState();
		assertEquals("Hello world", ret);
	}

}
