package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutController  {
	String infoMessage="\"The Tale of Wedens - Your Ultimate Dentist App.\\n\"\r\n"
			+ "       		+ \"Once upon a time in the bustling world of technology and healthcare, there was an innovative app called \"\r\n"
			+ "       		+ \"\\\"Wedens.\\\" The name, a clever fusion of \\\"tooth\\\" in Latin (dens) and \\\"our\\\" in English, perfectly\"\r\n"
			+ "       		+ \" encapsulated the app's core mission - to bring the world of dentistry closer to you, making it a part \"\r\n"
			+ "       		+ \"of your daily life.\";";
	
	public String infoBox() {
		// TODO Auto-generated method stub
		Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle("About Info");
		alert.setHeaderText("The tale of wedens");
		alert.showAndWait();
		
		return infoMessage;

	}


}

