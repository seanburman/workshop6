package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;
import java.sql.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import model.Booking;
import model.TravelPackage;
import model.TripType;

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
    private ComboBox<TripType> cbTripType;

    @FXML
    private ComboBox<TravelPackage> cbPackage;

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

        Connection conn = connectDB();
        ObservableList<TravelPackage> packageList = FXCollections.observableArrayList();
        ObservableList<TripType> tripTypeList = FXCollections.observableArrayList();

        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.Statement ttstmt = conn.createStatement();
            String sql = "select * from packages";
            String ttsql = "select * from triptypes";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet ttrs = ttstmt.executeQuery(ttsql);
            while (rs.next() && ttrs.next())
            {
                packageList.add(new TravelPackage(rs.getInt(1), rs.getString(2),
                                            rs.getDate(3), rs.getDate(4),
                                            rs.getString(5), rs.getDouble(6),
                                            rs.getDouble(7)));
                tripTypeList.add(new TripType(ttrs.getString(1), ttrs.getString(2)));

            }
            cbPackage.setItems(packageList);
            cbTripType.setItems(tripTypeList);
            conn.close();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

//        cbPackage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TravelPackage>() {
//            @Override
//            public void changed(ObservableValue<? extends TravelPackage> observableValue, TravelPackage travelpackage, TravelPackage t1) {
//                txtAgentId.setText(t1.getAgentId() + "");
//                txtAgtFirstName.setText(t1.getAgtFirstName());
//                txtAgtMiddleInitial.setText(t1.getAgtMiddleInitial());
//                txtAgtLastName.setText(t1.getAgtLastName());
//                txtAgtBusPhone.setText(t1.getAgtBusPhone());
//                txtAgtEmail.setText(t1.getAgtEmail());
//                txtAgtPosition.setText(t1.getAgtPosition());
//                txtAgencyId.setText(t1.getAgencyId() + "");
//            }
//        });

    }
    private Connection connectDB() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "jessy", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
