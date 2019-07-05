package application;

import java.security.SecureRandom;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PlayerCard extends Thread {  // we extends from threads use
	private int i, j, a, b, n;
	private boolean c, l, r, f;
	private final int id;
	private String ch;
	GridPane grid = new GridPane(); // new  grids
	Button bingoB = new Button("Bingo");
	private int[][] bing;
	private int[][] matD;
	Proclamation proc;
	Game game = new Game();
	//constructor for the class PlayerCard that receive the primary stage, the number of the players and the size of the game board
	public PlayerCard(int n, int id, Proclamation proc) {
		this.proc = proc;
		this.id = id;
		this.n = n;
		int[][] bing = new int[n][n]; // matrix to save the number in the button.
		int[][] matD = new int[n][n];// matrix to save the click or not 1/0.
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				bing[i][j] = 0;
			}
		}
		GridPane grid = new GridPane();
		SecureRandom rand = new SecureRandom();// we get an random number
		bingoB.setMinSize(60, 35);
		bingoB.setMaxSize(60, 35);
		grid.add(bingoB, (n / 2) - 1, 0);
// doing the button in the gridpane and add them 
		for (i = 0; i < n - 1; i++) {
			for (j = 1; j < n; j++) {
				Button buton = new Button("" + (rand.nextInt(100) + 1));
				buton.setMinSize(60, 35);
				buton.setMaxSize(60, 35);
				buton.setOnMouseClicked(new EventHandler<MouseEvent>() {// we set handler for each button
					@Override
					public void handle(MouseEvent event) {
						
						if ((buton.getTextFill().toString()).equals(Color.RED.toString())) {// get the color of text if it is reed
							buton.setTextFill(Paint.valueOf("BLACK"));
							a = GridPane.getColumnIndex(buton);// get column in grid
							b = GridPane.getRowIndex(buton);// get column in grid
							bing[b][a] = 0;
							setBring(bing);
							matD[b][a] = 0;
							setMatD(matD);
						
						}

						else {
							buton.setTextFill(Paint.valueOf("RED"));// if the color is black and we dont press at yet
							ch = buton.getText().toString();
							a = GridPane.getColumnIndex(buton);
							b = GridPane.getRowIndex(buton);
							bing[b][a] = Integer.parseInt(ch);
							matD[b][a] = 1;
							setMatD(matD);
							setBring(bing);
						
						}
					}

				});
				grid.add(buton, i, j); // add the button to the grid
				a = GridPane.getColumnIndex(bingoB);
				b = GridPane.getRowIndex(bingoB);
			
			}
		}
		setGrid(grid);

	}
//getters and setters
	public Button getBingoB() {
		return bingoB;
	}

	public void setBingoB(Button bingoB) {
		this.bingoB = bingoB;
	}

	public GridPane getGrid() {
		return grid;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	public int[][] getMatD() {
		return matD;
	}

	public void setMatD(int[][] matD) {
		this.matD = matD;
	}

	public long getId() {
		return id;
	}

	public int[][] getBring() {
		return bing;
	}

	public void setBring(int[][] bing) {
		this.bing = bing;
	}
// mothud ran that help us to use the threads
	public void run() {

		getBingoB().setOnMouseClicked(new EventHandler<MouseEvent>() { // handler for binngo button
			@Override
			public void handle(MouseEvent event) {

				System.out.println("Player Number " + id + " Press Bingo");
				
				r = proc.checkRowWin(getMatD(), getBring(), n);
				f=proc.checkSecondaryDiagonal( getMatD(), getBring(), n);
				c = proc.checkClouwWin(getMatD(), getBring(), n);
				l = proc.checkMainDiagonal(getMatD(), getBring(), n);
			
				if (r||c||f||l)
				{
					Alert win =new Alert(AlertType.INFORMATION);// alert if we get winner 
					win.setTitle("The winner");
					win.setContentText("The winner is player "+ getId());
					win.showAndWait();
					System.out.println("the winner is player "+ getId());
					bingoB.setVisible(false);
					Alert fin =new Alert(AlertType.INFORMATION);// alert if we get winner 
					fin.setTitle("End Game");
					fin.setContentText("The game end in order to finish press <Finish> button  ");
					fin.show();
					
						
				}
				else {
				Alert rong =new Alert(AlertType.WARNING);//alert if we get cheter
				rong.setTitle("Cheater");
				rong.setContentText("Player "+ getId()+" is cheating! \n He kicked out of the Game!");
				rong.showAndWait();
				bingoB.setVisible(false);
				System.out.println("Player"+ getId()+" is cheating! \n He kicked out of the Game!");
				}
				
			}
		});

	}

}