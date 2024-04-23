package game;

import java.util.concurrent.CyclicBarrier;

import environment.Board;

public class ObstacleMover extends Thread {
	private Obstacle obstacle ;
	private Board board ;
	private CyclicBarrier cB;
	public ObstacleMover(Board board, Obstacle obs, CyclicBarrier cB) {
		super();
		this.obstacle = obs;
		this.board = board;
		this.cB = cB;
	}
	public void setBarrier(CyclicBarrier cB2) {
		this.cB=cB2;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(Obstacle.OBSTACLE_MOVE_INTERVAL);
			setBarrier(cB);
			board.moveObstacle(obstacle);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
}
