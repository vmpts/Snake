package game;

import java.io.Serializable;
import game.Goal;
import java.util.LinkedList;

import environment.LocalBoard;
import gui.SnakeGui;
import environment.Board;
import environment.BoardPosition;
import environment.Cell;

public abstract class Snake extends Thread {

	private boolean killed = false ;
	protected LinkedList<Cell> cells = new LinkedList<Cell>();
	protected int size = 5;
	private int id;
	private Board board;

	public Snake(int id,Board board) {
		this.id = id;
		this.board=board;
	}

	
	public void killSnake () { killed = true ; }
	public boolean wasKilled () { return killed == true ;}
	
	public int getSize() {
		return size;
	}

	public int getIdentification() {
		return id;
	}

	public int getCurrentLength() {
		return cells.size();
	}
	
	public LinkedList<Cell> getCells() {
		return cells;
	}
	
		protected void move(Cell cell) throws InterruptedException {
        if (getBoard().isFinished()) {
            return;
        }
        if(!cell.isOcupied()) {
        if (getCells().size() >= getSize()) {
	        getCells().removeFirst().release(); 
	    }
       
        if (cell.isOcupiedByGoal()) {
        	 Goal g = cell.getGoal();
        	 if (g.getValue() == g.MAX_VALUE-1) {
 				board.setFinished(true);
 			
 			
 			}
            g.captureGoal(g);
            this.size= this.size+(g.getGoalValue()-1);
      }

        cell.request(this); 
        getCells().addLast(cell); 
        board.setChanged();
	}
		}

		
		
	public void setSize(int size) {
		this.size = size;
	}


	protected void doInitialPositioning() {
		Cell c = null;
		int posAleatoriaY;
		int posAleatoriaX = 0;
		for (int y=0;y<board.HEIGHT;y++) {
			 posAleatoriaY = (int) (Math.random() * board.HEIGHT);
			BoardPosition BoardPosition= new BoardPosition(posAleatoriaX,posAleatoriaY);
		
			c=board.getCell(BoardPosition);
			 if (!c.isOcupied()&& c!=null) {
				 break;
				  }
			 else {
				 c=null;
			 }
		}
		try { 
			c.request(this);
			cells.add(c);
				
		}
		catch(InterruptedException e){
			Thread.currentThread().interrupt(); 
	        
	    }
		}
		
	
	
	public Board getBoard() {
		return board;
	}
	
	// Utility method to return cells occupied by snake as a list of BoardPosition
	// Used in GUI. Do not alter
	public synchronized LinkedList<BoardPosition> getPath() {
		LinkedList<BoardPosition> coordinates = new LinkedList<BoardPosition>();
		for (Cell cell : cells) {
			coordinates.add(cell.getPosition());
		}

		return coordinates;
	}	
}
