package application;

import java.io.IOException;
import java.net.URL;
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

	@Override
	public void initialize(URL location, ResourceBundle resoruce) {
		/*
		 * Adding of options to the gender field
		 * 
		 */
		ToggleGroup toggleGroup=new ToggleGroup();
		male.setToggleGroup(toggleGroup);
		female.setToggleGroup(toggleGroup);
		other.setToggleGroup(toggleGroup);
		
		male.setSelected(true);
		
		RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
		
		
		signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!userField.getText().trim().isEmpty() && pwdField.getText().trim().isEmpty() 
						&&emailField.getText().trim().isEmpty()) {
					DButil.signUp(event,userField.getText(),emailField.getText(),pwdField.getText(),phoneField.getText(),selectedRadioButton.getText());
					infoBox("Successfully signed up!","Success",null);
				}else {
					System.out.println("Please fill in all the field;");
					showAlert(Alert.AlertType.ERROR,"Fill in all the fields","Forms Errro!");
				}
			}
		});
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

