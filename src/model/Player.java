package model;
import model.FlappyConstants;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player {
	private double xCoord;
	private double yCoord;
	private double downVelocity;
	private double downAcceleration;
	private int maxDownVelocity;
	private int jumpAmount;
	private int score;


	/**
	 * Init values
	 */
	public Player() {
		this.xCoord = FlappyConstants.FLAPPY_X;
		this.yCoord = FlappyConstants.FLAPPY_Y;
		this.downVelocity = 0;
		this.downAcceleration = FlappyConstants.GRAVITY;
		this.maxDownVelocity = FlappyConstants.TERMINAL_VELOCITY;
		this.jumpAmount = FlappyConstants.JUMP_HEIGHT;
		this.score = 0;

	}
	
	/**
	 * Called on mouse click.
	 * -10 is an arbitrary number
	 */
	public void jump() {
		yCoord -= jumpAmount;
		downVelocity = -10;
	}
	
	/**
	 * Updates the yCoord
	 */
	public void updatePosition() {
		downVelocity += downAcceleration;
		if (downVelocity > maxDownVelocity) {
			downVelocity = maxDownVelocity;
		}
		yCoord += downVelocity;
	}
	
	/**
	 * yeet
	 * @param graphicsContext
	 */
	public void drawSelf(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.YELLOW);
		graphicsContext.fillRect(xCoord, yCoord, FlappyConstants.FLAPPY_WIDTH, FlappyConstants.FLAPPY_WIDTH);
		
	}
	
	/**
	 * yup
	 * @return
	 */
	public double getXCoord() {
		return xCoord;
	}
	
	/**
	 * ok
	 * @return
	 */
	public double getYCoord() {
		return yCoord;
	}
	
	/**
	 * Reset, make new again
	 */
	public void reset() {
		this.xCoord = FlappyConstants.FLAPPY_X;
		this.yCoord = FlappyConstants.FLAPPY_Y;
		this.downVelocity = 0;
		this.downAcceleration = FlappyConstants.GRAVITY;
		this.maxDownVelocity = FlappyConstants.TERMINAL_VELOCITY;
		this.jumpAmount = FlappyConstants.JUMP_HEIGHT;
		this.score = 0;
	}
	/**
	 * :)
	 */
	public void addScore() {
		score++;
	}
	/**
	 * ;)
	 * @return
	 */
	public int getScore() {
		return score;
	}


}
