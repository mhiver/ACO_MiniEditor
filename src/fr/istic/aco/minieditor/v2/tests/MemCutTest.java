/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v2.MemCut;
import fr.istic.aco.minieditor.v2.Memento;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.1
 *
 */
public class MemCutTest {
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		memento = new MemCut();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.MemCut#getSavedState()}.
	 */
	@Test
	public void testGetSavedState() {
		assertNull(memento.getSavedState());
	}

}
