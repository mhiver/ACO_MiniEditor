package fr.istic.aco.minieditor.v1;


/**
 * Classe qui représente l'état courant de la sélection de notre editeur
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public class Selection
{

  /* attribut start représente le curseur début de la sélection
	 attribut end représente le curseur début de la sélection
	  
	 start > = 0
	 end > = 0
	 
	 end >= start*/
	 

	private int start;
	private int end;

	
	/**
	 * positionne les attributs start et end sur 0
	 */
	
	public Selection() {
		this.start = 0;
		setLength(0);
	}

	/**
	 * Retourne attribut start
	 * @return start >= 0
	 */
	public int getStart() {
		return start;
	}


	/**
	 * Retourne attribut end
	 * @return end >= 0 
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * modifie attribut start
	 * @param s >= 0 la nouvelle valeur de start
	 */
	public void setStart(int s) {
		start = s;
	}

	/**
	 * modifie attribut end
	 * @param e >= 0 la nouvelle valeur de end telle que e >= start
	 */
	
	public void setEnd(int e) {
		end = e;
	}
	


	/**
	 * modifie attribut end pour modifier end - start
	 * @param l >= 0 la nouvelle valeur de end - start
	 * 
	 */
	
	public void setLength(int l) {
		end = start + l;
	}

}

