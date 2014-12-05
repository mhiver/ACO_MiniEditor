/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v2.MemPaste;
import fr.istic.aco.minieditor.v2.Memento;

/**
 * @author Matthieu
 *
 */
public class MemPasteTest {
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		memento = new MemPaste();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.MemPaste#getSavedState()}.
	 */
	@Test
	public void testGetSavedState() {
		assertNull(memento.getSavedState());
	}

}
