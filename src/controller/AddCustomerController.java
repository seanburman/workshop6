package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import model.Customer;
import model.Province;

public class AddCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane addCustomerPane;

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
    private ComboBox<Province> cb_CustProvince;

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
        assert addCustomerPane != null : "fx:id=\"addCustomerPane\" was not injected: check your FXML file 'AddCustomer.fxml'.";
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

        //idk about this
        ObservableList<Province> provinceList = FXCollections.observableArrayList();
        provinceList.add(new Province("British Columbia", "BC"));
        provinceList.add(new Province("Alberta", "AB"));
        provinceList.add(new Province("Saskatchewan", "SK"));
        provinceList.add(new Province("Manitoba", "MB"));
        provinceList.add(new Province("Ontario", "ON"));
        provinceList.add(new Province("Quebec", "QC"));
        provinceList.add(new Province("Nova Scotia", "NS"));
        provinceList.add(new Province("New Brunswick", "NB"));
        provinceList.add(new Province("Prince Edward Island", "PE"));
        provinceList.add(new Province("Newfoundland and Labrador", "NL"));
        provinceList.add(new Province("Northwest Territories", "NT"));
        provinceList.add(new Province("Nunavut", "NU"));
        provinceList.add(new Province("Yukon", "YT"));

//        private void displayProvince(Province p){
//            cb_CustProvince.setText(c.getProvinceName);
//        }
//
//        cb_CustProvince.setItems(provinceList);
//        cb_CustProvince.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        displayProvince(Province.get(0));
//        cb_CustProvince.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                Province p = cb_CustProvince.getSelectionModel().getSelectedItem();
//                displayProvince(p);
//            }
//        });
//
        btn_AddCustomerRefresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //clear all fields
                //refocus onto customer phone number field
                // first name, last name, Address, Postal, city, province, country disabled untill Phone & email are filled
            }

        });

        btn_AddCustomerCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // close Add a customer form,
                //open agent dashboard
            }
        });

        btn_AddCustomerSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //check all required fields are completed
                //save to db
                //close add customer form

                //open book a trip
            }
        });

    }
}

//one controller to edit and add
        //if customer exists (
        //edit customer method)
        //else if customer does not exist(
        //add customer method)
