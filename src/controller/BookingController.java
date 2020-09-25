package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class BookingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pageBookings;

    @FXML
    private ComboBox<?> cbCustomer;

    @FXML
    private ComboBox<?> cbTripType;

    @FXML
    private ComboBox<?> cbPackage;

    @FXML
    private DatePicker dtBookingStart;

    @FXML
    private DatePicker dtBookingEnd;

    @FXML
    private CheckBox chkCustEmails;

    @FXML
    private Button btnSubmit;

    @FXML
    void initialize() {
        assert pageBookings != null : "fx:id=\"pageBookings\" was not injected: check your FXML file 'booking.fxml'.";
        assert cbCustomer != null : "fx:id=\"cbCustomer\" was not injected: check your FXML file 'booking.fxml'.";
        assert cbTripType != null : "fx:id=\"cbTripType\" was not injected: check your FXML file 'booking.fxml'.";
        assert cbPackage != null : "fx:id=\"cbPackage\" was not injected: check your FXML file 'booking.fxml'.";
        assert dtBookingStart != null : "fx:id=\"dtBookingStart\" was not injected: check your FXML file 'booking.fxml'.";
        assert dtBookingEnd != null : "fx:id=\"dtBookingEnd\" was not injected: check your FXML file 'booking.fxml'.";
        assert chkCustEmails != null : "fx:id=\"chkCustEmails\" was not injected: check your FXML file 'booking.fxml'.";
        assert btnSubmit != null : "fx:id=\"btnSubmit\" was not injected: check your FXML file 'booking.fxml'.";

    }
}
