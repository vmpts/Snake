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
			
		
//			super.move(nextCell());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
//	}

		
				Cell cell = new Cell(getPath().getLast());
				
				List<BoardPosition> possibleCells = getBoard().getNeighboringPositions(cell);

				
				Iterator<BoardPosition> iterator = possibleCells.iterator();
				while (iterator.hasNext()) {
					BoardPosition pos = iterator.next();
					if (this.cells.contains(getBoard().getCell(pos))) {
						iterator.remove();
					}
				}

				BoardPosition nextPosition = getBoard().selectPositionClosestToGoal(possibleCells);
			
//            System.out.println(nextPosition);
//            System.out.println(getBoard().getGoalPosition());

				if (nextPosition != null && !getBoard().getCell(nextPosition).isOcupiedBySnake()) {
					// System.out.println("entrei");
					// cells.add(getBoard().getCell(nextPosition));
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
	
    public Cell nextCell() {
        BoardPosition goalPosition = getBoard().getGoalPosition();
        if (goalPosition == null) {
            System.err.println("Goal position is not set.");
            return null;
        }

        Cell head = getCells().getLast();
        List<BoardPosition> neighboringPositions = getBoard().getNeighboringPositions(head);

        // Filtrar para manter apenas as posições que não estão no caminho atual da cobra e em direção ao objetivo
        neighboringPositions.removeIf(position -> 
            getPath().contains(position) || 
            position.distanceTo(goalPosition) >= head.getPosition().distanceTo(goalPosition)
        );

        // Escolher a célula mais próxima do objetivo entre as posições filtradas
        Cell bestCell = null;
        double minDistance = Double.MAX_VALUE;
        for (BoardPosition pos : neighboringPositions) {
            Cell currentCell = getBoard().getCell(pos);
            if (!currentCell.isOcupied() && (bestCell == null || pos.distanceTo(goalPosition) < minDistance)) {
                minDistance = pos.distanceTo(goalPosition);
                bestCell = currentCell;
            }
        }

        return bestCell;
    }
}