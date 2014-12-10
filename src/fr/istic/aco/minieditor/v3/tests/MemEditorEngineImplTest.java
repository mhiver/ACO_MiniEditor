/**
 * 
 */
package fr.istic.aco.minieditor.v3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import fr.istic.aco.minieditor.v2.Memento;
import fr.istic.aco.minieditor.v3.MemEditorEngineImpl;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 *
 */
public class MemEditorEngineImplTest {
	StringBuffer stringBuffer;
	String clipboardText;
	private Memento memento;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stringBuffer = new StringBuffer();
		clipboardText = "Hello world";
		memento = new MemEditorEngineImpl(stringBuffer, clipboardText, 3, 8);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v3.MemEditorEngineImpl#getSavedState()}.
	 */
	@Test
	public final void testGetSavedState() {
		Object[] ret = (Object[]) memento.getSavedState();
		assertArrayEquals(new Object[] {stringBuffer, clipboardText, 3, 8}, ret);
	}

}
