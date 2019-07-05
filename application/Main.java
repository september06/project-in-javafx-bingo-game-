package application;


import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// the main 
public class Main extends Application {

	@Override 
	public void start(Stage primaryStage) throws Exception { // the point we start from
		Parameters prom = getParameters();
		int dimension=0; 
		int players=0;//the number of players
		try { //check if insert arguments is legal
		List <String> stringList = prom .getRaw();//string for parm 
		dimension= (Integer.parseInt( stringList.get(0)));
		players=(Integer.parseInt( stringList.get(1)));
		if((players<2||12<players)||(dimension<5||dimension>7))
			throw new Exception(" Wrong Input , Please Enter Correct Arguments !!!!!");// mgs if the input wrong
		
	}
		catch(Exception e) { //exceptions msg in alert 
		Alert jumpA =new Alert(AlertType.ERROR);
		jumpA.setTitle("Exception"); 
		jumpA.setContentText("Something wrong ....\n <<The game building from  5 to 7 card dimension and 2 to 12 players.>>");
		jumpA.showAndWait();
		Alert jumpB=new Alert(AlertType.INFORMATION);
		jumpB.setContentText("We are going to default game setting for you " );
		jumpB.showAndWait();
		dimension=5;
		players=3;
			
		}
		Game builder = new Game(primaryStage,players,dimension);	
		Scene scene = new Scene(builder.Bordroot,1200,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bingo Game");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
		}
}