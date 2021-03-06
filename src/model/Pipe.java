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
	private File topPipePath;
	private File bottomPipePath;
	private Image topPipeImage;
	private Image bottomPipeImage;

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
		initImages();
	}
	
	private void initImages() {
		topPipePath = new File("resources/flappyPipetop.png");
		bottomPipePath = new File("resources/flappyPipeBottom.png");
		this.topPipeImage = new Image(topPipePath.toURI().toString(), FlappyConstants.PIPE_WIDTH, topHeight, false, false);
		this.bottomPipeImage = new Image(bottomPipePath.toURI().toString(), FlappyConstants.PIPE_WIDTH, bottomHeight, false, false);
	}

	/**
	 * U no
	 */
	@Override
	public void drawSelf(GraphicsContext graphicsContext) {
		graphicsContext.setFill(color);
		//graphicsContext.fillRect(xCoord, topYCoord, width, topHeight);
		graphicsContext.drawImage(topPipeImage, xCoord, topYCoord);
		//graphicsContext.fillRect(xCoord, bottomYCoord, width, bottomHeight);
		graphicsContext.drawImage(bottomPipeImage, xCoord, bottomYCoord);
		
		
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
