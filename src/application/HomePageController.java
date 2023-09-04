package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
	private Button contactBtn,accountBtn,aboutBtn;

	@FXML
	private Button seeMoreBtn,bookNowBtn,contactUsBtn,bookingBtn;
	;
	@FXML
	private Button logoutBtn;

	@FXML
	private Label welcomeLabel;

	@FXML
	private AnchorPane appointmentForm,homeForm;

	@FXML
	private Button appointmentForm_cancelBtn;

	@FXML
	private Button appointmentForm_completeBtn;

	@FXML
	private TableView<BookingModel> appointmentForm_tableView;

	@FXML
	private TableColumn<BookingModel, String> appointmentForm_tableView_col_date;

	@FXML
	private TableColumn<BookingModel, String> appointmentForm_tableView_col_email;

	@FXML
	private TableColumn<BookingModel, String> appointmentForm_tableView_col_location;

	@FXML
	private TableColumn<BookingModel, String> appointmentForm_tableView_col_name;

	@FXML
	private TableColumn<BookingModel, String> appointmentForm_tableView_col_phone;

	ObservableList<BookingModel> bookingModelObservableList = FXCollections.observableArrayList();



	GetData getData = new GetData(); 


	@Override
	public void initialize(URL location, ResourceBundle resource) {

		findAccess(getData.accesstype);
		setUserInformation(getData.userName); 

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
		//		homeBtn.setOnAction(new EventHandler<ActionEvent>() {
		//			@Override
		//			public void handle(ActionEvent event) {
		//				try {
		//					FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
		//					Scene scene = new Scene(fxmlLoader.load());
		//					Stage stagenew = new Stage();
		//					stagenew.setTitle("HomePage!");
		//					stagenew.setScene(scene);
		//					stagenew.showAndWait();
		//				}catch(IOException  e) {
		//					e.printStackTrace();
		//					System.out.println(e.getMessage());
		//					}
		//				}
		//			});


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


		bookNowBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Stage stage = (Stage)bookNowBtn.getScene().getWindow() ;
					stage.close();

					FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddAppointment.fxml"));
					Scene scene = new Scene(fxmlLoader.load());
					Stage stagenew = new Stage();
					stagenew.setTitle("ADD APPOINTMENT!");
					stagenew.setScene(scene);
					stagenew.showAndWait();
				}catch(IOException  e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		});

		contactUsBtn.setOnAction(new EventHandler<ActionEvent>() {
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


		seeMoreBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// Display the contact information when the button is clicked
					Label emergencyService = new Label();
					String urgentInfo = "Emergency Support Team:\n"
							+ " >>>> 24/7 Emergency Hotline: Call our emergency "
							+ "dental hotline at [+233-20 682 4003] for \n"
							+ "immediate assistance in case of \n"
							+ "dental emergencies\n"
							+ " >>>> Dental Trauma Care: Our experienced dental \n"
							+ "professionals are skilled in treating dental \n"
							+ "traumas such as knocked-out teeth, broken teeth,\n"
							+ " or severe toothaches\n"
							+ " >>>> Pain Relief: If you are experiencing severe\n"
							+ " dental pain or discomfort, our emergency dental \n"
							+ "services can provide pain relief and address the \n"
							+ "underlying cause of the issue.\n"
							+ " >>>> Oral Infections & Swellings: Our emergency services \n"
							+ "can treat oral infections, abscesses, and swellings \n"
							+ "to prevent further complications and promote healing\n"
							+ "We prioritize your urgent dental needs and are \n"
							+ "committed to delivering efficient and effective \n"
							+ "emergency dental care \n";

					emergencyService.setFont(Font.font("Sans Serif",FontWeight.NORMAL,14));
					emergencyService.setTextFill(Color.WHITE);
					emergencyService.setBackground(new Background(new BackgroundFill(Color.VIOLET,null,null)));
					emergencyService.setText(urgentInfo);

					Stage stage=new Stage();
					StackPane root =new StackPane();
					Scene scene=new Scene(root,500,500);

					root.getChildren().add(emergencyService);
					stage.setTitle("Emergency Service!");
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


	public void cancelBokking() {
		String sql = "UPDATE booking SET status=? WHERE id=?";

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {           
			DButil connect = new DButil();
			Connection connection = connect.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "canceled");
			preparedStatement.setString(2, "");
			int result =preparedStatement.executeUpdate();                                           //user credentials in existence 
			if(result > 0) {
				showAlert(Alert.AlertType.INFORMATION,"INFORMATION MESSAGE","Booking canceled");
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}


	}
	public void findAccess(String id) {
		if(id.equals("admin")) {
			bookingBtn.setVisible(true);
		}else {
			bookingBtn.setVisible(false);
		}			

	}

	public void movetobookings() {
		appointmentForm.setVisible(true);
		homeForm.setVisible(false);

		String sql = "SELECT * FROM booking where status=?";

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {           
			DButil connect = new DButil();
			Connection connection = connect.getConnection();

			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "pending");
			resultSet = preparedStatement.executeQuery() ;            
			while(resultSet.next()) {

				Integer id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phone = resultSet.getString("phoneNumber");
				String location = resultSet.getString("location");
				String email = resultSet.getString("email");
				String date = resultSet.getString("date");

				bookingModelObservableList.add(new BookingModel(id,firstName,lastName,phone,location,email,date));

			}
			appointmentForm_tableView_col_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			appointmentForm_tableView_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
			appointmentForm_tableView_col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
			appointmentForm_tableView_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
			appointmentForm_tableView_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

			appointmentForm_tableView.setItems(bookingModelObservableList);

		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}

	public void movetohome() {
		appointmentForm.setVisible(false);
		homeForm.setVisible(true);
	}
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		// TODO Auto-generated method stub
		Alert alert=new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
	/*
	 * method to set welcome message and a username
	 */
	public void setUserInformation(String username) {
		welcomeLabel.setText("Welcome," + username + "!");

	}
}