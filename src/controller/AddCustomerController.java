package controller;

import Validator.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCustomerController {
    public boolean isSavePage = true;
    private boolean testToken;
    int custID = 0; //CHANGE THIS TO THE CUSTOMER YOU WANT TO EDIT -- NOT YET WORKING <3
//    public AddCustomerController(boolean testToken) {
//        this.testToken = testToken;
//    }
//
//    public boolean isTestToken() {
//        return testToken;
//    }
//
//    public void setTestToken(boolean testToken) {
//        this.testToken = testToken;
//    }

    @FXML
    private Label lbl_Title;

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
    private ComboBox<String> cb_CustProvince;

    @FXML
    private ComboBox<String> cb_CustCountry;

    @FXML
    private Button btn_AddCustomerRefresh;

    @FXML
    private Button btn_AddCustomerSave;

    @FXML
    private Button btn_AddCustomerCancel;

    @FXML
    void initialize() {
        assert lbl_Title != null : "fx:id=\"lbl_Title\" was not injected: check your FXML file 'AddCustomer.fxml'.";
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

        // -------------------- CASING ------- NEED TO FIX
                // Methods should be PascalCase
                // Variables should be camelCase
                // Constants are all UPPERCASE



        //boolean isSavePage = true; // TRUE = ADD CUSTOMER PAGE, (FALSE = EDIT PAGE)
        //boolean isSavePage = true   - like a switch for add and edit page, DEFAULT Add page ,
        //ehsans page will send a token that changes this to true, rendering it an edit page
        //throughout this code anything that specifically applies to the save page will be wrapped
        //in a conditional statement with that boolean value to check for true (edit page)
//        if(testToken){
//            isSavePage = true;
//        }

        if(isSavePage == false){
            btn_AddCustomerRefresh.setVisible(false);
            lbl_Title.setText("Edit Customer");
        }

        pnl_CustHomePhone.setVisible(false);
        disableFields();
        ////-------------------------------------------PROVINCE DROP DOWN-----------------------------------------////

        ArrayList<String> province = new ArrayList<>();                //Instantiate new list to hold country names
        province.add("BC");                                            //populate list with province codes
        province.add("AB");
        province.add("SK");
        province.add("MB");
        province.add("ON");
        province.add("QC");
        province.add("NS");
        province.add("NB");
        province.add("PE");
        province.add("NL");
        province.add("NT");
        province.add("NU");
        province.add("YT");

        ObservableList<String> provinceList = FXCollections.observableArrayList(province);
        cb_CustProvince.setItems(provinceList);

        cb_CustProvince.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String p = cb_CustProvince.getSelectionModel().getSelectedItem();
            }
        });

        ////-------------------------------------COUNTRY DROP DOWN----------------------------------------------////
        ArrayList<String> country = new ArrayList<>();
        country.add("Canada");
        ObservableList<String> countryList = FXCollections.observableArrayList(country);
        cb_CustCountry.setItems(countryList);

        cb_CustCountry.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String c = cb_CustCountry.getSelectionModel().getSelectedItem();
            }
        });

        ////-->>>>>>>>>>>>>>>>>>>>>>>>>>>----FOCUS EVENT LISTENER + VALIDATION-<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<--////

        ///-------------CUSTOMER EMAIL-------------///
        txt_CustEmail.focusedProperty().addListener((ov, oldV, newV) -> {
        if (!isSavePage){ //if this page was loaded as EDIT PAGE
            //check if valid email address
            if (Validator.isValidEmailNoAlert(txt_CustEmail)) {                  //if it is valid do this stuff
                if (!txt_CustEmail.getText().isEmpty() && !txt_CustBusPhone.getText().isEmpty()) {
                    //if the fields are both filled, and subsequently both do not belong to other customers, then enable fields
                    enableFields();
                }
                txt_CustEmail.setStyle("-fx-border-color: null");
            }else if(!Validator.isValidEmailNoAlert(txt_CustEmail)) {           //if its not valid, lets indicate that to the user
            txt_CustEmail.focusedProperty();
            txt_CustEmail.setStyle("-fx-border-color: red");
            }
        }else{ //else isEditPage returns FALSE
        //check if valid email address
        if (Validator.isValidEmailNoAlert(txt_CustEmail)) {                  //if it is valid do this stuff
            boolean checkExists = custEmailExists();
            if (checkExists) {
//                    if the customer exists, show the agent a message
                   JOptionPane.showMessageDialog(null, "Customer Email Exists, do not add new customer",   //------ TESTING ONLY!!!!!!!!! REPLACE WITH REDIRECT ----------/
                           //change the token
                        "Warning", JOptionPane.CLOSED_OPTION);
                     } else {
                     //if it DOESNT, then we want to allow the addition of this customer
                    //FIRST check if cust email and cust phone are both not null4
                    txt_CustEmail.setStyle("-fx-border-color: null");

                    if (!txt_CustEmail.getText().isEmpty() && !txt_CustBusPhone.getText().isEmpty()) {
                         //if the fields are both filled, and subsequently both do not belong to other customers, then enable fields
                        enableFields();
                    }
                }
            } else if(!Validator.isValidEmailNoAlert(txt_CustEmail)) {           //if its not valid, lets indicate that to the user
                txt_CustEmail.focusedProperty();
                txt_CustEmail.setStyle("-fx-border-color: red");
            }
         }
      });

        ///-------------CUSTOMER BUSINESS PHONE-------------///
        txt_CustBusPhone.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!isSavePage){ //if this page was loaded as EDIT PAGE
                //check if valid phone using validator
                if (Validator.isValidPhoneNoAlert(txt_CustBusPhone)) {                  //if it is valid do this stuff
                    if (!txt_CustBusPhone.getText().isEmpty() && !txt_CustBusPhone.getText().isEmpty()) {
                        //if the fields are both filled, and subsequently both do not belong to other customers, then enable fields
                        enableFields();
                    }
                    txt_CustBusPhone.setStyle("-fx-border-color: null");
                }else if(!Validator.isValidEmailNoAlert(txt_CustBusPhone)) {           //if its not valid, lets indicate that to the user
                    txt_CustBusPhone.focusedProperty();
                    txt_CustBusPhone.setStyle("-fx-border-color: red");
                }
            }else{ //else isEditPage returns FALSE
                //check if valid phone using validator
                if (Validator.isValidPhoneNoAlert(txt_CustBusPhone)) {                  //if it is valid do this stuff
                    boolean checkExists = custPhoneExists();
                    if (checkExists) {
//                    if the customer exists, show the agent a message
                        JOptionPane.showMessageDialog(null, "Customer with this phone exists, do not add new customer",   //------ TESTING ONLY!!!!!!!!! REPLACE WITH REDIRECT ----------//
                                "Warning", JOptionPane.CLOSED_OPTION);
                    } else {
                        //if it DOESNT, then we want to allow the addition of this customer
                        //FIRST check if cust email and cust phone are both not null4
                        txt_CustBusPhone.setStyle("-fx-border-color: null");

                        if (!txt_CustBusPhone.getText().isEmpty() && !txt_CustBusPhone.getText().isEmpty()) {
                            //if the fields are both filled, and subsequently both do not belong to other customers, then enable fields
                            enableFields();
                        }
                    }
                } else if(!Validator.isValidPhoneNoAlert(txt_CustBusPhone)) {           //if its not valid, lets indicate that to the user
                    txt_CustBusPhone.focusedProperty();
                    txt_CustBusPhone.setStyle("-fx-border-color: red");
                }
            }
        });

        ///-------------CUSTOMER HOME PHONE-------------///
        txt_CustHomePhone.focusedProperty().addListener((ov, oldV, newV) -> {
            if (Validator.isValidPhoneNoAlert(txt_CustHomePhone)) {
                txt_CustHomePhone.setStyle("-fx-border-color: null");
            }
            else {
                txt_CustHomePhone.setStyle("-fx-border-color: red");
            }
        });

        ///-------------CUSTOMER FIRST NAME-------------///
        txt_CustFName.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV){
                if (!txt_CustFName.getText().isEmpty()){
                    txt_CustFName.setStyle("-fx-border-color: null");
                }
                else {
                    txt_CustFName.setStyle("-fx-border-color: red");
                }
            }
                });

        ///-------------CUSTOMER FIRST NAME-------------///
        txt_CustLName.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV){
                if (!txt_CustLName.getText().isEmpty()){
                    txt_CustLName.setStyle("-fx-border-color: null");
                }
                else {
                    txt_CustLName.setStyle("-fx-border-color: red");
                }
            }
        });

        ///-------------CUSTOMER ADDRESS-------------///
        txt_CustAddress.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV){
                if (!txt_CustAddress.getText().isEmpty() ){
                    txt_CustAddress.setStyle("-fx-border-color: null");
                }
                else {
                    txt_CustAddress.setStyle("-fx-border-color: red");
                }
            }
        });

        ///-------------CUSTOMER POSTAL-------------///
        txt_CustPostal.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV){
                if (!txt_CustPostal.getText().isEmpty() && Validator.isValidPostalCodeNoAlert(txt_CustPostal)){
                    txt_CustPostal.setStyle("-fx-border-color: null");
                }
                else{
                    txt_CustPostal.setStyle("-fx-border-color: red");
                }
            }
        });

        ///-------------CUSTOMER CITY-------------///
        txt_CustCity.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV){
                if (!txt_CustCity.getText().isEmpty()){
                    txt_CustCity.setStyle("-fx-border-color: null");
                }
                else {
                    txt_CustCity.setStyle("-fx-border-color: red");
                }
            }
        });




        ////-------------------------------------- ADD HOME PHONE BUTTON ------------------------------------------------////

        btn_CustAddPhone.setOnMouseClicked(new EventHandler<MouseEvent>() {//when the user click the [+] button,
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!pnl_CustHomePhone.isVisible()) {                //IF the home phone panel is not visible
                    pnl_CustHomePhone.setVisible(true);                     //Make the home phone panel visible
                    btn_CustAddPhone.setText("-");                          //Also, Change the button to a 'minimize' symbol

                } else if (pnl_CustHomePhone.isVisible()) {          //ELSE IF,  the home phone panel is visible,
                    //then we need to check another condition,

                    if (txt_CustHomePhone.getText().isEmpty()) {      //IF the home phone text box is empty
                        pnl_CustHomePhone.setVisible(false);                //then clicking the button when the panel is visible, should make it invisible
                        btn_CustAddPhone.setText("+");                      //and also restore the ' + ' sign as the text for the button

                    } else if (!txt_CustHomePhone.getText().isEmpty()) {                                      //ELSE IF , the text box is not empty
                        JOptionPane.showMessageDialog(null, "Clear Home Phone to minimize",   //present the user with a message box
                                "Home Phone : Warning", JOptionPane.CLOSED_OPTION);                              //requiring the field to be empty to minimize
                    }
                }
            }
        });

        ////-----------------------------------------------REFRESH BUTTON---------------------------------------------------////

        btn_AddCustomerRefresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearFields();
                disableFields();

            }
        });

        ////--------------------------------------------------CANCEL BUTTON--------------------------------------------------////
        btn_AddCustomerCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Stage stage = (Stage) btn_AddCustomerCancel.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Main.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = new Stage();
                    stage.setScene((new Scene(root1)));
                    stage.show();
                    JOptionPane.showMessageDialog(null, "Redirecting to (view name) page",
                            "Redirecting : Warning", JOptionPane.CLOSED_OPTION);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        ////--------------------------------------------------SAVE BUTTON-----------------------------------------------////

        btn_AddCustomerSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!isSavePage){ //if this page was loaded as EDIT PAGE  // IDEA - if the customer exi
                    //if all fields are filled
                    if (allArePresent() && IsValidData()) {
                        //establish connection to insert new customer into DB
                        Connection conn = connectDB();
                        //SQL string for insertion
                        String sql = "UPDATE `customers` SET `CustFirstName`=?, `CustLastName`=?, `CustAddress`=?, `CustCity`=?, `CustProv`=?, " +
                                "`CustPostal`=?, `CustCountry`=?, `CustHomePhone`=?, `CustBusPhone`=?, `CustEmail`=? WHERE `CustomerId` = ?"; //should be the cust ID from ehsans search view
                        try {
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, txt_CustFName.getText());         //assigns the text values from the form to the sql query
                            stmt.setString(2, txt_CustLName.getText());
                            stmt.setString(3, txt_CustAddress.getText());
                            stmt.setString(4, txt_CustCity.getText());
                            stmt.setString(5, cb_CustProvince.getValue());
                            stmt.setString(6, txt_CustPostal.getText());
                            stmt.setString(7, cb_CustCountry.getValue());
                            stmt.setString(8, txt_CustHomePhone.getText());
                            stmt.setString(9, txt_CustBusPhone.getText());
                            stmt.setString(10, txt_CustEmail.getText());
                            stmt.setInt(11, custID);

                            int numRows = stmt.executeUpdate();                             //this is to check if the insert was successfull
                            if (numRows == 0) {
                                System.out.println("save was unsuccessfull");
                            } else {
                                System.out.println("save was successful! WOOHOO");
                            }
                            conn.close();
                            try {
                                Stage stage = (Stage) btn_AddCustomerSave.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/booking.fxml"));
                                Parent root1 = (Parent) fxmlLoader.load();
                                stage = new Stage();
                                stage.setScene((new Scene(root1)));
                                stage.show();
                                JOptionPane.showMessageDialog(null, "Redirecting to (view name) page",
                                        "Redirecting : Warning", JOptionPane.CLOSED_OPTION);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Missing field data or incorrect format, please try again.",
                                "Warning", JOptionPane.CLOSED_OPTION);
                    }
                }else{
                    //if all fields are filled
                    if (allArePresent() && IsValidData()) {

                        //establish connection to insert new customer into DB
                        Connection conn = connectDB();
                        //SQL string for insertion
                        String sql = "INSERT INTO `customers` SET `CustFirstName`=?, `CustLastName`=?, `CustAddress`=?, `CustCity`=?, `CustProv`=?, `CustPostal`=?, `CustCountry`=?, `CustHomePhone`=?, `CustBusPhone`=?, `CustEmail`=?";
                        try {
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, txt_CustFName.getText());         //assigns the text values from the form to the sql query
                            stmt.setString(2, txt_CustLName.getText());
                            stmt.setString(3, txt_CustAddress.getText());
                            stmt.setString(4, txt_CustCity.getText());
                            stmt.setString(5, cb_CustProvince.getValue());
                            stmt.setString(6, txt_CustPostal.getText());
                            stmt.setString(7, cb_CustCountry.getValue());
                            stmt.setString(8, txt_CustHomePhone.getText());
                            stmt.setString(9, txt_CustBusPhone.getText());
                            stmt.setString(10, txt_CustEmail.getText());

                            int numRows = stmt.executeUpdate();                             //this is to check if the insert was successfull
                            if (numRows == 0) {
                                System.out.println("save was unsuccessfull");
                            } else {
                                System.out.println("save was successful! WOOHOO");
                            }
                            conn.close();
                            try {
                                Stage stage = (Stage) btn_AddCustomerSave.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/booking.fxml"));
                                Parent root1 = (Parent) fxmlLoader.load();
                                stage = new Stage();
                                stage.setScene((new Scene(root1)));
                                stage.show();
                                JOptionPane.showMessageDialog(null, "Redirecting to (view name) page",
                                        "Redirecting : Warning", JOptionPane.CLOSED_OPTION);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "FILL IN ALL FIELDS!!",
                                "Warning", JOptionPane.CLOSED_OPTION);
                    }
                }
            }
        });

    } //ends initialize(), all 'stand alone' methods go below this!

    private Connection connectDB() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "TEAdmin", "password");
        } catch (ClassNotFoundException |
                SQLException e) {
            e.printStackTrace();
            System.out.println("DB connection failed");
        }
        return c;
    }

    public void clearFields() {
        txt_CustFName.setText("");
        txt_CustLName.setText("");
        txt_CustBusPhone.setText("");
        txt_CustHomePhone.setText("");
        txt_CustEmail.setText("");
        txt_CustAddress.setText("");
        txt_CustPostal.setText("");
        txt_CustCity.setText("");
        pnl_CustHomePhone.setVisible(false);
    }

    public void disableFields() {
        txt_CustFName.setDisable(true);
        txt_CustLName.setDisable(true);
        txt_CustAddress.setDisable(true);
        txt_CustCity.setDisable(true);
        txt_CustPostal.setDisable(true);
        cb_CustCountry.setDisable(true);
        cb_CustProvince.setDisable(true);
        btn_CustAddPhone.setDisable(true);
    }

    public void enableFields() {
        txt_CustFName.setDisable(false);
        txt_CustLName.setDisable(false);
        txt_CustAddress.setDisable(false);
        txt_CustCity.setDisable(false);
        txt_CustPostal.setDisable(false);
        cb_CustCountry.setDisable(false);
        cb_CustProvince.setDisable(false);
        btn_CustAddPhone.setDisable(false);
    }

    ////----------------------------------------Validation Method-----------------------------////
