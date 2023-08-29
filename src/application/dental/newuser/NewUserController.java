package application.dental.newuser;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
	private ComboBox<String> regionSelection;

	@FXML
	private ComboBox<String> genderSelection;

	@FXML
	private Button loginBtn,signUpBtn;

	@Override
	public void initialize(URL location, ResourceBundle resoruce) {
		/*
		 * Adding of options to the gender field
		 */
		ObservableList<String> genderOptions = FXCollections.observableArrayList("Female", "Male");
		genderSelection.setItems(genderOptions);
		genderSelection.getSelectionModel().getSelectedItem();
		genderSelection.getSelectionModel().selectFirst();

		/*
		 * Adding of option to the region field
		 */
		ObservableList<String> regionOptions = FXCollections.observableArrayList("Eastern", "Ashanti",
				"Greater-Accra","North-East","Savannah","Western","Western-North","Central","Bono","Upper-East",
				"Upper-West","Volta","Ahafo","Oti","Bono-East");
		regionSelection.setItems(regionOptions);
		regionSelection.getSelectionModel().getSelectedItem();
		regionSelection.getSelectionModel().selectFirst();


		signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!userField.getText().trim().isEmpty() && pwdField.getText().trim().isEmpty() 
						&&emailField.getText().trim().isEmpty()) {
					DButil.signUp(event,userField.getText(),emailField.getText(),pwdField.getText(),regionOptions,genderOptions);
				}else {
					System.out.println("Please fill in all the field;");
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please fill in all the information to sign up");
					alert.show();
				}
			}
		});;


		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DButil.changeScene(event,"Login.fxml","Log in!",null);

			}
		});


	}





}
