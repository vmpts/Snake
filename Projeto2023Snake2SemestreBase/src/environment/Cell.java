package environment;


import game.GameElement;
import game.Goal;
import game.Killer;
import game.Obstacle;
import game.Snake;
import game.AutomaticSnake;

public class Cell{
	private BoardPosition position;
	private Snake ocuppyingSnake = null;
	private GameElement gameElement=null;

	public GameElement getGameElement() {
		return gameElement;
		
	}
	
	


	public Cell(BoardPosition position) {
		super();
		this.position = position;
	}

	public BoardPosition getPosition() {
		return position;
	}

	// request a cell to be occupied by Snake, If it is occupied by another Snake or Obstacle, wait.
	public synchronized void request(Snake snake)
			throws InterruptedException {
		while (isOcupied()) {
			wait();
		}
		ocuppyingSnake = snake;
	}

	public void release() 
		throws InterruptedException {
			while(!isOcupied()) {
				wait();
			}
			ocuppyingSnake=null;
			
		}
		
	

	public boolean isOcupiedBySnake() {
		return ocuppyingSnake!=null;
	}

	@Override
	public String toString() {
		return "" + position;
	}

	public void setGameElement(GameElement obstacle) {
		gameElement = obstacle;

	}

	
		public boolean isOcupied() {
		    if (isOcupiedBySnake() || (gameElement != null && gameElement instanceof Obstacle)) {
		        return true;
		    } else {
		    
		    return false;
		}
		}

	public Snake getOcuppyingSnake() {
		return ocuppyingSnake;
	}


	public Goal removeGoal() {
		if(isOcupiedByGoal()) {
		        Goal goal = (Goal) this.gameElement;
		        this.gameElement = null;
		        return goal;
		    }else {
		    return null;
		}
			
		}
		
	public void removeObstacle() {
		if (isOcupiedByObstacle()) {
	          gameElement = null;
	      }
	}


	public Goal getGoal() {
		return (Goal)gameElement;
	}


	public boolean isOcupiedByGoal() {
		return (gameElement!=null && gameElement instanceof Goal);
	}


	public boolean isOccupiedByKiller() {
		return (gameElement!=null && gameElement instanceof Killer);
	}


	public boolean isOcupiedByObstacle() {
		return (gameElement!=null && gameElement instanceof Obstacle);
	}


	public void removeSnake(Snake snake) {
		// TODO
	}



}
