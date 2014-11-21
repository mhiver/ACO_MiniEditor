package fr.istic.aco.minieditor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Buffer
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private StringBuffer buffer;


	/**
	 * @param editorEngineImpl
	 * @param buffer
	 */
	public Buffer() {
		this.buffer = new StringBuffer();
	}

	public String getContent(int start, int end) {
		return buffer.substring(start, end);
	}
	
	public void setContent(int start, int end, String text) {
		buffer.replace(start, end, text);
	}
	
	public StringBuffer getBuffer() {
		return new StringBuffer(buffer);
	}
	
	public int getLength() {
		return buffer.length();
	}

}

