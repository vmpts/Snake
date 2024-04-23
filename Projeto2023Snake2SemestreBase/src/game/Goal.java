package game;

import environment.Board;

import environment.BoardPosition;
import environment.LocalBoard;

public class Goal extends GameElement  {
	private int value=1;
	private Board board;
	public static final int MAX_VALUE=10;
	public Goal( Board board2) {
		this.board = board2;
	}
	public int getValue() {
		return value;
	}
	public void incrementValue() throws InterruptedException {
		value++;
	}

	public int getGoalValue() {
		return value;
	}
	public  void captureGoal(GameElement gameElement)
			throws InterruptedException {

		
		boolean isGoalPlaced = false;
			while (!isGoalPlaced) {
				int randomX = (int) (Math.random() * Board.WIDTH);
		        int randomY = (int) (Math.random() * Board.HEIGHT);
		        BoardPosition bp = new BoardPosition(randomX, randomY);

			if (!board.getCell(bp).isOcupied()&& !board.getCell(bp).isOcupiedBySnake()) {
				BoardPosition posição = board.getGoalPosition();
				board.getCell(posição).removeGoal();
			
				board.getCell(bp).setGameElement(gameElement);
				board.setGoalPosition(bp);
				isGoalPlaced = true;
			}
		}
			incrementValue();
		board.setChanged();
	}
}

