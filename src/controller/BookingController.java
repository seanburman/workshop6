package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;
import java.sql.*;

import com.sun.javafx.scene.control.DatePickerContent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;

import javax.swing.*;


public class BookingController {
    public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
    Booking booking;
    int bookingId;
    int custID;
    JFrame f;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pageBookings;

//    @FXML
//    private ComboBox<String> cbCustomer;

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
    private TextArea txtPackageDescription;

    @FXML
    private Label txtTripType;

    @FXML
    private Label txtPackageName;

    @FXML
    private Label txtPackagePrice;

    @FXML
    private Label lblCustomer;

    @FXML
    private TextField txtItineraryNo;

    @FXML
    private TextField txtDestination;

    @FXML
    private Label txtAlert;

    @FXML
    void initialize() {
        assert pageBookings != null : "fx:id=\"pageBookings\" was not injected: check your FXML file 'booking.fxml'.";
//        assert cbCustomer != null : "fx:id=\"cbCustomer\" was not injected: check your FXML file 'booking.fxml'.";
        assert cbTripType != null : "fx:id=\"cbTripType\" was not injected: check your FXML file 'booking.fxml'.";
        assert cbPackage != null : "fx:id=\"cbPackage\" was not injected: check your FXML file 'booking.fxml'.";
        assert dtBookingStart != null : "fx:id=\"dtBookingStart\" was not injected: check your FXML file 'booking.fxml'.";
        assert dtBookingEnd != null : "fx:id=\"dtBookingEnd\" was not injected: check your FXML file 'booking.fxml'.";
        assert chkCustEmails != null : "fx:id=\"chkCustEmails\" was not injected: check your FXML file 'booking.fxml'.";
        assert btnSubmit != null : "fx:id=\"btnSubmit\" was not injected: check your FXML file 'booking.fxml'.";
        assert txtPackageDescription != null : "fx:id=\"txtPackageDescription\" was not injected: check your FXML file 'booking.fxml'.";
        assert txtTripType != null : "fx:id=\"txtTripType\" was not injected: check your FXML file 'booking.fxml'.";
        assert txtPackageName != null : "fx:id=\"txtPackageName\" was not injected: check your FXML file 'booking.fxml'.";
        assert txtPackagePrice != null : "fx:id=\"txtPackagePrice\" was not injected: check your FXML file 'booking.fxml'.";
        assert lblCustomer != null : "fx:id=\"lblCustomer\" was not injected: check your FXML file 'booking.fxml'.";
        assert txtItineraryNo != null : "fx:id=\"txtItineraryNo\" was not injected: check your FXML file 'booking.fxml'.";
        assert txtDestination != null : "fx:id=\"txtDestination\" was not injected: check your FXML file 'booking.fxml'.";
        assert txtAlert != null : "fx:id=\"txtAlert\" was not injected: check your FXML file 'booking.fxml'.";

        txtAlert.setText("");
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

        cbTripType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TripType>() {
            @Override
            public void changed(ObservableValue<? extends TripType> observableValue, TripType triptype, TripType t1) {
                txtTripType.setText(t1.getTripTypeName());
            }
        });

