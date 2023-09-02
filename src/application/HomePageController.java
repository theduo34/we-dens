package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import lib.DButil;

public class HomePageController implements Initializable{
	/*
	 * fxml injection of the nodes id's
	 */
	@FXML
	private Button homeBtn , aboutBtn,contactBtn,accountBtn;

	@FXML
	private Button seeMoreBtn,bookNowBtn,contactUsBtn;
	;
	@FXML
	private Button logoutBtn;

	@FXML
	private Label welcomeLabel;





	@Override
	public void initialize(URL location, ResourceBundle resource) {
		aboutBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("About.fxml"));
					Scene scene = new Scene(fxmlLoader.load());
					Stage stagenew = new Stage();
					stagenew.setTitle("THE TALE OF WE`DENS!");
					stagenew.setScene(scene);
					stagenew.showAndWait();
				}catch(IOException  e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					}
				}
			});

		

		contactBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// Display the contact information when the button is clicked
					Label contactInfoLabel = new Label();
					String contactInfo = "Support Team Contacts:\n\n"
							+ " Email: support@example.com\n"
							+ " Phone: +233 20-862-4003\n"
							+ " Office: 123 Main, Franko Estate";
					contactInfoLabel.setTextAlignment(TextAlignment.CENTER);
					contactInfoLabel.setFont(Font.font("Sans Serif",FontWeight.BOLD,30));
					contactInfoLabel.setText(contactInfo);
					contactInfoLabel.setTextFill(Color.WHITE);
					contactInfoLabel.setBackground(new Background(new BackgroundFill(Color.VIOLET,null,null)));
					Stage stage=new Stage();
					StackPane root =new StackPane();
					Scene scene=new Scene(root,500,500);

					root.getChildren().add(contactInfoLabel);
					stage.setTitle("Contact Info!");
					stage.setFullScreen(false);
					stage.setScene(scene);
					stage.showAndWait();
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}    
			}
		});

	}

	/*
	 * method to set welcome message and a username
	 */
	public void setUserInformation(String username) {
		welcomeLabel.setText("Welcome," + username + "!");

	}
}