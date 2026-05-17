package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private DatePicker dateBox;

    @FXML
    private TextField feeField;

    @FXML
    private ComboBox nameBox;

    @FXML
    private TableColumn patientCol;

    @FXML
    private TextField patientNameField;

    @FXML
    private TableColumn paymentCol;

    @FXML
    private TableView registerTbl;

    @FXML
    private ComboBox smallNameBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Registration View Loaded");
    }


}
