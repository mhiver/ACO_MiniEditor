/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.Cut;
import fr.istic.aco.minieditor.EditorEngineImpl;

/**
 * @author 12001247
 *
 */
public class CutTest {
	Cut cut;
	EditorEngineImpl editorEngine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		cut = new Cut(editorEngine);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Cut#execute()}.
	 */
	@Test
	public final void testExecute() {
		cut.execute();
		Mockito.verify(editorEngine).cut();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.Cut#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Cut", cut.getName());
	}

}
