package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {
		
		crossword = new Cell [puzzle.length][puzzle[0].length];
		Cell aCell = null;
		CellType state = null;
		String apoyo = "";
		int count = 1;
		for (int i=0; i< crossword.length; i++ ) { // filas numbers.length
			for (int j = 0; j < crossword[0].length; j++) { //columnas numbers[0].length
			   apoyo = puzzle[i][j];
			   if(apoyo == " "){
				   state = CellType.BLACK;
				   aCell = new Cell(state, apoyo, -1);
				   crossword[i][j] = aCell;
			   }else{
				   state = CellType.CLOSED;
				   aCell = new Cell(state, apoyo, count);
				   crossword[i][j] = aCell;
				   count++;
			   }
				
			}
		
		}
		
	}
	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */
	public String getHint(String letter) {
		String out = "";
		boolean flag = true;
		for (int i=0; i< crossword.length; i++ ) { // filas numbers.length
			for (int j = 0; j < crossword[0].length; j++) { //columnas numbers[0].length
			
					
				if(crossword[i][j].getState() ==CellType.CLOSED && flag == true){
				
					if(crossword[i][j].getLetter().equals(letter)){
						out = "Hay una palabra con la letra " + letter +" en el crucigrama en la casilla " + crossword[i][j].getNumber();
						crossword[i][j].setState(CellType.OPEN);
						flag = false;
					}else{
						out = "Lo siento, no hay palabras con la letra " + letter;
					}
				}
			}
		
		}

		return out;
	}
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {
		
		return null;
	}
	
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					

					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"    ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
