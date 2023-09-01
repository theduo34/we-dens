package lib;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import application.HomePageController;
import application.Main;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/*
 * utility class is act as the main of operation
 * to do all the communication ,to send and update user,
 * and changing scene to the correct one
 * 
 */


public class DButil {

	/*
	 * method to change which takes parameter like Actionevent,
	 * fxmlfile which will will be passed ,
	 * title to set at the top of the scene,then username of the user been loggined
	 * 
	 */
	public static void changeScene(ActionEvent event,String fxmlFile,String title,String username) {
		Parent root=null;            //Base class for all the nodes passed to the scene

		/*
		 * validation check
		 */
		if(username!=null) {
			try {
				FXMLLoader loader=new FXMLLoader(DButil.class.getResource(fxmlFile));
				root=loader.load();
				HomePageController homePageController=loader.getController(); 
				homePageController.setUserInformation(username);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				root=FXMLLoader.load(DButil.class.getResource(fxmlFile));
			}catch(IOException e){
				e.printStackTrace();
			}
		}

		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root,600,600));
		stage.show();

	}

	/*
	 * method to establish connection with our database when new 
	 * user is creating account
	 */
	public static void signUp(ActionEvent event,String username,String email,String password,String telephone,String gender) {
		Connection connection=null;
		PreparedStatement psInsert=null;
		PreparedStatement psCheckUserExists=null;
		ResultSet resultSet=null;
		
		String dbUrl="jdbc:mysql://localhost:3306/wedens";   //database info
		String dbUsername="root";
		String dbPassword="";
		
		try {                                                      //database connection                        
			connection=DriverManager.getConnection(dbUrl,dbUsername,dbPassword );
			psCheckUserExists=connection.prepareStatement("SELECT * FROM WHERE username=?");
			psCheckUserExists.setString(1, username);
			//psCheckUserExits.setString(2,email);
			resultSet=psCheckUserExists.executeQuery() ;            //username does exists
			if(resultSet.isBeforeFirst()) {
				System.out.println("User Already Exist!");
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Entered username has been taken already");
				alert.show();
			}
			else {                                            //username not in existence
				psInsert=connection.prepareStatement("INSERT INTO users(username,email,password,telephone,gender) VALUE(?,?,?,?,?)");
				psInsert.setString(1, username);
				psInsert.setString(2, email);
				psInsert.setString(3, password);
				psInsert.setString(4, telephone);
				psInsert.setString(5, gender);
				psInsert.executeUpdate();

				changeScene(event,"HomePage.fxml","Welcome!",null);


			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		/*
		 * finally block excute automatically no matter,
		 * and this is use to close our databse connection with the
		 * sense of preventing memory leakage
		 */
		finally {	
			if(resultSet!=null) {
				try {
					resultSet.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(psCheckUserExists!=null) {
				try {
					psCheckUserExists.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(psInsert!=null) {
				try {
					psInsert.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection!=null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}
	

	/*
	 * method to establish with our database connection when logging
	 * to ensure that user account info provided in the login page
	 * is certainly in existence on our database
	 */
	public static void loginUser(ActionEvent event,String username,String password) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null; 
		
		String dbUrl="jdbc:mysql://localhost:3306/wedens";   //database info
		String dbUsername="root";
		String dbPassword="";

		try {                                                  //databse connection  
			connection=DriverManager.getConnection(dbUrl,dbUsername,dbPassword );
			//connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/wedens","wedens@localhost", "wedens");
			preparedStatement=connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			//psCheckUserExits.setString(2,email);
			resultSet=preparedStatement.executeQuery() ;                                           //user credentials in existence 
				if(resultSet.next()) {
					
					try {
						System.out.println("logged in");
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
		finally {
			if(resultSet!=null) {
				try {
					resultSet.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				}
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}



		}

	}
}
