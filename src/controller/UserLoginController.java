package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserLoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void initialize() {
        assert lbl_Username != null : "fx:id=\"lbl_Username\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert txt_Username != null : "fx:id=\"txt_Username\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert lbl_Password != null : "fx:id=\"lbl_Password\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert txt_Password != null : "fx:id=\"txt_Password\" was not injected: check your FXML file 'AgentLogin.fxml'.";
        assert btn_Login != null : "fx:id=\"btn_Login\" was not injected: check your FXML file 'AgentLogin.fxml'.";

    }
}
