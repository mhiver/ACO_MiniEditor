package fr.istic.aco.minieditor.v1;



/**
 * Interface qui joue le rôle de receveur dans le patron de conception Command
 * 
 * @author Baptiste Tessiau 
 * @author Matthieu Hiver
 * @version 1.2
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

	/**
	 * retourne le texte du buffer défini par la sélection
	 * 
	 * @return String
	 */
	
	public String getSelectionText();

	/**
	 * retourne le text dans le clipboard
	 * @return Strin
	 */
	public String getClipboardText();

	/**
	 * retourne l'entier de début de la sélection
	 * @return int
	 */
	public int getStartSelection();


	/**
	 * retourne l'entier de fin de la sélection
	 * @return int
	 */
	public int getEndSelection();

	/**
	 * Inverse de la commande Copy
	 * @param clipBoardText 
	 * 
	 */
	public void copyOpposite(String clipBoardText);

	/**
	 * méthode qui inverse l'effet d'une méthode couper effectuée
	 * 
	 * @param clipBoardText
	 * @param textInBuffer
	 * @param oldStartSelection
	 * @param oldEndSelection
	 */
	public void cutOpposite(String clipBoardText, String textInBuffer,
			int oldStartSelection, int oldEndSelection);

	/**
	 * méthode qui inverse l'effet d'une méthode enterText effectuée
	 * 
	 * @param textInBuffer
	 * @param oldStartSelection
	 * @param oldEndSelection
	 */
	public void enterTextOpposite(String textInBuffer,
			int oldStartSelection, int oldEndSelection);

	/**
	 * inverse de changeSelection pour un selection effectué
	 * 
	 * @param oldStartSelection
	 * @param oldEndSelection
	 */
	public void changeSelectionOpposite(int oldStartSelection,
			int oldEndSelection);


	/**
	 * méthode qui inverse l'effet d'une méthode paste effectuée
	 * 
	 * @param textInBuffer
	 * @param oldStartSelection
	 * @param oldEndSelection
	 */
	public void pasteUndoable(String textInBuffer, int oldStartSelection,
			int oldEndSelection);

	
	
}

