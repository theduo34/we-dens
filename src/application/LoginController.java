package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
	

	GetData getData = new GetData();
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
	}
	
	
	public void handlelLogin() {
		if(userFieldLogin.getText().isEmpty() || pwdFieldLogin.getText().isEmpty()) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Empty feilds");
			alert.setContentText("Please make sure you fill both the username and password feilds ");
			alert.showAndWait();
		}else {
			loginUser(userFieldLogin.getText(),pwdFieldLogin.getText());
		}
	}
	
	public void loginUser(String username,String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {           
			DButil connect = new DButil();
			Connection connection = connect.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet=preparedStatement.executeQuery() ;                                           //user credentials in existence 
				if(resultSet.next()) {
					
					try {
						
						System.out.println("logged in");
						Stage stageClose = (Stage)loginBtn.getScene().getWindow() ;
						stageClose.close();

						getData.userName = resultSet.getString("username");
						getData.accesstype = resultSet.getString("accounType");
						
						FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
						Scene scene = new Scene(fxmlLoader.load());
						Stage stage = new Stage();
						stage.setTitle("Home");
						stage.setScene(scene);
						stage.show();
						
						
					}catch(IOException  e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
				}else {                                                
					System.out.println("Incorrect credentials");
							Alert alert=new Alert(Alert.AlertType.ERROR);
							alert.setHeaderText("Sorry!Incorrect");
							alert.setContentText("Credentials provided are Incorrect");
							alert.show();
						
					}
        }catch(SQLException e) {
			e.printStackTrace();
		}
		
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
	
	
}
