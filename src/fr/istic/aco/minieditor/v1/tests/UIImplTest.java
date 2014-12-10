package fr.istic.aco.minieditor.v1.tests;

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

import fr.istic.aco.minieditor.v1.Command;
import fr.istic.aco.minieditor.v1.Quit;
import fr.istic.aco.minieditor.v1.UIImpl;

/**
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 *
 */
public class UIImplTest {
	private UIImpl uI;
	private OutputStream os;
	
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#getEnd()}.
	 */
	@Test
	public final void testGetEnd() {
		uI.setPrintStream(new PrintStream(os));
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#getStart()}.
	 */
	@Test
	public final void testGetStart() {
		uI.setPrintStream(new PrintStream(os));
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
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#getText()}.
	 */
	@Test
	public final void testGetText() {
		uI.setPrintStream(new PrintStream(os));
		String inputs[] = {"bla", "", "42", "Abc Def Ghi", "1 2 3 4"};
		Iterator<String> it = Arrays.asList(inputs).iterator();
		
		InputStream is = new ByteArrayInputStream(inputArrayToString(inputs).getBytes());
		uI.setReadStream(is);
		
		while (it.hasNext()) {
			assertEquals(it.next(), uI.getText());
		}
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#addCommand(java.lang.String, fr.istic.aco.minieditor.v1.Command)}.
	 */
	@Test
	public final void testAddCommand() {
		assertTrue(uI.getCommands().isEmpty());
		
		Command cmd1 = new Quit(uI);
		uI.addCommand("q", cmd1);
		assertEquals(1, uI.getCommands().size());
		assertEquals(cmd1, uI.getCommands().get("q"));
		
		Command cmd2 = new Command() {
			@Override
			public String getName() {
				return "Test";
			}

			@Override
			public void execute() {
			}
		};
		uI.addCommand("test", cmd2);
		assertEquals(2, uI.getCommands().size());
		assertEquals(cmd2, uI.getCommands().get("test"));
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#setReadStream(java.io.InputStream)}.
	 */
	@Test
	public final void testSetReadStream() {
		assertNull(uI.getReadStream());

		uI.setReadStream(new InputStream() {
			@Override
			public int read() throws IOException {
				return 0;
			}
		});
		assertNotNull(uI.getReadStream());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#setReadStream(java.io.InputStream)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testSetReadStreamNull() {
		assertNull(uI.getReadStream());
		uI.setReadStream(null);
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#setPrintStream(java.io.PrintStream)}.
	 */
	@Test
	public final void testSetPrintStream() {
		assertNull(uI.getPrintStream());

		PrintStream ps1 = new PrintStream(os);
		uI.setPrintStream(ps1);
		assertEquals(ps1, uI.getPrintStream());
		
		PrintStream ps2 = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) throws IOException {
			}
		});
		uI.setPrintStream(ps2);
		assertEquals(ps2, uI.getPrintStream());
	}

	/**
	 * Test method for {@link fr.istic.aco.minieditor.v1.UIImpl#setPrintStream(java.io.PrintStream)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void testSetPrintStreamNull() {
		assertNull(uI.getPrintStream());
		uI.setPrintStream(null);
	}

}
