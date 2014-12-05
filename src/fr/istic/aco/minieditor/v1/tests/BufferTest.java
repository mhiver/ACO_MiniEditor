/**
 * 
 */
package fr.istic.aco.minieditor.v1.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.v1.Buffer;

/**
 * @author 12001247
 *
 */
public class BufferTest {
	Buffer buffer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		buffer = new Buffer();
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Buffer#Buffer()}.
	 */
	@Test
	public final void testBuffer() {
		assertEquals("", buffer.getBuffer().toString());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Buffer#getContent(int, int)}.
	 */
	@Test
	public final void testGetContent() {
		String text = "Test 123";
		buffer.setContent(0, 0, text);
		
		for (int i = 0, l = text.length(); i <= l; ++i)
			for (int j = i; j <= l; ++j)
				assertEquals(text.substring(i, j), buffer.getContent(i, j));
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Buffer#setContent(int, int, java.lang.String)}.
	 */
	@Test
	public final void testSetContent() {
		buffer.setContent(0, 0, "test");
		assertEquals("test", buffer.getBuffer().toString());
		buffer.setContent(4, 4, " 123");
		assertEquals("test 123", buffer.getBuffer().toString());
		buffer.setContent(0, 4, "Hello");
		assertEquals("Hello 123", buffer.getBuffer().toString());
		buffer.setContent(6, 9, "world");
		assertEquals("Hello world", buffer.getBuffer().toString());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Buffer#getBuffer()}.
	 */
	@Test
	public final void testGetBuffer() {
		buffer.setContent(0, 0, "test");
		StringBuffer sb = buffer.getBuffer();
		sb.append(" 123");
		assertEquals("test", buffer.getBuffer().toString());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.Buffer#getLength()}.
	 */
	@Test
	public final void testGetLength() {
		assertEquals(0, buffer.getLength());
		buffer.setContent(0, 0, "test");
		assertEquals(4, buffer.getLength());
		buffer.setContent(1, 3, "12");
		assertEquals(4, buffer.getLength());
		buffer.setContent(1, 3, "1234");
		assertEquals(6, buffer.getLength());
		buffer.setContent(2, 6, "");
		assertEquals(2, buffer.getLength());
	}

}