//    public void validateAllFields() {
//        txt_CustFName.getText(); // Letter Values only, no numbers or special characters
//        txt_CustLName.getText(); // Letter values only, no numbers or special characters
//        txt_CustAddress.getText(); // Letter and number values, no special characters?
//        txt_CustCity.getText(); //Letter values only no numbers or special characters
//        txt_CustPostal.getText(); //canadian postal Regex
//    }

    ////------------------------SEARCH / CONFIRM CUSTOMER DOES NOT ALREADY EXIST---------------------------------////
                                                                                        //    CUST EMAIL    //
    public boolean custEmailExists() {
        String email = txt_CustEmail.getText();
        boolean custExists = true;
        Connection conn = connectDB();
        String sql = "SELECT * from customers Where CustEmail= '" + email + "'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            custExists = (rs.next());//if there is data(if there is a customer), rs.next returns TRUE ,
            System.out.println(custExists);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return custExists;

    }
                                                                                            //   CUST PHONE   //
    public boolean custPhoneExists() {
        String phone = txt_CustBusPhone.getText();
        boolean custExists = true;
        Connection conn = connectDB();

        String sql = "SELECT * from customers Where CustBusPhone= '" + phone + "'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            custExists = (rs.next());//if theres data(if there is a customer), rs.next returns TRUE ,
            System.out.println(custExists);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return custExists;

    }


