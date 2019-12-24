package model;


import java.io.File;
import java.util.Random;
import model.FlappyConstants;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Pipe implements Drawable{
	private int topHeight;
	private int bottomHeight;
	private int xCoord;
	private int topYCoord;
	private int bottomYCoord;
	private int width = FlappyConstants.PIPE_WIDTH;
	private Color color = Color.YELLOW;
	private boolean wasPassed;

	/**
	 * Needs to know where to start the pipes
	 * @param xCoord - yeet
	 */
	public Pipe(int xCoord) {
		Random random = new Random();
		int topSize = random.nextInt(FlappyConstants.MAX_TOP_PIPE_LENGTH) + FlappyConstants.MIN_TOP_PIPE_LENGTH;
		this.topHeight = topSize * FlappyConstants.FLAPPY_WIDTH;
		this.xCoord = xCoord;
		this.topYCoord = 0;
		this.bottomYCoord = FlappyConstants.PIPE_SPACING + topHeight;
		this.bottomHeight = FlappyConstants.SCREEN_HEIGHT - bottomYCoord - FlappyConstants.GROUND_HEIGHT;
		this.wasPassed = false;
	}

	/**
	 * U no
	 */
	@Override
	public void drawSelf(GraphicsContext graphicsContext) {
		graphicsContext.setFill(color);
		graphicsContext.fillRect(xCoord, topYCoord, width, topHeight);
		graphicsContext.fillRect(xCoord, bottomYCoord, width, bottomHeight);
		
		
	}
	
	/**
	 * Getters, setters
	 */
	public void move() {
		this.xCoord -= FlappyConstants.PIPE_MOVEMENT;
	}
	
	public int getXCoord() {
		return this.xCoord;
	}
	
	public int getTopHeight() {
		return this.topHeight;
	}
	
	public int getBottomHeight() {
		return this.bottomHeight;
	}
	
	public int getTopYCoord() {
		return this.topYCoord;
	}
	public int getBottomYCoord() {
		return this.bottomYCoord;
	}
	
	public boolean wasPassed() {
		return wasPassed;
	}
	
	public void setPassed() {
		wasPassed = true;
	}
}
