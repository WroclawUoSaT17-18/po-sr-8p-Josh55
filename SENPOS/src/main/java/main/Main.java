package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uiController.MainController;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/MainScreen.fxml"));
		VBox vbox = loader.load();
		
		MainController controller = loader.getController();
		
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("SENPOS rev 0.4");
		primaryStage.show();
	}

}
