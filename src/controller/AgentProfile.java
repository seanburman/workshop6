package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AgentProfile {

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
    private ComboBox<?> cbAgencyId;

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

    }
}

