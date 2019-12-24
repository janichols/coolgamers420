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
	private File neutralFlappyBirdPath;
	private Image neutralFlappyBirdImage;
	private File upFlappyBirdPath;
	private File downFlappyBirdPath;
	private Image upFlappyBirdImage;
	private Image downFlappyBirdImage;

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
		initImage();

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
		if (downVelocity < 0) {
			graphicsContext.drawImage(upFlappyBirdImage, xCoord, yCoord);
		}
		else if (downVelocity > 0) {
			graphicsContext.drawImage(downFlappyBirdImage, xCoord, yCoord);
		}
		else {
			graphicsContext.drawImage(neutralFlappyBirdImage, xCoord, yCoord);
		}
		
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

	private void initImage() {
		neutralFlappyBirdPath = new File("resources/flappyBird.png");
		neutralFlappyBirdImage = new Image(neutralFlappyBirdPath.toURI().toString(), FlappyConstants.FLAPPY_WIDTH, FlappyConstants.FLAPPY_WIDTH, false, false);
		upFlappyBirdPath = new File("resources/flappyBirdUp.png");
		upFlappyBirdImage = new Image(upFlappyBirdPath.toURI().toString(), FlappyConstants.FLAPPY_WIDTH, FlappyConstants.FLAPPY_WIDTH, false, false);
		downFlappyBirdPath = new File("resources/flappyBirdDown.png");
		downFlappyBirdImage = new Image(downFlappyBirdPath.toURI().toString(), FlappyConstants.FLAPPY_WIDTH, FlappyConstants.FLAPPY_WIDTH, false, false);
	}

}
