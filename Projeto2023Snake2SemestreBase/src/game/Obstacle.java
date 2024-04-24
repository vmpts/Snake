package game;

import environment.Board;
import environment.BoardPosition;
import environment.LocalBoard;

public class Obstacle extends GameElement {
	
	
	private static final int NUM_MOVES=3;
	public static final int OBSTACLE_MOVE_INTERVAL = 600;
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
	public BoardPosition getPos(BoardPosition pos) {
		return pos;
	}
	public void setPos(BoardPosition pos) {
		this.pos = pos;
	}

	public static int getNumMoves() {
		return NUM_MOVES;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public BoardPosition getPos() {
		return pos;
	}

	public void setRemainingMoves(int remainingMoves) {
		this.remainingMoves = remainingMoves;
	}

}
