/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.Selection;

/**
 * @author 12001247
 *
 */
public class SelectionTest {
	Selection selection;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		selection = new Selection();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Selection#Selection()}.
	 */
	@Test
	public final void testSelection() {
		assertEquals(0, selection.getStart());
		assertEquals(0, selection.getEnd());
		selection.setStart(5);
		assertEquals(5, selection.getStart());
		selection.setEnd(8);
		assertEquals(8, selection.getEnd());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Selection#setLength(int)}.
	 */
	@Test
	public final void testSetLength() {
		selection.setLength(5);
		assertEquals(0, selection.getStart());
		assertEquals(5, selection.getEnd());
		selection.setStart(4);
		selection.setLength(8);
		assertEquals(4, selection.getStart());
		assertEquals(12, selection.getEnd());
	}

}
