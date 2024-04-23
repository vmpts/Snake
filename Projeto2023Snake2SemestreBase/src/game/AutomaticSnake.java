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
			Thread.sleep(Board.PLAYER_PLAY_INTERVAL);	
				Cell cell = new Cell(getPath().getLast());
				
				List<BoardPosition> possibleCells = getBoard().getNeighboringPositions(cell);

				
				for (int i = 0; i < possibleCells.size(); i++) {
				    BoardPosition pos = possibleCells.get(i);
				    
				    // Verificar se a posição vizinha está na mesma linha ou coluna
				    if (pos.x != cell.getPosition().x && pos.y != cell.getPosition().y) {
				        possibleCells.remove(i);
				        i--; // Decrementar o índice para compensar a remoção do elemento
				    } else if (this.cells.contains(getBoard().getCell(pos))) {
				        possibleCells.remove(i);
				        i--; // Decrementar o índice para compensar a remoção do elemento
				    }
				}

				BoardPosition nextPosition = getBoard().selectPositionClosestToGoal(possibleCells);
			


				if (nextPosition != null && !getBoard().getCell(nextPosition).isOcupiedBySnake()) {
					
					move(getBoard().getCell(nextPosition));
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