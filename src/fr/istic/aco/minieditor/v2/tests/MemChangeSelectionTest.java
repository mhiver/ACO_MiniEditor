/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v2.MemChangeSelection;
import fr.istic.aco.minieditor.v2.Memento;

/**
 * @author Matthieu
 *
 */
public class MemChangeSelectionTest {
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		memento = new MemChangeSelection(42, 1337);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.MemChangeSelection#getSavedState()}.
	 */
	@Test
	public void testGetSavedState() {
		int[] ret = (int[]) memento.getSavedState();
		assertArrayEquals(new int[]{42, 1337}, ret);
	}

}
