package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import lib.DButil;

public class NewUserController implements Initializable {

	/*
	 * fxml injection of the nodes id's
	 */
	@FXML
	private TextField userField;

	@FXML
	private PasswordField pwdField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField phoneField;

	@FXML
	private RadioButton male, female,other;


	@FXML
	private Button signUpBtn,loginBtn;
	String selectgender;

	@Override
	public void initialize(URL location, ResourceBundle resoruce) {
		ToggleGroup gender = new ToggleGroup();
		male.setToggleGroup(gender);
		female.setToggleGroup(gender);
		other.setToggleGroup(gender);
		gender.selectedToggleProperty().addListener((observable, oldValue,newValue)->{
			if(newValue != null) {
				RadioButton selectedgender = (RadioButton) newValue;

				selectgender = selectedgender.getText();
			}else {

				showAlert(Alert.AlertType.ERROR,"Forms Errro!","Fill in all the fields");
			}
		});
	}




	public void handleSignup() {
		if(userField.getText().trim().isEmpty() || pwdField.getText().trim().isEmpty() 
				|| emailField.getText().trim().isEmpty()) {

			System.out.println("Please fill in all the field;");
			showAlert(Alert.AlertType.ERROR,"Forms Errro!","Fill in all the fields");

		}else {	

			signUp(userField.getText(),emailField.getText(),pwdField.getText(),phoneField.getText(),selectgender);

		}
	}





	public void signUp(String username,String email,String password,String telephone,String gender) {
		String sql = "INSERT INTO users(username,email,password,telephone,gender,accounType) VALUE(?,?,?,?,?,?)";

		DButil connect = new DButil();
		Connection connection = connect.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;

		try {                                               
			preparedStatement=connection.prepareStatement("SELECT * FROM users WHERE email=?");
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery() ;            
			if(resultSet.next()) {
				System.out.println("User Already Exist!");
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Entered username has been taken already");
				alert.show();
			}
			else {                                            //username not in existence
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, email);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, telephone);
				preparedStatement.setString(5, gender);
				preparedStatement.setString(6, "user");
				int ret =preparedStatement.executeUpdate();
				if(ret > 0) {
					infoBox("YOu have registered succesfully","Register","Register");
					moveToLogin();
				}else {
					showAlert(Alert.AlertType.ERROR,"ERROR MESSAGE","An error occured");
				}


			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

	}




	public void moveToLogin() {
		try {
			System.out.println("Signed in");
			Stage stage = (Stage)signUpBtn.getScene().getWindow() ;
			stage.close();
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stagenew = new Stage();
			stagenew.setTitle("Login!");
			stagenew.setScene(scene);
			stagenew.show();
		}catch(IOException  e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/*show alert method to display outcome of the form
	 *when sign-in button is clicked 
	 */
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		// TODO Auto-generated method stub
		Alert alert=new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
	/*Info to display to after the sign-in button
	 * clicked and it outcome depends on  the results
	 */
	private void infoBox(String infoMessage,String headerInfo,String title) {
		// TODO Auto-generated method stub
		Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerInfo);
		alert.showAndWait();

	}
}

