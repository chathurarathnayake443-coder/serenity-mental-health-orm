package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TherapistController {

    @FXML
    private TableColumn datCol;

    @FXML
    private TableColumn programCol;

    @FXML
    private TableColumn sessionIdCol;

    @FXML
    private TextField therapistAddressField;

    @FXML
    private TextField therapistEmailField;

    @FXML
    private TableColumn therapistIdCol;

    @FXML
    private TextField therapistIdField;

    @FXML
    private TableColumn therapistNameCol;

    @FXML
    private TextField therapistNameField;

    @FXML
    private TextField therapistPhoneField;

    @FXML
    private Button therapistSaveBtn;

    @FXML
    private TableView therapistScheduleTbl;

    @FXML
    private TableView therapistTbl;

    @FXML
    private TableColumn timeCol;
}
