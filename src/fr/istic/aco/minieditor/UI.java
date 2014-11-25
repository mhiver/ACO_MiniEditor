package fr.istic.aco.minieditor;

import java.io.InputStream;
import java.io.PrintStream;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface UI 
{

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public int getEnd();

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public int getStart();

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getText();
	
	 /**
	* Starts the reading of the read stream set by setReadStream operation
	*/
	public void runInvokerLoop();
	
	/**
	* Stops the read stream loop now.
	*/
	public void stopLoop();
	
	/**
	* Sets the read stream that will be used by runInvokerLoop
	*
	* @param inputStream the read stream
	* @throws IllegalArgumentException if inputStream is null
	*/
	public void setReadStream(InputStream inputStream);
	
	/**
	* Sets the print stream that will be used to display output and messages
	*
	* @param printStream the read stream
	* @throws IllegalArgumentException if printStream is null
	*/
	public void setPrintStream(PrintStream printStream);
	
	/**
	* Registers a new keyword/command pair
	*
	* @param keyword a non-null String
	* @param cmd a non-null Command reference
	* @throws java.lang.IllegalArgumentException for null parameters
	*/
	public void addCommand(String keyword, Command cmd);
	
}

