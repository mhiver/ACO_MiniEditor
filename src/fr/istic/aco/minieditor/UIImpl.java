/**
 * 
 */
package fr.istic.aco.minieditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author 12001247
 *
 */
public class UIImpl implements UI {
	private Map<String, Command> commands = new TreeMap<String, Command>();
	private boolean stopLoop = false;
	private BufferedReader bufferedReader;
	private PrintStream printStream;
	
	private int readUserNumber() {
		String s = "";
		try {
			s = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int n = -1;
		try {
			n = Integer.parseInt(s);
		} catch (NumberFormatException e) {}
		
		return n;
	}
	
	private String createCommandList() {
		StringBuilder sb = new StringBuilder();
		
		for (Entry<String, Command> e: commands.entrySet()) {
			sb.append(e.getKey());
			sb.append('=');
			sb.append(e.getValue().getName());
			sb.append(", ");
		}

		return sb.substring(0, sb.length()-2);
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.UI#getEnd()
	 */
	@Override
	public int getEnd() {
		printStream.print("Selection end: ");
		return readUserNumber();
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.UI#getStart()
	 */
	@Override
	public int getStart() {
		printStream.print("Selection start: ");
		return readUserNumber();
	}

	/* (non-Javadoc)
	 * @see fr.istic.aco.minieditor.UI#getText()
	 */
	@Override
	public String getText() {
		String s = "";
		printStream.print("Text: ");
		try {
			s = readUserInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return s;
	}

	@Override
	public void runInvokerLoop() {
		while (!stopLoop) {
			String userInput = null;
			printStream.println("Command: " + createCommandList());
			try {
				userInput = readUserInput();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (userInput == null) {
				stopLoop = true;
				break;
			}
			Command cmdToExecute = commands.get(userInput);
			if (cmdToExecute != null) {
				cmdToExecute.execute();
			}
		}
	}

	@Override
	public void stopLoop() {
		stopLoop = true;
	}

	private String readUserInput() throws IOException {
		return bufferedReader.readLine();
	}

	/**
	 * Registers a new keyword/command pair
	 *
	 * @param keyword a non-null String
	 * @param cmd     a non-null Command reference
	 * @throws java.lang.IllegalArgumentException for null parameters
	 */
	@Override
	public void addCommand(String keyword, Command cmd) {
		if ((keyword == null) || (cmd == null)) {
			throw new IllegalArgumentException("null parameter");
		}
		commands.put(keyword,cmd);
	}

	@Override
	public void setReadStream(InputStream inputStream) {
		if(inputStream == null) {
			throw new IllegalArgumentException("null inputStream");
		}
		this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	}

	@Override
	public void setPrintStream(PrintStream printStream) {
		if(printStream == null) {
			throw new IllegalArgumentException("null printStream");
		}
		this.printStream = new PrintStream(printStream, true);
	}
}
