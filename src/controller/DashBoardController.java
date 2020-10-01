package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private ImageView iconAdd;

    @FXML
    private ImageView iconSearch;

    @FXML
    private ImageView iconBook;

    @FXML
    private ImageView iconLogout;

    @FXML
    private AnchorPane dynamicPane;

    @FXML
    private Button btnAgent;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnBook;

    @FXML
    void add(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/AddCustomer.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Add Customer Loaded...");
    }

    @FXML
    void agent(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/AgentProfile.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Agent Profile Loaded...");
    }

    @FXML
    void book(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/booking.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Customer Booking Loaded...");
    }

    @FXML
    void dash(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/main.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("DashBoard Loaded...");
    }

    @FXML
    void search(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/SearchView.fxml"));
        dynamicPane.getChildren().setAll(root);
        System.out.println("Search View Loaded...");
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'main.fxml'.";
        assert iconAdd != null : "fx:id=\"iconAdd\" was not injected: check your FXML file 'main.fxml'.";
        assert iconSearch != null : "fx:id=\"iconSearch\" was not injected: check your FXML file 'main.fxml'.";
        assert iconBook != null : "fx:id=\"iconBook\" was not injected: check your FXML file 'main.fxml'.";
        assert iconLogout != null : "fx:id=\"iconLogout\" was not injected: check your FXML file 'main.fxml'.";
        assert dynamicPane != null : "fx:id=\"dynamicPane\" was not injected: check your FXML file 'main.fxml'.";
        assert btnAgent != null : "fx:id=\"btnAgent\" was not injected: check your FXML file 'main.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'main.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'main.fxml'.";
        assert btnBook != null : "fx:id=\"btnBook\" was not injected: check your FXML file 'main.fxml'.";

    }
}
