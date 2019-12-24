package model;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
	
	/**
	 * I just figured it would be easier if we let objects draw themselves on to the canvas
	 * @param graphicsContext
	 */
	public void drawSelf(GraphicsContext graphicsContext);

}
