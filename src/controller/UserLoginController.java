/*
 * Threaded Project Term 3 - Workshop 6
 * Purpose: This is the code for the class that runs
 * the Agent login scene
 * Author: Group 2 - Doug Cameron and Sean Burman - primary developers of this page
 * Date: Oct, 2020
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Validator.Validator;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Agent;

public class UserLoginController {

    public Agent a;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane mainPane;

    @FXML
    private Label lbl_Username;

    @FXML
    private TextField txt_Username;

    @FXML
    private Label lbl_Password;

    @FXML
    private TextField txt_Password;

    @FXML
    private Button btn_Login;

    @FXML
    private Label txtAlert;

    @FXML
    void initialize() {
        assert lbl_Username != null : "fx:id=\"lbl_Username\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert txt_Username != null : "fx:id=\"txt_Username\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert lbl_Password != null : "fx:id=\"lbl_Password\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert txt_Password != null : "fx:id=\"txt_Password\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert btn_Login != null : "fx:id=\"btn_Login\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert txtAlert != null : "fx:id=\"txtAlert\" was not injected: check your FXML file 'AgentLogin.fxml'.";

        Connection conn = connectDB();  //call method to create the db connection
        Statement stmt = null;  //create a statement object
        Statement stmtAgencyID = null;  //create a statement object

        btn_Login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Connection conn = connectDB(); //set a new db connection
                Statement stmtSelect;
                //set a reference to the validator classes
                Validator v = new Validator();

                if (!v.isPresentNoAlert(txt_Username)) { // check it username is present
                    txtAlert.setText("Please enter a username.");
                }
                if (!v.isPresentNoAlert(txt_Password)) { // check if password is present
                    txtAlert.setText("Please enter a password");
                }
                if (!v.isPresentNoAlert(txt_Username) && !v.isPresentNoAlert(txt_Password)) {
                    txtAlert.setText("Please enter a username and password.");
                }
//                if (!v.isValidEmailNoAlert(txt_Username)) {
//                    txtAlert.setText("Invalid username.");
//                }
                if (v.isPresentNoAlert(txt_Username) &&
                        v.isPresentNoAlert(txt_Password) //&&
                   //     v.isValidEmailNoAlert(txt_Username)

                ){
                    try {
                        stmtSelect = conn.createStatement();
                        String searchUserName = txt_Username.getText();//get the username from textfield
                        String searchPassword = txt_Password.getText();//get password

                        //System.out.println(searchEmail);
                        //create the sql to query the agents from the table
                        String sql = "Select * from agents Where AgtEmail= '" + searchUserName + "' AND AgtLastName= '" + searchPassword + "'";
                        System.out.println(sql);
                        ResultSet rs = stmtSelect.executeQuery(sql); //execute the query and get data

                        if(rs.next())
                        {
                            //create a new agent object from the retrieved data
                            Agent a = new Agent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getInt(8));

                            //create a new stage for a scene
                            Stage stage = new Stage();

                            try {
                                //set a new loader for the main.fxml scene
                                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/main.fxml"), resources);
                                Parent root = (Parent) loader.load();
                                mainPane.getChildren().setAll(root);
                                DashBoardController addDashBoardController = loader.getController();
                                addDashBoardController.GetAgentInfo(a);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            txtAlert.setText("Invalid Login Credentials. Please Try Again.");
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
    //method for the database connection
    private Connection connectDB(){
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//db driver name
            //set the connection parameters
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","TEAdmin","password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }

    //the method was used only for testing to alert if user
    //was successfully logged in
    private void createMessageBox(String fieldName, String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Query Message");
        alert.setHeaderText(fieldName);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
