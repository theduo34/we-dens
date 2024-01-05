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
 * class set to be the main of operation
 * to do all the communication ,request and update,
 * in coperation with the database connection
 * 
 */
public class DButil {
    private static String dbUrl="jdbc:mysql://localhost:3306/wedens";   //database info
	private static String dbUsername="root";
	private static String dbPassword="";

	public Connection connect;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(dbUrl,dbUsername,dbPassword );
		}catch(Exception e) {
			e.getCause();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Connection Error!");
			alert.setHeaderText("Error!");
			alert.setContentText("Unable to make a connection to database, please check your connection!!!");
			alert.showAndWait();
		}

		return connect;
		
	}
}
