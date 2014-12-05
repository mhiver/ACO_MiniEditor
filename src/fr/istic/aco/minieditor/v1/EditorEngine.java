package fr.istic.aco.minieditor.v1;



/**
 * Interface qui joue le rôle de receveur dans le patron de conception Command
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.0
 */

public  interface EditorEngine 
{
	/**
	 * va changer la sélection courante grâce à start et end
	 * 
	 * start et end doivent être de type int
	 * 
	 * entrée quelconque gestion des cas à problème dans l'implémentation de changeSelection
	 * 
	 * 
	 * @param int start
	 * @param int end
	 */
	
	public void changeSelection(int start, int end) ;
	
	/**
	 * va remplacer le contenu de Clipboard par le texte definie par :
	 * 
	 *  le subString du buffer compris entre selection.start et selection.end
	 *  
	 */
	
	public void copy() ;
	
	/**
	 * va remplacer le contenu de Clipboard par le texte definie par :
	 * 
	 *  le subString du buffer compris entre selection.start et selection.end
	 *  
	 *  
	 *  et ensuite va rendre la sélection nulle en faisant : 
	 *      selection.end recoit selection.start
	 */
	
	public void cut() ;
	
	/**
	 *  va remplacer modifier le contenu de Buffer et de selection ainsi :
	 *  
	 *  	la sous-chaine contenue entre selection.start et selection.end va devenir text
	 *  	puis
	 *  	selection.start = selection.start + text.length
	 *  	puis 
	 *      selection.end = selection.start
	 *  
	 *  @param String text
	 */
	
	public void enterText(String text) ;
	
	/**
	 *  va remplacer modifier le contenu de Buffer et de selection ainsi :
	 *  
	 *  	la sous-chaine contenue entre selection.start et selection.end va devenir clipboard.text
	 *  	puis
	 *  	selection.start = selection.start + clipboard.text.length
	 *  	puis 
	 *      selection.end = selection.start
	 *  
	 *  @param String text
	 */
	
	public void paste() ;
	
	/**
	 * Retourne une chaîne représentant les données associée au Buffer, Clipboard et Selection
	 *
	 * @return String
	 */
	public String printData();
	
	
}

