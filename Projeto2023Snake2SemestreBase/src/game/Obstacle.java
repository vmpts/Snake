package game;

import environment.Board;
import environment.BoardPosition;
import environment.LocalBoard;

public class Obstacle extends GameElement {
	
	
	private static final int NUM_MOVES=3;
	public static final int OBSTACLE_MOVE_INTERVAL = 400;
	private int remainingMoves=NUM_MOVES;
	private Board board;
	private BoardPosition pos;
	public Obstacle(Board board2) {
		super();
		this.board = board2;
	}
	
	public int getRemainingMoves() {
		return remainingMoves;
	}
	public void decrementRemainingMoves() {
		remainingMoves--;
	}
	public void setPos(BoardPosition pos) {
		this.pos = pos;
	}

}
