package main;

import javafx.application.Application;
import view.FlappyView;

public class PlayFlappy {
	public static void main(String[] args) {
		FlappyView view = new FlappyView();
		Application.launch(view.getClass(), args);
	}
}
