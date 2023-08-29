package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lib.DButil;

public class LoginController implements Initializable {

	/*
	 * fxml injection of the nodes id's
	 */
	@FXML
	private TextField userFieldLogin;

	@FXML
	private PasswordField pwdFieldLogin;

	@FXML
	private Button signUpBtn;

	@FXML
	private Button loginBtn;

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		loginBtn.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				DButil.loginUser(event,userFieldLogin.getText(),pwdFieldLogin.getText());
			}
		});
		

		signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DButil.changeScene(event,"NewUser.fxml","Sign Up!",null);

			}

		});

	}
}
