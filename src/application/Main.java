package application;


import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Path;
import javafx.fxml.FXMLLoader;


public class Main extends Application  {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Log in!");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
