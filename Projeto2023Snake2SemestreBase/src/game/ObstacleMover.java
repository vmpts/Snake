package game;

import java.util.concurrent.CyclicBarrier;

import environment.Board;

public class ObstacleMover extends Thread {
	private Obstacle obstacle ;
	private Board board ;
	
	public ObstacleMover(Obstacle o,Board board) {
		super();
		this.obstacle = o;
		this.board = board;
		
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(Obstacle.OBSTACLE_MOVE_INTERVAL);
			
			board.moveObstacle(obstacle);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
}
