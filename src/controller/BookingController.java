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


public class BookingController {
    public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
//    Booking booking;

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
    private TextField hiddenCustId;

    @FXML
    private TextField hiddenBookingId;

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
        assert hiddenCustId != null : "fx:id=\"hiddenCustId\" was not injected: check your FXML file 'booking.fxml'.";
        assert hiddenBookingId != null : "fx:id=\"hiddenBookingId\" was not injected: check your FXML file 'booking.fxml'.";
        int bookingId;
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

                long date = System.currentTimeMillis();
                java.sql.Date today = new java.sql.Date(date);

                LocalDate bookingStartRaw = LocalDate.from(dtBookingStart.getValue());
                java.sql.Date bookingStart = Date.valueOf(bookingStartRaw);

                LocalDate bookingEndRaw = LocalDate.from(dtBookingEnd.getValue());
                java.sql.Date bookingEnd = Date.valueOf(bookingEndRaw);

                ObservableList<Booking> bookingList = FXCollections.observableArrayList();
                int custId = Integer.parseInt(hiddenCustId.getText());
                int customerId = custId;
                Connection conn = connectDB();
                String sql = "INSERT INTO `bookings` SET `BookingDate`=?, `CustomerId`=?, `TripTypeId`=?, `PackageId`=?";

//                        "SELECT `bookings.BookingId` FROM `bookings` JOIN bookingdetails on bookings.BookingId" +
//                        " = bookingdetails.BookingId WHERE bookingdetails.BookingId = bookings.BookingId";
                //NOT WORKING FOR SOME REASON^^(maybe bookingId?)

                try {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setDate(1, today); // todays date formatted to sql
                    stmt.setInt(2, Integer.parseInt(hiddenCustId.getText())); // customerId pulled from a hidden field in the app.
                    stmt.setString(3, cbTripType.getValue().getTripTypeId());
                    stmt.setInt(4, cbPackage.getValue().getPackageId());
//                    stmtbd.setInt(6, Integer.parseInt(hiddenBookingId.getText()));
                    int numRows = stmt.executeUpdate();

                    java.sql.Statement bookingStmt = conn.createStatement();
                    String bookingQuery = "select `BookingId` from `bookings`";
                    ResultSet brs = bookingStmt.executeQuery(bookingQuery);
//                    int column_index = brs.findColumn("BookingId");
//                    System.out.println(column_index);
                    while (brs.next())
                    {
                        bookingList.add(new Booking(brs.getInt(1), brs.getDate(null), brs.getString(null), brs.getInt(null), brs.getInt(null), brs.getString(null), brs.getInt(null)));
                    }
//                    bookingId = bookingList;

                    if (numRows == 0)
                    {
                        System.out.println("failed");
                    }
                    else
                    {
                        System.out.println("Updated Successfully");
                        System.out.println(bookingList);
//                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/SearchView.fxml"));
//                        Parent root = (Parent) loader.load();
//                        mainPane.getChildren().setAll(root);
//                        BookingController bookingController = loader.getController();
                    }
                    conn.close();
                } catch (SQLException throwables) { //  | IOException
                    throwables.printStackTrace();
                }

//                Connection conn2 = connectDB();
//                String sqlbd = "INSERT INTO `bookingdetails` SET `ItineraryNo`=?, `TripStart`=?, `TripEnd`=?, `Description`=?, `Destination`=?," +
//                        "`BasePrice`=?, `BookingId` ";
//                try {
//
//
//
//                    PreparedStatement stmtbd = conn.prepareStatement(sqlbd);
//
//                    stmtbd.setDouble(1, Double.parseDouble(txtItineraryNo.getText()));
//                    stmtbd.setDate(2, bookingStart);
//                    stmtbd.setDate(3, bookingEnd);
//                    stmtbd.setString(4, txtPackageDescription.getText());
//                    stmtbd.setString(5, txtDestination.getText());
//                    stmtbd.setDouble(6, Double.parseDouble(txtPackagePrice.getText()));
////                    stmtbd.setInt(6, Integer.parseInt(hiddenBookingId.getText()));
//                    int numRowsbd = stmtbd.executeUpdate();
//                    if (numRowsbd == 0)
//                    {
//                        System.out.println("failed");
//                    }
//                    else
//                    {
//                        System.out.println("Updated Successfully");
////                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/SearchView.fxml"));
////                        Parent root = (Parent) loader.load();
////                        mainPane.getChildren().setAll(root);
////                        BookingController bookingController = loader.getController();
//                    }
//                    conn.close();
//                } catch (SQLException throwables) { //  | IOException
//                    throwables.printStackTrace();
//                }
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
        hiddenCustId.setText(String.valueOf(customerId));
    }

    public void GetBookingDetailInfo(BookingDetail bd){
    }

//    public void getBookingInfo(Booking booking) {
//        int bookingId = booking.getBookingId();
//        hiddenBookingId.setText(String.valueOf(bookingId));
//    }

//    public void getCustomerInfo(Customer customer){
//        int customerId = customer.getCustomerId();
////        int custID = Integer.parseInt(lblCustomer.getId());
//        hiddenCustId.setText(String.valueOf(customerId));
//        System.out.println("customerID: " + customerId);
//    }
}
