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
 * Classe qui implémente l'interface UI
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class UIImpl implements UI {
	
	/* ensemble des commandes visibles par l'uI*/
	private Map<String, Command> commands = new TreeMap<String, Command>();

	/* Sert à stopper la boucle de l'uI */
	private boolean stopLoop = false;

	/* Entrée utilisée par l'utilisateur */
	private BufferedReader bufferedReader;

	/* Sert à afficher les messages pour l'utilisateur */
	private PrintStream printStream;
	
	/*
	 * retourne la valeur entrer par l'utilisateur à travers l'uI dans bufferedReader
	 * 
	 * si valeur entrée par l'utilisateur est pas un entier, retourne -1
	 * 
	 * @return n > -2
	 */
	
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
	
	/*
	 * sert à l'affichage dans la sortie des commandes et de la touche associée
	 * 
	 * @return un String
	 */
	private String createCommandList() {
		if (commands.isEmpty())
			return "(no commands)";
		StringBuilder sb = new StringBuilder();
		
		for (Entry<String, Command> e: commands.entrySet()) {
			sb.append(e.getKey());
			sb.append('=');
			sb.append(e.getValue().getName());
			sb.append(", ");
		}

		return sb.substring(0, sb.length()-2);
	}

	/**
	 * @see fr.istic.aco.minieditor.UI#getEnd()
	 */
	@Override
	public int getEnd() {
		printStream.print("Selection end: ");
		return readUserNumber();
	}

	/**
	 * @see fr.istic.aco.minieditor.UI#getStart()
	 */
	@Override
	public int getStart() {
		printStream.print("Selection start: ");
		return readUserNumber();
	}

	/**
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


	/**
	 * @see fr.istic.aco.minieditor.UI#runInvokerLoop()
	 */
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


	/**
	 * @see fr.istic.aco.minieditor.UI#stopLoop()
	 */
	@Override
	public void stopLoop() {
		stopLoop = true;
	}

	/*
	 * Va lire entrée clavier à travers bufferedReader
	 * 
	 * @return String
	 * @throws IOException
	 */
	
	private String readUserInput() throws IOException {
		return bufferedReader.readLine();
	}
	
	/**
	 * @see fr.istic.aco.minieditor.UI#addCommand(String keyword, Command cmd)
	 */
	@Override
	public void addCommand(String keyword, Command cmd) {
		if ((keyword == null) || (cmd == null)) {
			throw new IllegalArgumentException("null parameter");
		}
		commands.put(keyword,cmd);
	}

	public Map<String, Command> getCommands() {
		return new TreeMap<String, Command>(commands);
	}
	
	/**
	 * @see fr.istic.aco.minieditor.UI#setReadStream(InputStream inputStream)
	 */
	@Override
	public void setReadStream(InputStream inputStream) {
		if (inputStream == null) {
			throw new IllegalArgumentException("null inputStream");
		}
		this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	}

	public BufferedReader getReadStream() {
		return bufferedReader;
	}
	
	/**
	 * @see fr.istic.aco.minieditor.UI#setPrintStream(PrintStream printStream)
	 */
	@Override
	public void setPrintStream(PrintStream printStream) {
		if (printStream == null) {
			throw new IllegalArgumentException("null printStream");
		}
		this.printStream = printStream;
	}
	
	
	/**
	 * Retourne l'attribut printStream
	 * @return PrintStream
	 */
	public PrintStream getPrintStream() {
		return printStream;
	}

	@Override
	public void printText(String text) {
		printStream.println(text);
	}
}
