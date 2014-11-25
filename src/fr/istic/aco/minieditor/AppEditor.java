package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class AppEditor
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private EditorEngine editorEngine;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private UI uI;

	public static void main(String[] args) {
		AppEditor editor = new AppEditor();
		editor.run();
	}

	private void run() {
		editorEngine = new EditorEngineImpl();
		uI = new UIImpl();
		uI.setReadStream(System.in);
		uI.setPrintStream(System.out);
		configureCommands();
		uI.runInvokerLoop();
		
		System.out.println("Bye!");
	}

	private void configureCommands() {
		uI.addCommand("c", new Copy(editorEngine));
		uI.addCommand("x", new Cut(editorEngine));
		uI.addCommand("v", new Paste(editorEngine));
		uI.addCommand("i", new EnterText(editorEngine, uI));
		uI.addCommand("s", new ChangeSelection(editorEngine, uI));
		uI.addCommand("q", new Quit(uI));
		uI.addCommand("p", new PrintData(editorEngine));
	}
}

