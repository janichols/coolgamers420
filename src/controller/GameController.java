package controller;

import javafx.scene.canvas.GraphicsContext;
import model.GameModel;
import model.Player;
import view.FlappyView;

/**
 * I mean these are all basically just commands for the models.
 * 
 * Glorified getters and setters
 * @author lolwu
 *
 */

public class GameController {
	
	private GameModel model;
	
	public GameController() {
		this.model = new GameModel();
	}
	
	public void updatePipes() {
		model.updatePipes();
	}
	
	public void drawPipes(GraphicsContext graphicsContext) {
		model.drawPipes(graphicsContext);
	}
	
	public void setObserver(FlappyView view) {
		model.setObserver(view);
	}
	
	public void setPlayer(Player player) {
		model.setPlayer(player);
	}
	
	public void updatePlayer() {
		model.updatePlayer();
	}
	
	public void drawPlayer(GraphicsContext graphicsContext) {
		model.drawPlayer(graphicsContext);
	}
	
	public void playerJump() {
		model.jump();
	}
	
	public void onDeath() {
		model.onDeath();
	}
	
	public boolean collision() {
		return model.collision();
	}
	
	public boolean hitGround() {
		return model.hitGround();
	}
	
	public void updateScore() {
		model.updateScore();
	}
	
	public int getScore() {
		return model.getScore();
	}

}
