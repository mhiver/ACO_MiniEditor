/**
 * 
 */
package fr.istic.aco.minieditor_test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import fr.istic.aco.minieditor.UIImpl;

/**
 * @author 12001247
 *
 */
public class UIImplTest {
	
	UIImpl uI;
	OutputStream os;
	
	private String inputArrayToString(String[] arr) {
		StringBuilder res = new StringBuilder();
		for (String s: arr) {
			res.append(s);
			res.append('\n');
		}
		return res.toString();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		uI = new UIImpl();
		os = new OutputStream() {
			@Override
			public void write(int b) throws IOException {}
		};
		uI.setPrintStream(new PrintStream(os));
		
		/*
		EditorEngine editorEngine = new EditorEngineImpl();
		uI.addCommand("c", new Copy(editorEngine));
		uI.addCommand("x", new Cut(editorEngine));
		uI.addCommand("v", new Paste(editorEngine));
		uI.addCommand("i", new EnterText(editorEngine, uI));
		uI.addCommand("s", new ChangeSelection(editorEngine, uI));
		uI.addCommand("q", new Quit(uI));
		uI.addCommand("p", new PrintData(editorEngine));
		*/
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#getEnd()}.
	 */
	@Test
	public final void testGetEnd() {
		String inputs[] = {"5", "0", "-5", "", "toto"};
		
		InputStream is = new ByteArrayInputStream(inputArrayToString(inputs).getBytes());
		uI.setReadStream(is);
		assertEquals(5, uI.getEnd());
		assertEquals(0, uI.getEnd());
		assertEquals(-5, uI.getEnd());
		assertEquals(-1, uI.getEnd());
		assertEquals(-1, uI.getEnd());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#getStart()}.
	 */
	@Test
	public final void testGetStart() {
		String inputs[] = {"5", "0", "-5", "", "toto"};
		
		InputStream is = new ByteArrayInputStream(inputArrayToString(inputs).getBytes());
		uI.setReadStream(is);
		assertEquals(5, uI.getStart());
		assertEquals(0, uI.getStart());
		assertEquals(-5, uI.getStart());
		assertEquals(-1, uI.getStart());
		assertEquals(-1, uI.getStart());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#getText()}.
	 */
	@Test
	public final void testGetText() {
		String inputs[] = {"bla", "", "42", "abc def ghi", "1 2 3 4"};
		Iterator<String> it = Arrays.asList(inputs).iterator();
		
		InputStream is = new ByteArrayInputStream(inputArrayToString(inputs).getBytes());
		uI.setReadStream(is);
		
		while (it.hasNext()) {
			assertEquals(it.next(), uI.getText());
		}
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#runInvokerLoop()}.
	 */
	@Test
	public final void testRunInvokerLoop() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#stopLoop()}.
	 */
	@Test
	public final void testStopLoop() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#addCommand(java.lang.String, fr.istic.aco.minieditor.Command)}.
	 */
	@Test
	public final void testAddCommand() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#setReadStream(java.io.InputStream)}.
	 */
	@Test
	public final void testSetReadStream() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.UIImpl#setPrintStream(java.io.PrintStream)}.
	 */
	@Test
	public final void testSetPrintStream() {
		fail("Not yet implemented"); // TODO
	}

}
