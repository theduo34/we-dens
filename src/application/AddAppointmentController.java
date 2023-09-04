package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lib.DButil;

public class AddAppointmentController implements Initializable {
	
	@FXML
    private Button backHomeBtn;

    @FXML
    private Button bookingBack;

    @FXML
    private Button bookingBook;

    @FXML
    private TextField bookingEmail;

    @FXML
    private AnchorPane bookingForm;

    @FXML
    private TextField bookingFirstName;

    @FXML
    private TextField bookingLastName;

    @FXML
    private TextArea bookingLocation;

    @FXML
    private TextField bookingPhoneNumber;
    @FXML
    private DatePicker bookingDate;

    @FXML
    private AnchorPane clinicForm;

    @FXML
    private Button clinicOneBtn;

    @FXML
    private Label clinicOneName;

    @FXML
    private Label bookingClinicName;
    
    @FXML
    private Button clinicTwoBtn;

    @FXML
    private Label clinicTwoName;



    GetData getData = new GetData();
    
    @Override
	public void initialize(URL location, ResourceBundle resource) {
    	bookingForm.setVisible(false);
		clinicForm.setVisible(true);
		
	}
	
	
	
	
	public void openForOne() {
		getData.clinicName = clinicOneName.getText();
		bookingForm.setVisible(true);
		clinicForm.setVisible(false);
		bookingClinicName.setText(clinicOneName.getText());
		
	}
	
	public void openForTwo() {
		getData.clinicName = clinicTwoName.getText();
		bookingForm.setVisible(true);
		clinicForm.setVisible(false);
		bookingClinicName.setText(clinicTwoName.getText());
	}
	
	public void book() {
		String sql = "INSERT INTO booking(firstName, lastName, date, phoneNumber, location, email, clinic, status) VALUE(?,?,?,?,?,?,?,?)";
		
		DButil connect = new DButil();
		Connection connection = connect.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		if(bookingFirstName.getText().isEmpty() || bookingLastName.getText().isEmpty() || bookingPhoneNumber.getText().isEmpty() || bookingLocation.getText().isEmpty() ||
				bookingDate.getValue()== null) {
			showAlert(Alert.AlertType.ERROR,"ERROR MESSAGE","Please fill all fields!!");
		}else {
			try {                                              
				preparedStatement=connection.prepareStatement("SELECT * FROM booking WHERE firstName=? AND lastName=? AND date=? AND phoneNumber=? AND location=? AND email=? AND clinic=? AND status=?");
				preparedStatement.setString(1, bookingFirstName.getText());
				preparedStatement.setString(2, bookingLastName.getText());
				preparedStatement.setString(3, bookingDate.getValue().toString());
				preparedStatement.setString(4, bookingPhoneNumber.getText());
				preparedStatement.setString(5, bookingLocation.getText());
				preparedStatement.setString(6, bookingEmail.getText());
				preparedStatement.setString(7, getData.clinicName);
				preparedStatement.setString(8, "pending");
				resultSet = preparedStatement.executeQuery() ;            
				if(resultSet.next()) {
					System.out.println("Booking Already Exist!");
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Entered username has been taken already");
					alert.show();
				}
				else {         
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, bookingFirstName.getText());
					preparedStatement.setString(2, bookingLastName.getText());
					preparedStatement.setString(3, bookingDate.getValue().toString());
					preparedStatement.setString(4, bookingPhoneNumber.getText());
					preparedStatement.setString(5, bookingLocation.getText());
					preparedStatement.setString(6, bookingEmail.getText());
					preparedStatement.setString(7, getData.clinicName);
					preparedStatement.setString(8, "pending");
					int ret =preparedStatement.executeUpdate();
					if(ret > 0) {
						preparedStatement=connection.prepareStatement("SELECT * FROM booking WHERE firstName=? AND lastName=? AND date=? AND phoneNumber=? AND location=? AND email=? AND clinic=? AND status=? ");
						preparedStatement.setString(1, bookingFirstName.getText());
						preparedStatement.setString(2, bookingLastName.getText());
						preparedStatement.setString(3, bookingDate.getValue().toString());
						preparedStatement.setString(4, bookingPhoneNumber.getText());
						preparedStatement.setString(5, bookingLocation.getText());
						preparedStatement.setString(6, bookingEmail.getText());
						preparedStatement.setString(7, getData.clinicName);
						preparedStatement.setString(8, "pending");
						resultSet = preparedStatement.executeQuery() ;            
						if(resultSet.next()) {
							int id = resultSet.getInt("id");
							String firstname = resultSet.getString("firstName");
							showAlert(Alert.AlertType.INFORMATION,"INFORMATION MESSAGE","You have have successfully book an appointment to "+ getData.clinicName + " Your Booking Id is: "+firstname+"-"+id );
						}
						
					}else {
						showAlert(Alert.AlertType.ERROR,"ERROR MESSAGE","An error occured");
					}


				}

			}catch(SQLException e) {
				e.printStackTrace();
			}

			
		}
			
	}
	
	
	 private void showAlert(Alert.AlertType alertType, String title, String message) {
			// TODO Auto-generated method stub
			Alert alert=new Alert(alertType);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.show();
		}
	   

	public void backToClinicPage() {
		bookingForm.setVisible(false);
		clinicForm.setVisible(true);
		
	}
	public void backHomePage() {
		try {
			System.out.println("logged in");
			Stage stage = (Stage)backHomeBtn.getScene().getWindow() ;
			stage.close();
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stagenew = new Stage();
			stagenew.setTitle("HomePage");
			stagenew.setScene(scene);
			stagenew.show();
		}catch(IOException  e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
