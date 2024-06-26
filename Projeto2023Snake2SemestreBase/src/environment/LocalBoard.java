package environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.GameElement;
import game.Goal;
import game.Killer;
import game.Obstacle;
import game.ObstacleMover;
import game.Snake;
import server.Server;
import game.AutomaticSnake;

public class LocalBoard extends Board{
	
	private static final int NUM_SNAKES = 6;
	private static final int NUM_OBSTACLES = 25;
	private static final int NUM_SIMULTANEOUS_MOVING_OBSTACLES = 3;
	

	public LocalBoard() {	
		 addObstacles(NUM_OBSTACLES);
		 addGoal();
		
		for (int i = 0; i < NUM_SNAKES; i++) {
			AutomaticSnake as = new AutomaticSnake(i,this);
			System.out.println("cobras" + i);
			snakes.add(as);
		} 
		}
	
	
	
	public void init() {
		for(Snake s: snakes) {
			s.start();
			System.out.println(s.getId());
			
			
		}
		ExecutorService threadPool = Executors.newFixedThreadPool(NUM_SIMULTANEOUS_MOVING_OBSTACLES);
		
		for (Obstacle o : getObstacles()) {
			ObstacleMover obstacleMover = new ObstacleMover(o,this);
			threadPool.submit(obstacleMover);
			
		}

		setChanged();
	}
	
	
					
	public void removeSnake(BoardPosition position) {
//		TODO
	}
	



	// Ignore these methods: only for remote players, which are not present in this project
	@Override
	public void handleKeyPress(int keyCode) {
		// do nothing... No keys relevant in local game
	}

	@Override
	public void handleKeyRelease(){
		// do nothing... No keys relevant in local game
	}




	
}

