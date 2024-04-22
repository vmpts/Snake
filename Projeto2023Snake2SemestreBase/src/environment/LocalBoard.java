package environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.GameElement;
import game.Goal;
import game.Obstacle;
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
		int x = 0;
		do {
			AutomaticSnake as = new AutomaticSnake(x,this);
			snakes.add(as);
		} while (x<NUM_SNAKES);
		}
	

	// synchronization in cell
	
	public void init() {
		for(Snake s2: snakes) {
			s2.start();
			setChanged();
		}
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
	public void handleKeyRelease() {
		// do nothing... No keys relevant in local game
	}



}
