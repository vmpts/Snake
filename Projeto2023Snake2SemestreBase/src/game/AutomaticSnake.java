package game;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.Position;

import java.util.Iterator;

import environment.LocalBoard;
import gui.SnakeGui;
import environment.Cell;
import environment.Board;
import environment.BoardPosition;

public class AutomaticSnake extends Snake {
	public AutomaticSnake(int id, LocalBoard board) {
		super(id,board);

	}

	@Override
	public void run() {

		doInitialPositioning();
		while(!Thread.interrupted()) {
		try {
				
				Cell cell = new Cell(getPath().getLast());
				
				List<BoardPosition> neighborPositions = getBoard().getNeighboringPositions(cell);

				
				for (int i = 0; i < neighborPositions.size(); i++) {
				    BoardPosition boardPos = neighborPositions.get(i);
				    
				   
				    if (boardPos.x != cell.getPosition().x && boardPos.y != cell.getPosition().y) {
				    	neighborPositions.remove(i);
				        i--; 
				    } else
				    if (this.cells.contains(getBoard().getCell(boardPos))) {
				    	neighborPositions.remove(i);
				        i--; 
				    }
				}

				BoardPosition closestPosition = getBoard().selectPositionClosestToGoal(neighborPositions);
			


				if (closestPosition != null && !getBoard().getCell(closestPosition).isOcupiedBySnake()) {
					
					move(getBoard().getCell(closestPosition));
				}

				Thread.sleep(Board.PLAYER_PLAY_INTERVAL);
			}
		
	

			catch (InterruptedException e) {
				return;
			}
			getBoard().setChanged();
		}
	}
	
}