////---------------------------------------VALIDATION------------------------------------------------////

    private boolean IsValidData() {
        return(
                Validator.isValidEmailNoAlert(txt_CustEmail) &&
                Validator.isValidPhoneNoAlert(txt_CustBusPhone) &&
                Validator.isValidPostalCodeNoAlert(txt_CustPostal)&&
                    HomePhoneValidOrEmpty()
        );
    }

    public boolean HomePhoneValidOrEmpty(){

        boolean isValid = true;
        String phone = txt_CustHomePhone.getText();
        String regex = "^((\\(\\d{3}\\))|\\s{3})\\s\\d{3}[-]\\d{4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches() || txt_CustHomePhone.getText().isEmpty()) {
            isValid = false;
        }
        return isValid;

    }

    private boolean allArePresent() { // doesnt include home phone as that one is nullable
        return(
                !txt_CustFName.getText().isEmpty() && !txt_CustLName.getText().isEmpty() && !txt_CustEmail.getText().isEmpty()                      //ADD VALIDATION
                        && !txt_CustBusPhone.getText().isEmpty() && !txt_CustAddress.getText().isEmpty() && !txt_CustCity.getText().isEmpty()
                        && !txt_CustPostal.getText().isEmpty() && !cb_CustProvince.getValue().isEmpty() && !cb_CustCountry.getValue().isEmpty()
        );
    }

    ////----------------------------------------------EDIT FILL FIELDS------------------------------------////
    public void GetCustomerInfo(Customer c){
        txt_CustFName.setText(c.getCustFirstName());
        txt_CustLName.setText(c.getCustLastName());
        txt_CustEmail.setText(c.getCustEmail());
        txt_CustPostal.setText(c.getCustPostal());
        txt_CustAddress.setText(c.getCustAddress());
        txt_CustCity.setText(c.getCustCity());
        txt_CustHomePhone.setText(c.getCustHomePhone());
        txt_CustBusPhone.setText(c.getCustBusPhone());
        cb_CustProvince.setValue(c.getCustProv());
        cb_CustCountry.setValue(c.getCustCountry());
        custID = c.getCustomerId();
    }

    public void EditPage(){
        isSavePage = false;
        initialize();
    }

}//ends controller



//Ehsans page gets a boolean value / method  that holds true or false token for my page
//this method would be called in ehsans edit button click which changes the token to false on click ?
//then this method could be taken and tested in my initialization to use within my page?