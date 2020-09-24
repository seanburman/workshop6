package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AddCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_CustFName;

    @FXML
    private TextField txt_CustLName;

    @FXML
    private TextField txt_CustBusPhone;

    @FXML
    private TextField txt_CustEmail;

    @FXML
    private Button btn_CustAddPhone;

    @FXML
    private Pane pnl_CustHomePhone;

    @FXML
    private TextField txt_CustHomePhone;

    @FXML
    private TextField txt_CustAddress;

    @FXML
    private TextField txt_CustPostal;

    @FXML
    private TextField txt_CustCity;

    @FXML
    private ComboBox<?> cb_CustProvince;

    @FXML
    private ComboBox<?> cb_CustCountry;

    @FXML
    private Button btn_AddCustomerRefresh;

    @FXML
    private Button btn_AddCustomerSave;

    @FXML
    private Button btn_AddCustomerCancel;

    @FXML
    void initialize() {
        assert txt_CustFName != null : "fx:id=\"txt_CustFName\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert txt_CustLName != null : "fx:id=\"txt_CustLName\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert txt_CustBusPhone != null : "fx:id=\"txt_CustBusPhone\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert txt_CustEmail != null : "fx:id=\"txt_CustEmail\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert btn_CustAddPhone != null : "fx:id=\"btn_CustAddPhone\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert pnl_CustHomePhone != null : "fx:id=\"pnl_CustHomePhone\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert txt_CustHomePhone != null : "fx:id=\"txt_CustHomePhone\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert txt_CustAddress != null : "fx:id=\"txt_CustAddress\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert txt_CustPostal != null : "fx:id=\"txt_CustPostal\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert txt_CustCity != null : "fx:id=\"txt_CustCity\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert cb_CustProvince != null : "fx:id=\"cb_CustProvince\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert cb_CustCountry != null : "fx:id=\"cb_CustCountry\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert btn_AddCustomerRefresh != null : "fx:id=\"btn_AddCustomerRefresh\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert btn_AddCustomerSave != null : "fx:id=\"btn_AddCustomerSave\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert btn_AddCustomerCancel != null : "fx:id=\"btn_AddCustomerCancel\" was not injected: check your FXML file 'AddCustomer.fxml'.";

        //one controller to edit and add
        //if customer exists (
        //edit customer method)
        //else if customer does not exist(
        //add customer method)

    }
}
