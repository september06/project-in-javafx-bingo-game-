package application;

import java.security.SecureRandom;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Proclamation {
	private int maxPrss = 0, rNum, i, j, k, sum = 0;
	Button next = new Button("Next");//next button to generate the next number
	Button finish = new Button("Finish");//finish button to close the game window
	Label nextNum = new Label();// label that shows the last generated numbe
	private ArrayList<Integer> arr1 = new ArrayList<Integer>();//ArrayList to save the generated numbers 
	//constructor for the proclamation class
	public Proclamation() {
	
		//setting the text and the font of the label that shows the last generated number
		nextNum.setFont(Font.font("Tahoma", FontWeight.BLACK, 15));
		nextNum.setAlignment(Pos.CENTER);
		nextNum.setMaxSize(40, 25);
		nextNum.setMinSize(40, 25);
		nextNum.setBorder(new Border(
		new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		SecureRandom rand = new SecureRandom();
		//handling a press on the next button
		next.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (maxPrss == 99)//covering all the options
					next.setVisible(false);// the next button has no more option of numbers
				else
					rNum = rand.nextInt(100) + 1;// generate a new random number
				if (!chechDuplacits(rNum))
					while (!chechDuplacits(rNum))
						rNum = rand.nextInt(100) + 1;
				arr1.add(rNum);

				nextNum.setText("" + (getArr1().get(getArr1().size() - 1)));//shows the generated number on the label
				maxPrss++;//counting how many times the next button clicked in order to lock the button(making it invisible)

			}
		});

		Stage endGame = new Stage();//we want to open new stage
		VBox end = new VBox(); //do the stage in lines
		Label endText = new Label();
// handler to End the game after at finish
		finish.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Button exit = new Button("Exit");// exit from the game 
				exit.setOnAction(e -> System.exit(0));
				endGame.setTitle("Game Over");// set a title
				endGame.setMinHeight(350);
				endGame.setMinWidth(600);
				endText.setText("Game Over!!");
				endText.setAlignment(Pos.CENTER);
				endText.setAlignment(Pos.CENTER);
				end.setAlignment(Pos.CENTER);
				end.getChildren().addAll(endText, exit);// add the button and text
				Scene scene = new Scene(end);
				endGame.setScene(scene);
				endGame.show();

			}

		});

	}
// check the number if he exist in array list 
	public boolean chechDuplacits(int num) { 
		if (arr1.contains(num))
			return false;
		return true;
	}
	
// it return aboolean that check if the player is cheating or not in the rows if not return true
	public boolean checkRowWin(int arr[][], int arr2[][], int dim) {
		for (i = 1; i < dim; i++) {
			sum = 0;
			for (j = 0; j < dim - 1; j++) {
				sum = sum + arr[i][j];
			}
			if (sum == dim - 1) {
				for (k = 0; k < dim-1; k++)
				if (arr1.contains(arr2[i][k]));//check if we have right clicks
				else
				return false;
			return true;}
			
	
		}
		return false;
	}
	// this method  return aboolean that check if the player is cheating or not in the rows if not return true
	public boolean checkClouwWin(int arr[][],int arr2[][], int dim) {
		for (i = 0; i < dim-1; i++) {
			sum = 0;
			for (j = 1; j < dim; j++) {

				sum = sum + arr[j][i];

			}
			if (sum == dim - 1) {
				for (k = 1; k < dim - 1; k++)
					if (arr1.contains(arr2[k][i]))//check if we have right clicks
						;
					else
						return false;
				return true;
			}

		}
		return false;
	}
	// this method  return aboolean that check if the player is cheating or not in the primery diagonal if not return true
	public boolean checkMainDiagonal(int arr[][],int arr2[][], int dim) {
		sum = 0;
		for (i = 1; i < dim; i++) {
			for (j = 0; j < dim - 1; j++) {
				if (i==(j+1))
					sum = sum + arr[i][j];
			}
			
				if (sum == dim - 1) {
					for (k = 1; k < dim ; k++)
						for (j = 0; j < dim - 1; j++) {
							if (k==(j+1)) {
								if (arr1.contains(arr2[k][j]))//check if we have right clicks
									;
								else
									return false;
							return true;
						}}

					}
		}
					return false;
		}
	// this method  return aboolean that check if the player is cheating or not in the Secondary diagonal if not return true

	public boolean checkSecondaryDiagonal(int arr[][],int arr2[][], int dim) {
		sum = 0;
		for (i = dim-1; i > 0; i--) {
			for (j = 0; j < dim - 1; j++) {
				if (dim-1==(j+i))
					sum = sum + arr[i][j];
			}
			
				if (sum == dim - 1) {
					for (k = dim-1; k >0 ; k--)
						for (j = 0; j < dim - 1; j++) {
							if (dim-1==(j+k)) {
								if (arr1.contains(arr2[k][j])) //check if we have right clicks
									;
								else
									return false;
							return true;
						}}

					}
		}
					return false;
		}
	//getters and setters
	public Button getNext() {
		return next;
	}

	public void setNext(Button next) {
		this.next = next;
	}

	public Button getFinish() {
		return finish;
	}

	public void setFinish(Button finish) {
		this.finish = finish;
	}

	public Label getNextNum() {
		return nextNum;
	}

	public void setNextNum(Label nextNum) {
		this.nextNum = nextNum;
	}

	public ArrayList<Integer> getArr1() {
		return arr1;
	}

	public void setArr1(int in) {
		arr1.add(in);
	}

}
