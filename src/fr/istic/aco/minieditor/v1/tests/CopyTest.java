/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.istic.aco.minieditor.v1.Copy;
import fr.istic.aco.minieditor.v1.EditorEngineImpl;

/**
 * @author 12001247
 *
 */
public class CopyTest {
	Copy copy;
	EditorEngineImpl editorEngine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		editorEngine = Mockito.mock(EditorEngineImpl.class);
		copy = new Copy(editorEngine);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Copy#execute()}.
	 */
	@Test
	public final void testExecute() {
		copy.execute();
		Mockito.verify(editorEngine).copy();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Copy#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertEquals("Copy", copy.getName());
	}

}
