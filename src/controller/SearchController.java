package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Customer;

import javax.xml.transform.Result;
import javax.xml.validation.Validator;

public class SearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfPostal;

    @FXML
    private TableView<?> tvBookings;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnBookAdd;

    @FXML
    private Button btnBookEdit;

    @FXML
    void initialize() {
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfFirstName != null : "fx:id=\"tfFirstName\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfLastName != null : "fx:id=\"tfLastName\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfPhone != null : "fx:id=\"tfPhone\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfCity != null : "fx:id=\"tfCity\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfCountry != null : "fx:id=\"tfCountry\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfPostal != null : "fx:id=\"tfPostal\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tvBookings != null : "fx:id=\"tvBookings\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnBookAdd != null : "fx:id=\"btnBookAdd\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnBookEdit != null : "fx:id=\"btnBookEdit\" was not injected: check your FXML file 'SearchView.fxml'.";

        Connection conn = connectDB();
        final Customer[] customer = new Customer[1];

        btnSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tfEmail.getText() != "" || tfPhone.getText() != ""){
                    try {
                        String email = tfEmail.getText();
                        System.out.println(email);
                        String sql = "Select * from customers Where CustEmail= '" + email + "'";
                        Statement statement = conn.createStatement();
                        ResultSet rs = statement.executeQuery(sql);
                        rs.first();
                        customer[0] = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),
                                rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
                        ,rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));

                        tfPhone.setText(customer[0].getCustHomePhone());
                        tfFirstName.setText(customer[0].getCustFirstName());
                        tfLastName.setText(customer[0].getCustLastName());
                        tfCity.setText(customer[0].getCustCity());
                        tfAddress.setText(customer[0].getCustAddress());
                        tfCountry.setText(customer[0].getCustCountry());
                        tfPostal.setText(customer[0].getCustPostal());


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });


    }


    private Connection connectDB(){
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","TEAdmin","password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }
}