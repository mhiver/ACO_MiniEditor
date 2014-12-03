package fr.istic.aco.minieditor.v2;

import fr.istic.aco.minieditor.*;;
/**
 * Classe qui joue le rôle de client dans le patron de conception Command
 * 
 * Contient la méthode main()
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class AppEditor_v2
{
	/* Objet qui joue le rôle de receveur dans le patron de conception Command*/

	private EditorEngine editorEngine;
	
	/* objet qui joue le role de caretaker
	 * objet qui joue le role de receveur pour les commandes Begin et Replay
	 * 
	*/
	
	private Recorder recorder;

	/* Objet qui joue le rôle d'invoqueur dans le patron de conception Command
	 * 
	 * Objet qui joue le rôle d'invoqueur et de receveur pour la commande concrète "Quit"
	 */
	
	private UI uI;

	/**
	 * méthode main qui créer tout les objets nécessaire et lance la boucle de l'invoqueur
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AppEditor_v2 editor = new AppEditor_v2();
		editor.run();
	}
	
	/**
	 * méthode qui va lancer la boucle principale de l'éditeur de texte après avoir initialisé
	 * tout les objets nécessaires
	 * 
	 * 
	 */
	private void run() {
		editorEngine = new EditorEngineImpl();
		recorder = new RecorderImpl();
		uI = new UIImpl();
		uI.setReadStream(System.in);
		uI.setPrintStream(System.out);
		configureCommands();
		uI.runInvokerLoop();
		
		System.out.println("Bye!");
	}

	
	/**
	 * initialise les commandes standards de l'éditeur de texte
	 */
	private void configureCommands() {
		uI.addCommand("c", new CopyRecordable(editorEngine, recorder));
		uI.addCommand("x", new CutRecordable(editorEngine, recorder));
		uI.addCommand("v", new PasteRecordable(editorEngine, recorder));
		uI.addCommand("i", new EnterTextRecordable(editorEngine, uI, recorder));
		uI.addCommand("s", new ChangeSelectionRecordable(editorEngine, uI, recorder));
		uI.addCommand("q", new Quit(uI));
		uI.addCommand("p", new PrintData(editorEngine));
		
		uI.addCommand("r", new Replay(recorder));
		uI.addCommand("b", new Begin(recorder));
		uI.addCommand("e", new End(recorder));
	}
}

