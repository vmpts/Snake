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
		try { 
			Cell head = new Cell(getPath().getLast());
			List<BoardPosition> possiblePos = getBoard().getNeighboringPositions(head);
			Iterator<BoardPosition> iterator = possiblePos.iterator();
			while (iterator.hasNext()) {
			    BoardPosition pos = iterator.next();
			    if (getBoard().getCell(pos).isOcupied() || getPath().contains(pos)) {
			        iterator.remove();
			    }
			}
			
			BoardPosition bestPos = getBoard().selectPositionClosestToGoal(possiblePos);
			
			if (bestPos == null && getBoard().getCell(bestPos).isOcupiedBySnake()) {
				
				Thread.sleep(Board.PLAYER_PLAY_INTERVAL);
			}
			else {
				move(getBoard().getCell(bestPos));
			
		}
		} catch (InterruptedException e) {
			return;
		}
			
		}
	
	}
	

	