        cbPackage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TravelPackage>() {
            @Override
            public void changed(ObservableValue<? extends TravelPackage> observableValue, TravelPackage travelpackage, TravelPackage t1) {
                txtPackageName.setText(t1.getPkgName());
                txtPackagePrice.setText(t1.getPkgBasePrice() + "");
                txtPackageDescription.setText(t1.getPkgDesc());
            }
        });


        btnSubmit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (custID > 0)
                {
                    long date = System.currentTimeMillis();
                    java.sql.Date today = new java.sql.Date(date);

                    ObservableList<Booking> bookingList = FXCollections.observableArrayList();
                    Connection conn = connectDB();
                    String sql = "INSERT INTO `bookings` SET `BookingDate`=?, `CustomerId`=?, `TripTypeId`=?, `PackageId`=?";

                    if (!cbTripType.getSelectionModel().isEmpty() && !cbPackage.getSelectionModel().isEmpty() && dtBookingStart.getValue() != null && dtBookingEnd.getValue() != null && !txtDestination.getText().equals("") && !txtItineraryNo.getText().equals(""))
                    {
                        txtAlert.setText("");
                        try {
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setDate(1, today); // todays date formatted to sql
                            stmt.setInt(2, custID);
                            stmt.setString(3, cbTripType.getValue().getTripTypeId());
                            stmt.setInt(4, cbPackage.getValue().getPackageId());
                            int numRows = stmt.executeUpdate();

                            java.sql.Statement bookingStmt = conn.createStatement();
                            String bookingQuery = "select * from `bookings`";
                            ResultSet brs = bookingStmt.executeQuery(bookingQuery);
//                    int column_index = brs.findColumn("BookingId");
//                    System.out.println(column_index);
                            while (brs.next())
                            {
                                booking = new Booking(brs.getInt(1), brs.getDate(2), brs.getString(3), brs.getInt(4), brs.getInt(5), brs.getString(6), brs.getInt(7));
                                bookingId = booking.getBookingId();
                            }

                            if (numRows == 0)
                            {
                                System.out.println("failed");
                            }
                            else
                            {
                                System.out.println("Updated Successfully");
                                System.out.println(bookingId);
//                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/SearchView.fxml"));
//                        Parent root = (Parent) loader.load();
//                        mainPane.getChildren().setAll(root);
//                        BookingController bookingController = loader.getController();
                            }

                        } catch (SQLException throwables) { //  | IOException
                            throwables.printStackTrace();
                        }

                        txtAlert.setText("");
                        LocalDate bookingStartRaw = LocalDate.from(dtBookingStart.getValue());
                        LocalDate bookingEndRaw = LocalDate.from(dtBookingEnd.getValue());
                        java.sql.Date bookingStart = Date.valueOf(bookingStartRaw);
                        java.sql.Date bookingEnd = Date.valueOf(bookingEndRaw);
                        String sqlbd = "INSERT INTO `bookingdetails` SET `ItineraryNo`=?, `TripStart`=?, `TripEnd`=?, `Description`=?, `Destination`=?," +
                                "`BasePrice`=?, `BookingId`=? ";
                        try {
                            PreparedStatement stmtbd = conn.prepareStatement(sqlbd);
                            stmtbd.setDouble(1, Double.parseDouble(txtItineraryNo.getText()));
                            stmtbd.setDate(2, bookingStart);
                            stmtbd.setDate(3, bookingEnd);
                            stmtbd.setString(4, txtPackageDescription.getText());
                            stmtbd.setString(5, txtDestination.getText());
                            stmtbd.setDouble(6, Double.parseDouble(txtPackagePrice.getText()));
                            stmtbd.setInt(7, bookingId);
                            int numRowsbd = stmtbd.executeUpdate();
                            if (numRowsbd == 0)
                            {
                                System.out.println("failed");
                            }
                            else
                            {
                                f = new JFrame();
                                JOptionPane.showMessageDialog(f, "Booking Added Successfully");

                                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/SearchView.fxml"));
                                Parent root = (Parent) loader.load();
                                pageBookings.getChildren().setAll(root);
                            }
                            conn.close();
                        } catch (SQLException  | IOException throwables) { //
                            throwables.printStackTrace();
                        }

                    } else {
                        txtAlert.setText("Please fill out all fields");
                    }
                }
                else {
                    txtAlert.setText("ERROR: No customers have been loaded");
                }


            }
        });
    }

    private Connection connectDB() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "TEAdmin", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    // From Ehsan to Jessy: we've discussed the customer info one for adding, the new method is for editing. from
    // BookingDetail model you can extract bookingId and use it for a query or however you think is best to do the edit!
    // <3
    public void SetCustomerInfo(Customer c){
        String CustomerName = c.getCustFirstName() + " " + c.getCustLastName();
        int customerId = c.getCustomerId();
        lblCustomer.setText(CustomerName);
        custID = customerId;
    }
}
