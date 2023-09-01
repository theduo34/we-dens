package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
	
	Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		loginBtn.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				DButil.loginUser(event,userFieldLogin.getText(),pwdFieldLogin.getText());
			}
		});
	}
	
	
	public void moveToSignUp() {
		try {
			System.out.println("logged in");
			Stage stage = (Stage)signUpBtn.getScene().getWindow() ;
			stage.close();
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewUser.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stagenew = new Stage();
			stagenew.setTitle("Sign-Up!");
			stagenew.setScene(scene);
			stagenew.show();
		}catch(IOException  e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	public void moveHomePage() {
		try {
			System.out.println("logged in");
			Stage stage = (Stage)signUpBtn.getScene().getWindow() ;
			stage.close();
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stagenew = new Stage();
			stagenew.setTitle("Welcome!");
			stagenew.setScene(scene);
			stagenew.show();
		}catch(IOException  e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
