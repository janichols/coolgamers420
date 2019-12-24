package model;

import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;
import view.FlappyView;
/**
 * This is the model. It contains the player
 * and pipes
 * @author Big Jimbo
 *
 */
public class GameModel extends Observable {
	
	private Pipe[] pipes;
	private Player player;
	
	/**
	 * Calls for all pipes to be drawn, off screen initially
	 */
	public GameModel() {
		initializePipes();
		
	}
	
	/**
	 * Sets the player
	 * @param player - the player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Allows view to be set and observer
	 * @param view - the observer
	 */
	public void setObserver(FlappyView view) {
		this.addObserver(view);
	}
	
	/**
	 * Sets up 3 pipe objects in an array, initialized off screen with the
	 * appropriate gap space. Off screen to allow for some kind of lead time
	 */
	public void initializePipes() {
		this.pipes = new Pipe[3];
		pipes[0] = new Pipe(FlappyConstants.SCREEN_WIDTH);
		pipes[1] = new Pipe(FlappyConstants.SCREEN_WIDTH + FlappyConstants.PIPE_GAP);
		pipes[2] = new Pipe(FlappyConstants.SCREEN_WIDTH + (2 *FlappyConstants.PIPE_GAP));
		
	}
	
	/**
	 * Checks if a pipe moves of the screen, and creates a new one
	 * 
	 * Then updates all their positions
	 * 
	 * Then notifies the view so it can redraw
	 * 
	 */
	public void updatePipes() {
		if (timeForNewPipe()) {
			for (int i = 0; i < pipes.length - 1; i++) {
				pipes[i] = pipes[i + 1];
			}
			pipes[pipes.length - 1] = new Pipe(pipes[pipes.length - 1].getXCoord() + FlappyConstants.PIPE_GAP);
		}
		for (int i = 0; i < pipes.length; i++) {
			pipes[i].move();
			
		}
		setChanged();
		notifyObservers();
		
	}
	
	/**
	 * Tells the player to move
	 */
	public void updatePlayer() {
		player.updatePosition();
	}
	
	/**
	 * I just gave them the ability to draw themselves given a graphics context.
	 * 
	 * Good style? I have no idea but its way easier IMO
	 * 
	 * @param graphicsContext - from the view
	 */
	public void drawPipes(GraphicsContext graphicsContext) {
		for (int i = 0; i < pipes.length; i++) {
			pipes[i].drawSelf(graphicsContext);
		}
	}
	/**
	 * Tells the player to draw itself
	 * @param graphicsContext
	 */
	public void drawPlayer(GraphicsContext graphicsContext) {
		player.drawSelf(graphicsContext);
	}
	
	/**
	 * Checks if the first pipe is off screen (would be drawing itself at -2*pipe width)
	 * @return true if the xcoord is -2*pipeWidth
	 */
	public boolean timeForNewPipe() {
		return pipes[0].getXCoord() < -(2 * FlappyConstants.PIPE_WIDTH);
	}
	
	/**
	 * moves the player up
	 */
	public void jump() {
		player.jump();
	}
	/**
	 * Returns the pipes
	 * @return the pipes
	 */
	public Pipe[] getPipes() {
		return pipes;
	}
	
	/**
	 * Checks for each pipe, if the player is colliding with
	 * @return true if collided
	 */
	public boolean collision() {
		for (int i = 0; i < pipes.length; i++) {
			if (didCollide(pipes[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determines if the player is colliding with a pipe
	 * @param pipe - pipe to check
	 * @return true if collided
	 */
	private boolean didCollide(Pipe pipe) {
		
		// get info about top and bottom pipes
		int pipeX = pipe.getXCoord();
		int topPipeY = pipe.getTopYCoord();
		int topPipeHeight = pipe.getTopHeight();
		int bottomPipeY = pipe.getBottomYCoord();
		int bottomPipeHeight = pipe.getBottomHeight();
		int pipeWidth = FlappyConstants.PIPE_WIDTH;
		int flappyWidth = FlappyConstants.FLAPPY_WIDTH;
		double flappyX = player.getXCoord();
		double flappyY = player.getYCoord();
		
		// did player collide with top pipe?
		if (flappyX < pipeX + pipeWidth &&
			flappyX + flappyWidth > pipeX &&
			flappyY < topPipeY + topPipeHeight &&
			flappyY + flappyWidth > topPipeY) {
			return true;
		}
		// did player collide with bottom pipe?
		if (flappyX < pipeX + pipeWidth &&
				flappyX + flappyWidth > pipeX &&
				flappyY < bottomPipeY + bottomPipeHeight &&
				flappyY + flappyWidth > bottomPipeY) {
				return true;
			}		
		
		return false;
	}
	
	/**
	 * When death happens, make new pipes, put them further out tho
	 * 
	 * reset the player stats.
	 */
	public void onDeath() {
		this.pipes = new Pipe[3];
		pipes[0] = new Pipe(2 * FlappyConstants.SCREEN_WIDTH);
		pipes[1] = new Pipe(2 * FlappyConstants.SCREEN_WIDTH + FlappyConstants.PIPE_GAP);
		pipes[2] = new Pipe(2 * FlappyConstants.SCREEN_WIDTH + (2 *FlappyConstants.PIPE_GAP));
		player.reset();
	}
	
	/**
	 * Determines if the player hit the ground
	 * @return true - you guessed it, true if the player hit the ground
	 */
	public boolean hitGround() {
		int flappyWidth = FlappyConstants.FLAPPY_WIDTH;
		double flappyY = player.getYCoord();
		if (flappyY + flappyWidth > FlappyConstants.GROUND_Y) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if the player score should be incremented
	 */
	public void updateScore() {
		if (passedPipe()) {
			player.addScore();
		}
	}
	
	/**
	 * Checks all pipes, skipping over already passed pipes.
	 * Determines if the players xcoordinate is past the 
	 * pipes xcoord + with, sets the pipe's passed field to
	 * true
	 * 
	 * @return true if it passed a newly pased pipe
	 */
	public boolean passedPipe() {
		for (int i = 0; i < pipes.length; i++) {
			if (pipes[i].wasPassed()) {
				break;
			}
			int pipeXCoord = pipes[i].getXCoord();
			double flappyXCoord = player.getXCoord();
			if (flappyXCoord > pipeXCoord + FlappyConstants.PIPE_WIDTH) {
				pipes[i].setPassed();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Unused currently, should be useful for AI stuff.
	 * Determines how far the player is from the pipe
	 * in front of it
	 * @return
	 */
	public double getDistanceToNextPipe() {
		int i = 0;
		while (pipes[i].wasPassed()) {
			i++;
		}
		Pipe pipe = pipes[i];
		int pipeXCoord = pipe.getXCoord();
		double flappyXCoord = player.getXCoord();
		return pipeXCoord - flappyXCoord;
	}
	
	/**
	 * Returns the distance to the center gap of the nearest pipes
	 * @return
	 */
	public double getDistanceToCenterGap() {
		int i = 0;
		while (pipes[i].wasPassed()) {
			i++;
		}
		Pipe pipe = pipes[i];
		// center gap - dead center y coord
		double middleGap = FlappyConstants.PIPE_GAP/2 + pipe.getTopHeight();
		double playerY = player.getYCoord() - FlappyConstants.FLAPPY_WIDTH/2;
		// this should be dead center. If playerY > pipeMiddleGap, the player is below (will be positive)
		// if playerY < pipeMiddleGap, player is above (negative value);
		return playerY - middleGap;
		
	}
	
	/**
	 * Returns num pipes passed
	 * @return num pipes passed
	 */
	public int getScore() {
		return player.getScore();
	}

}
