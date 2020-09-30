package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Validator.Validator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Agency;
import model.Agent;

public class AgentProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAgtFirstName;

    @FXML
    private TextField txtAgtLastName;

    @FXML
    private TextField txtAgtBusPhone;

    @FXML
    private TextField txtAgtEmail;

    @FXML
    private Button btnAgtAddPhone;

    @FXML
    private Button btnAgtProfileRefresh;

    @FXML
    private TextField txtAgtMiddleInitial;

    @FXML
    private TextField txtAgtPosition;

    @FXML
    private TextField txtAgtAgencyId;

    @FXML
    private ComboBox<Agency> cbAgencyId;

    @FXML
    private Button btnAgtProfileEdit;

    @FXML
    private Button btnAgtProfileSave;

    @FXML
    private Button btnAgtProfileCancel;

    @FXML
    void initialize() {
        assert txtAgtFirstName != null : "fx:id=\"txtAgtFirstName\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert txtAgtLastName != null : "fx:id=\"txtAgtLastName\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert txtAgtBusPhone != null : "fx:id=\"txtAgtBusPhone\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert txtAgtEmail != null : "fx:id=\"txtAgtEmail\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert btnAgtAddPhone != null : "fx:id=\"btnAgtAddPhone\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert btnAgtProfileRefresh != null : "fx:id=\"btnAgtProfileRefresh\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert txtAgtMiddleInitial != null : "fx:id=\"txtAgtMiddleInitial\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert txtAgtPosition != null : "fx:id=\"txtAgtPosition\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert cbAgencyId != null : "fx:id=\"cbAgencyId\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert btnAgtProfileEdit != null : "fx:id=\"btnAgtProfileEdit\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert btnAgtProfileSave != null : "fx:id=\"btnAgtProfileSave\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert btnAgtProfileCancel != null : "fx:id=\"btnAgtProfileCancel\" was not injected: check your FXML file 'AgentProfile.fxml'.";
        assert txtAgtAgencyId != null : "fx:id=\"txtAgtAgencyId\" was not injected: check your FXML file 'AgentProfile.fxml'.";

        Connection conn = connectDB();  //call method to create the db connection
        ObservableList<Agency> agenciesList = FXCollections.observableArrayList(); // create a new

        Statement stmt = null;  //create a statement object
        Statement stmtAgencyID = null;  //create a statement object
        //call the load method

        initialLoad(conn, agenciesList);

        cbAgencyId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agency>() {
            @Override
            public void changed(ObservableValue<? extends Agency> observableValue, Agency agency, Agency t1) {
                txtAgtAgencyId.setText(t1.getAgencyId() + "");
            }
        });

        btnAgtProfileEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtAgtFirstName.setDisable(false);
                txtAgtFirstName.setEditable(true);
                txtAgtMiddleInitial.setDisable(false);
                txtAgtMiddleInitial.setEditable(true);
                txtAgtLastName.setDisable(false);
                txtAgtLastName.setEditable(true);
                txtAgtBusPhone.setDisable(false);
                txtAgtBusPhone.setEditable(true);
                txtAgtEmail.setDisable(false);
                txtAgtEmail.setEditable(true);
                txtAgtPosition.setDisable(false);
                txtAgtPosition.setEditable(true);
                txtAgtAgencyId.setDisable(false);
                txtAgtAgencyId.setEditable(true);
                cbAgencyId.setDisable(false);
                btnAgtProfileSave.setDisable(false);
                btnAgtProfileEdit.setDisable(true);
            }
        });

        btnAgtProfileCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) btnAgtProfileCancel.getScene().getWindow();
                stage.close();
            }
        });
        btnAgtProfileSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Connection conn = connectDB(); //set a new db connection
                //prepare the sql update statement
                String sql = "UPDATE `agents` SET `AgtFirstName`=?,`AgtMiddleInitial`=?,`AgtLastName`=?,`AgtBusPhone`=?,`AgtEmail`=?,`AgtPosition`=?,`AgencyId`=? WHERE AgentId=3";

                Validator v = new Validator();
                if (v.isPresent(txtAgtFirstName, "First Name", " must not be empty") &&
                v.isPresent(txtAgtLastName, "Last Name", " must not be empty") &&
                //v.isValidPhone(txtAgtBusPhone, "Business Phone", " format may be incorrect")&&
                //v.isValidEmail(txtAgtEmail, "Email", " format may be incorrect") &&
                v.isPresent(txtAgtPosition, "Agent Position", " must not be empty")) {
                    try {
                        PreparedStatement stmtUpdate = conn.prepareStatement(sql);
                        stmtUpdate.setString(1,txtAgtFirstName.getText());
                        stmtUpdate.setString(2,txtAgtMiddleInitial.getText());
                        stmtUpdate.setString(3,txtAgtLastName.getText());
                        stmtUpdate.setString(4,txtAgtBusPhone.getText());
                        stmtUpdate.setString(5,txtAgtEmail.getText());
                        stmtUpdate.setString(6,txtAgtPosition.getText());
                        stmtUpdate.setInt(7, Integer.parseInt(txtAgtAgencyId.getText()));

                        //get the number of rows that were updated
                        int numRows = stmtUpdate.executeUpdate();
                        //if the number of updates was zero
                        if (numRows == 0) {
                            //System.out.println("Failed");
                            createMessageBox("Update Failed");
                        }
                        else
                        {
                            //System.out.println("Updated");
                            createMessageBox("Update Successful");
                            //initialLoad(conn, agenciesList);

                            txtAgtFirstName.setDisable(true);
                            txtAgtFirstName.setEditable(false);
                            txtAgtMiddleInitial.setDisable(true);
                            txtAgtMiddleInitial.setEditable(false);
                            txtAgtLastName.setDisable(true);
                            txtAgtLastName.setEditable(false);
                            txtAgtBusPhone.setDisable(true);
                            txtAgtBusPhone.setEditable(false);
                            txtAgtEmail.setDisable(true);
                            txtAgtEmail.setEditable(false);
                            txtAgtPosition.setDisable(true);
                            txtAgtPosition.setEditable(false);
                            txtAgtAgencyId.setDisable(true);
                            txtAgtAgencyId.setEditable(false);
                            cbAgencyId.setDisable(true);
                            btnAgtProfileSave.setDisable(true);
                            btnAgtProfileEdit.setDisable(false);

                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    private void initialLoad(Connection conn, ObservableList<Agency> agenciesList) {
        Statement stmtAgencyID;
        Statement stmt;
        try {
            stmt = conn.createStatement();
            String sql = "select * from agents where AgentId = 3"; //create the select sql string
            ResultSet rs = stmt.executeQuery(sql); //execute the query and get data


            if(rs.next())
            {
                Agent a = new Agent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getInt(8));
                //txtAgentId.setText(rs.getAgentId() + "");
                txtAgtFirstName.setText(a.getFirstName());
                txtAgtMiddleInitial.setText(a.getMiddleInitial());
                txtAgtLastName.setText(a.getLastName());
                txtAgtBusPhone.setText(a.getBusinessPhone());
                txtAgtEmail.setText(a.getEmail());
                txtAgtPosition.setText(a.getPosition());
                txtAgtAgencyId.setText(a.getAgencyId() +"");
                int agentId = a.getAgentId();
                //Agent agencyId = new Agent(rs.getInt(1));


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            stmtAgencyID = conn.createStatement();
            String sqlAgencyId = "select DISTINCT AgencyId from agencies"; //create the select sql string
            ResultSet rsAgency = stmtAgencyID.executeQuery(sqlAgencyId); //execute the query and get data

            while (rsAgency.next()) {  // process each record in the result set
                //add the new agent objects to the list by calling the agent constructor
                agenciesList.add(new Agency(rsAgency.getInt(1)));
            }
            cbAgencyId.setItems(agenciesList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //method for the database connection
    private Connection connectDB() {
        Connection c = null; //declare the connection set it to null
        try {
            Class.forName("com.mysql.jdbc.Driver");  //driver at the package path
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "TAAdmin", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c; //return the connection
    }

    private void createMessageBox(String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Message");
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
        }
}

