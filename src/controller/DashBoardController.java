package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane dynamicPane;

    @FXML
    private Button btnAgentProfile;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnSearchCustomer;

    @FXML
    private Button btnBrowseTP;

    @FXML
    private ImageView agentIcon;

    @FXML
    private ImageView bookIcon;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ImageView addCustomerIcon;

    @FXML
    private ImageView logoutIcon;

    @FXML
    void addCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/AddCustomer.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Add Customer Loaded");
    }

    @FXML
    void addCustomerIconClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/AddCustomer.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Add Customer Loaded");
    }

    @FXML
    void agentIconClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/Dashboard.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Agent Profile Loaded");
    }

    @FXML
    void agentProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/AgentProfile.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Agent Profile Loaded");
    }

    @FXML
    void bookIconClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/booking.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Customer Booking Loaded");
    }

    @FXML
    void booking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/booking.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Customer Booking Loaded");
    }

    @FXML
    void loadMain(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/Dashboard.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Main View Loaded");
    }

    @FXML
    void searchIconClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/SearchView.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Search View Loaded");
    }

    @FXML
    void searchView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/SearchView.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Search View Loaded");
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert dynamicPane != null : "fx:id=\"dynamicPane\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnAgentProfile != null : "fx:id=\"btnAgentProfile\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnAddCustomer != null : "fx:id=\"btnAddCustomer\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnSearchCustomer != null : "fx:id=\"btnSearchCustomer\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnBrowseTP != null : "fx:id=\"btnBrowseTP\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert agentIcon != null : "fx:id=\"agentIcon\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert bookIcon != null : "fx:id=\"bookIcon\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert searchIcon != null : "fx:id=\"searchIcon\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert addCustomerIcon != null : "fx:id=\"addCustomerIcon\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert logoutIcon != null : "fx:id=\"logoutIcon\" was not injected: check your FXML file 'Dashboard.fxml'.";

    }
}
