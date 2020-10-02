package controller;

import Validator.Validator;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BookingDetail;
import model.Customer;

public class SearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfPostal;

    @FXML
    private TableView<BookingDetail> tvBookings;

    @FXML
    private TableColumn<BookingDetail, Integer> tcItineraryNo;

    @FXML
    private TableColumn<BookingDetail, String> tcDestination;

    @FXML
    private TableColumn<BookingDetail, Date> tcTripStart;

    @FXML
    private TableColumn<BookingDetail, Date> tcTripEnd;

    @FXML
    private TableColumn<BookingDetail, String> tcDescription;

    @FXML
    private TableColumn<BookingDetail, Double> tcBasePrice;

    @FXML
    private TableColumn<BookingDetail, Integer> tcBookingId;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnBookAdd;

    @FXML
    private Button btnBookEdit;

    @FXML
    void initialize() {
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfFirstName != null : "fx:id=\"tfFirstName\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfLastName != null : "fx:id=\"tfLastName\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfPhone != null : "fx:id=\"tfPhone\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfCity != null : "fx:id=\"tfCity\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfCountry != null : "fx:id=\"tfCountry\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tfPostal != null : "fx:id=\"tfPostal\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tvBookings != null : "fx:id=\"tvBookings\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnBookAdd != null : "fx:id=\"btnBookAdd\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert btnBookEdit != null : "fx:id=\"btnBookEdit\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tcItineraryNo != null : "fx:id=\"tcItineraryNo\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tcDestination != null : "fx:id=\"tcDestination\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tcTripStart != null : "fx:id=\"tcTripStart\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tcTripEnd != null : "fx:id=\"tcTripEnd\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tcDescription != null : "fx:id=\"tcDescription\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert tcBasePrice != null : "fx:id=\"tcBasePrice\" was not injected: check your FXML file 'SearchView.fxml'.";


        Connection conn = connectDB();
        final Customer[] customer = new Customer[1];
        ObservableList<BookingDetail> bookingList = FXCollections.observableArrayList();
        final BookingDetail[] bd = new BookingDetail[1];


        btnSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String sql = null;
                if (!tfEmail.getText().isEmpty() || !tfPhone.getText().isEmpty()) {
                    if (tfEmail.getText().isEmpty() && !tfPhone.getText().isEmpty()
                            && Validator.isValidPhone(tfPhone, "tfPhone", "Invaid Phone Number!")) {
                        String phone = tfPhone.getText();
                        System.out.println(phone);
                        sql = "Select * from customers Where custHomePhone= '" + phone + "' OR custBusPhone= '" + phone + "'";
                    }
                    if (!tfEmail.getText().isEmpty() && tfPhone.getText().isEmpty()
                            && Validator.isValidEmail(tfEmail, "tfEmail", "Invaid Email!")) {
                        String email = tfEmail.getText();
                        System.out.println(email);
                        sql = "Select * from customers Where CustEmail= '" + email + "'";
                    }

                    if (sql != null)
                    {
                        try {

                            Statement statement = conn.createStatement();
                            ResultSet rs = statement.executeQuery(sql);
                            rs.first();
                            customer[0] = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),
                                    rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
                                    ,rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));

                            tfEmail.setText((customer[0].getCustEmail()));
                            tfPhone.setText(customer[0].getCustHomePhone());
                            tfFirstName.setText(customer[0].getCustFirstName());
                            tfLastName.setText(customer[0].getCustLastName());
                            tfCity.setText(customer[0].getCustCity());
                            tfAddress.setText(customer[0].getCustAddress());
                            tfCountry.setText(customer[0].getCustCountry());
                            tfPostal.setText(customer[0].getCustPostal());

                            if (tfEmail.getText().isEmpty()){
                                tfEmail.setDisable(true);
                            }

                            int customerId = customer[0].getCustomerId();
                            System.out.println(customerId);
                            String sqlBooking = "SELECT ItineraryNo, Destination, TripStart, TripEnd, Description, " +
                                    "BasePrice, bookings.bookingId FROM `bookingdetails` JOIN bookings on bookingdetails.BookingId" +
                                    " = bookings.BookingId WHERE bookings.CustomerId = " + customerId;
                            Statement statement1 = conn.createStatement();
                            ResultSet rs1 = statement1.executeQuery(sqlBooking);


                            tcItineraryNo.setCellValueFactory(new PropertyValueFactory<BookingDetail, Integer>("itineraryNo"));
                            tcDestination.setCellValueFactory(new PropertyValueFactory<BookingDetail, String>("destination"));
                            tcTripStart.setCellValueFactory(new PropertyValueFactory<BookingDetail, Date>("tripStart"));
                            tcTripEnd.setCellValueFactory(new PropertyValueFactory<BookingDetail, Date>("tripEnd"));
                            tcDescription.setCellValueFactory(new PropertyValueFactory<BookingDetail, String>("description"));
                            tcBasePrice.setCellValueFactory(new PropertyValueFactory<BookingDetail, Double>("basePrice"));
                            tcBookingId.setCellValueFactory(new PropertyValueFactory<BookingDetail, Integer>("bookingId"));

                            while (rs1.next()){
                                bookingList.add(new BookingDetail(rs1.getInt(1),rs1.getString(2),rs1.getDate(3),
                                        rs1.getDate(4),rs1.getString(5),rs1.getDouble(6),rs1.getInt(7)));
                            }

                            tvBookings.setItems(bookingList);
                            btnEdit.setDisable(false);
                            btnReset.setDisable(false);
                            btnBookAdd.setDisable(false);

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText("Search Input Error!");
                    alert.setContentText("Please input a valid Email or Phone Number!");
                    alert.showAndWait();
                    tfEmail.isFocused();
                }
            }
        });

        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/AddCustomer.fxml"), resources);
                    Parent mainLoader = (Parent) loader.load();
                    stage.setTitle("Edit Customer");
                    stage.setScene(new Scene(mainLoader, 800, 600));
                    AddCustomerController addCustomerController = loader.getController();
                    addCustomerController.GetCustomerInfo(customer[0]);

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnReset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ClearFields();
            }
        });

        btnBookAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/booking.fxml"), resources);
                    Parent mainLoader = (Parent) loader.load();
                    stage.setTitle("Add a Booking!");
                    stage.setScene(new Scene(mainLoader, 800, 600));
                    BookingController bookingController = loader.getController();
                    bookingController.SetCustomerInfo(customer[0]);

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        tvBookings.getSelectionModel().selectedItemProperty().addListener((observableValue, bookingDetail, t1) -> {
            btnBookEdit.setDisable(false);
            bd[0] = t1;
        });
        
        btnBookEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/booking.fxml"), resources);
                    Parent mainLoader = (Parent) loader.load();
                    stage.setTitle("Edit Booking!");
                    stage.setScene(new Scene(mainLoader, 800, 600));
                    BookingController bookingController = loader.getController();
                    bookingController.GetBookingDetailInfo(bd[0]);

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void ClearFields() {
        tfEmail.setText("");
        tfPhone.setText("");
        tfFirstName.setText("");
        tfLastName.setText("");
        tfPostal.setText("");
        tfCountry.setText("");
        tfAddress.setText("");
        tfCity.setText("");
        tvBookings.setItems(null);
        btnEdit.setDisable(true);
        btnBookAdd.setDisable(true);
        btnBookEdit.setDisable(true);
        btnReset.setDisable(true);
        tfEmail.setDisable(false);
    }


    private Connection connectDB(){
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","TEAdmin","password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }


}