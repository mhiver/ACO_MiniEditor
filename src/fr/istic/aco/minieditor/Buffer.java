package fr.istic.aco.minieditor;


/**
 * Classe qui représente l'état courant de du clipboard de notre editeur
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class Buffer
{
	/**
	 * attribut buffer représente l'ensemble du texte tapé dans le buffer de l'éditeur
	 * 
	 */


	private StringBuffer buffer;


	public Buffer() {
		this.buffer = new StringBuffer();
	}


	/**
	 * Retourne un substring de l'attribut buffer
	 *  
	 * @param start >= 0 tel que start <= buffer.size()
	 * @param end >= start tel que end <= buffer.size()
	 * @return un string compris entre start et end
	 */
	public String getContent(int start, int end) {
		return buffer.substring(start, end);
	}



	/**
	 * modifie un substring de l'attribut buffer
	 *  
	 * @param start >= 0 tel que start <= buffer.size()
	 * @param end >= start tel que end <= buffer.size()
	 * @param text de type String
	 *  
	 * remplace la sous chaine compris entre start et end par la sous chaine text
	 */
	public void setContent(int start, int end, String text) {
		buffer.replace(start, end, text);
	}

	/**
	 * retourne une copie de l'attribut buffer
	 * 
	 * @return type StringBuffer
	 */
	public StringBuffer getBuffer() {
		return new StringBuffer(buffer);
	}


	/**
	 * retourne la taille du buffer
	 * 
	 * @return un entier >= 0
	 */
	public int getLength() {
		return buffer.length();
	}

}

