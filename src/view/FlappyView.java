package view;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Pipe;
import model.Player;
import model.FlappyConstants;
import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Set up model, calling a tick to update positions.
 * Move all pipes towards model who drops
 * 
 * @author lolwu
 *
 */
public class FlappyView extends Application implements Observer{
	
	private Stage stage;
	private BorderPane borderPane;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext graphicsContext;
	private AnimationTimer timer;
	private GameController controller;
	private static final int ticksPerFrame = 1;
	
	private Player player;
	
	/**
	 * make the scene
	 * draw the background
	 * set up MVC
	 * 
	 * set up event handling and animation timers
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		setScene();
		drawBackground();
		this.controller = new GameController();
		controller.setObserver(this);
		this.player = new Player();
		controller.setPlayer(player);
		updateScore();
		setEventHandlers();
		timer.start();
		stage.show();

	}
	
	/**
	 * Sets up the basic scene with a borderpane and canvas
	 */
	private void setScene() {
		BorderPane borderPane = new BorderPane();
		this.borderPane = borderPane;
		Canvas canvas = new Canvas(FlappyConstants.SCREEN_WIDTH, FlappyConstants.SCREEN_HEIGHT);
		this.canvas = canvas;
		this.graphicsContext = canvas.getGraphicsContext2D();
		borderPane.getChildren().add(canvas);
		Scene mainScene = new Scene(borderPane, FlappyConstants.SCREEN_WIDTH, FlappyConstants.SCREEN_HEIGHT);
		this.scene = mainScene;
		
		stage.setScene(mainScene);
	}
	
	/**
	 * Sets up the ticktimer and click event handler
	 */
	
	private void setEventHandlers() {
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent keyPress) {
				controller.playerJump();
			}
		});
		timer = new AnimationTimer() {
			 @Override
	            public void handle(long now) {
				 	for (int i = 0; i < ticksPerFrame; i++) {
				 		tick();
				 	}
				 
				 
			 }
		};
	}
	
	/**
	 * Checks for collision or death
	 * 
	 * Updates the score
	 * 
	 * Then pipe and player positions
	 */
	private void tick() {
		if (controller.collision() || controller.hitGround()) {
 			controller.onDeath();
 		}
		controller.updateScore();
 		controller.updatePipes();
 		controller.updatePlayer();
	}
	/**
	 * Clears the graphics context, draws the green background and tan foreground
	 */
	private void drawBackground() {
		graphicsContext.clearRect(0, 0,FlappyConstants.SCREEN_WIDTH, FlappyConstants.SCREEN_HEIGHT);
		graphicsContext.setFill(Color.GREEN);
		graphicsContext.fillRect(0, 0,FlappyConstants.SCREEN_WIDTH, FlappyConstants.SCREEN_HEIGHT);
		graphicsContext.setFill(Color.TAN);
		graphicsContext.fillRect(0, FlappyConstants.GROUND_Y, FlappyConstants.SCREEN_WIDTH, FlappyConstants.SCREEN_HEIGHT);
		
	}

	/**
	 * Displays the score.
	 */
	private void updateScore() {
		int score = controller.getScore();
		Font copper = Font.font("Copperplate Gothic Bold", 16);
		graphicsContext.setFont(copper);
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillText(String.valueOf(score), 15, 45);
		
	}
	

	/**
	 * Just redraws all the shit
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		drawBackground();
		controller.drawPipes(graphicsContext);
		controller.drawPlayer(graphicsContext);
		updateScore();

		
	}
	

}
