package controller;

/*
Author: Sean Burman
 */

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

public class SplashController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView splashPlane;

    @FXML
    private Button btnBegin;

    @FXML
    void loadLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/AgentLogin.fxml"), resources);
        Parent root = (Parent) loader.load();
        mainPane.getChildren().setAll(root);
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'Splash.fxml'.";
        assert splashPlane != null : "fx:id=\"splashPlane\" was not injected: check your FXML file 'Splash.fxml'.";
        assert btnBegin != null : "fx:id=\"btnBegin\" was not injected: check your FXML file 'Splash.fxml'.";

    }
}

