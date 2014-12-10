/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v1.Clipboard;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 *
 */
public class ClipboardTest {
	Clipboard clipboard;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		clipboard = new Clipboard();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Clipboard#Clipboard()}.
	 */
	@Test
	public final void testClipboard() {
		assertEquals("", clipboard.getText());
		String strings[] = {"abc", "A B C", "", "123 456 abc def"};
		
		for (String s: strings) {
			clipboard.setText(s);
			assertEquals(s, clipboard.getText());
		}
	}

}
