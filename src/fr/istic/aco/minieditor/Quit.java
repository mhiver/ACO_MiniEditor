package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Quit implements Command
{	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private UI uI;
	
	
	/**
	 * @param uI
	 */
	public Quit(UI uI) {
		this.uI = uI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void execute() {
		uI.stopLoop();
	}
	
	@Override
	public String getName() {
		return "Quit";
	}
	
}

