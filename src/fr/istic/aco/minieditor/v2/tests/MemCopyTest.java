/**
 * 
 */
package fr.istic.aco.minieditor.v2.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v2.MemCopy;
import fr.istic.aco.minieditor.v2.Memento;

/**
 * @author Matthieu
 *
 */
public class MemCopyTest {
	Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		memento = new MemCopy();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v2.MemCopy#getSavedState()}.
	 */
	@Test
	public void testGetSavedState() {
		assertNull(memento.getSavedState());
	}

}
