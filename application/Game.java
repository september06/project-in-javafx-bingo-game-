package application;

import java.security.SecureRandom;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Game {
	public int i, j;
	BorderPane Bordroot = new BorderPane();//using borderPane in order to divide the stage to areas(top,left,center,right)
	
	Stage stage;
	//constructor for the class Game that receive the primary stage, the number of the players and the size of the game board
	public Game(Stage stage, int Players, int Dim) {
		Proclamation proc = new Proclamation();
		this.stage = stage;
		// using Hbox in order to divide the top area in the borderPane to horizontal lines
		HBox hb = new HBox(30, proc.getNext(), proc.getNextNum(), proc.getFinish());

		hb.setAlignment(Pos.CENTER);
		Bordroot.setTop(hb);
		// threads array for the players, each player plays the game in an individual thread
		Thread[] threads = new Thread[Players];
		// using FlowPane in order to order all the grids(the game cards, for each player)
		FlowPane matrix = new FlowPane();
		Bordroot.setCenter(matrix);
		for (i = 0; i < Players; i++) {
			PlayerCard p = new PlayerCard(Dim + 1, i + 1, proc);
			threads[i] = new Thread(p);// creating a new thread
			this.threadJoin(threads[i]);
			threads[i].start();
			matrix.getChildren().add(p.grid);// add the grid to our FlowPane
			matrix.setHgap(30);
			matrix.setVgap(30);
			matrix.setAlignment(Pos.TOP_CENTER);
		}
		matrix.setPrefWrapLength(1800);
		matrix.setPadding(new Insets(50, 50, 50, 50));

	}
	// this method makes the main thread waiting for all the other thread, and finish the last one
	public void threadJoin(Thread t) {
		try {
			t.join(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	//constructor
	public Game() {
	};
}
