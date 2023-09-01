package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DButil.changeScene(event,"login.fxml", "Log in!", null);

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
