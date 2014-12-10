package fr.istic.aco.minieditor.v3;

import fr.istic.aco.minieditor.v1.*;
/**
 * Classe qui joue le rôle de client dans le patron de conception Command
 * 
 * Contient la méthode main()
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
 */

public class AppEditor_v3
{
	/* Objet qui joue le rôle de receveur dans le patron de conception Command*/

	private EditorEngine editorEngine;
	
	/* objet qui joue le role de caretaker
	 * objet qui joue le role de receveur pour les commandes Begin et Replay
	 * 
	*/
	
	private UndoRedoManager undoRedoManager;

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
		AppEditor_v3 editor = new AppEditor_v3();
		editor.run();
	}
	
	/**
	 * méthode qui va lancer la boucle principale de l'éditeur de texte après avoir initialisé
	 * tout les objets nécessaires
	 * 
	 * 
	 */
	private void run() {
		editorEngine = new EditorEngineImplRecordable();
		undoRedoManager = new UndoRedoManagerImpl(editorEngine);
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
		uI.addCommand("c", new CopyUndoable(editorEngine, undoRedoManager));
		uI.addCommand("x", new CutUndoable(editorEngine, undoRedoManager));
		uI.addCommand("v", new PasteUndoable(editorEngine, undoRedoManager));
		uI.addCommand("i", new EnterTextUndoable(editorEngine, uI, undoRedoManager));
		uI.addCommand("s", new ChangeSelectionUndoable(editorEngine, uI, undoRedoManager));
		uI.addCommand("q", new Quit(uI));
		uI.addCommand("p", new PrintData(editorEngine, uI));
		
		uI.addCommand("u", new Undo(undoRedoManager));
		uI.addCommand("r", new Redo(undoRedoManager));
	}
}

