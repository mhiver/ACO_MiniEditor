/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.Cut;
import fr.istic.aco.minieditor.v1.EditorEngine;
import fr.istic.aco.minieditor.v1.EditorEngineImpl;

/**
 * @author 12001247
 *
 */
public class CutTest {
	private Cut cut;
	private EditorEngine editorEngine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		cut = new Cut(editorEngine);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Cut#execute()}.
	 */
	@Test
	public final void testExecute() {
		cut.execute();
		Mockito.verify(editorEngine).cut();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Cut#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Cut", cut.getName());
	}

}
