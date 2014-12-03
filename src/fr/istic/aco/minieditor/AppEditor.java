package fr.istic.aco.minieditor;


/**
 * Classe qui joue le rôle de client dans le patron de conception Command
 * 
 * Contient la méthode main()
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class AppEditor
{
	/* Objet qui joue le rôle de receveur dans le patron de conception Command*/

	private EditorEngine editorEngine;

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
		AppEditor editor = new AppEditor();
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
		uI.addCommand("C", new Copy(editorEngine));
		uI.addCommand("x", new Cut(editorEngine));
		uI.addCommand("v", new Paste(editorEngine));
		uI.addCommand("i", new EnterText(editorEngine, uI));
		uI.addCommand("s", new ChangeSelection(editorEngine, uI));
		uI.addCommand("q", new Quit(uI));
		uI.addCommand("p", new PrintData(editorEngine));
	}
}

