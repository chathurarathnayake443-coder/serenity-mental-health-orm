package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TherapySessionController {

    @FXML
    private TableView createSessionTbl;

    @FXML
    private DatePicker dateBox;

    @FXML
    private TextField dateField;

    @FXML
    private Spinner hourTimeBox;

    @FXML
    private Spinner minuteTimeBox;

    @FXML
    private TableView pastSessionTable;

    @FXML
    private ComboBox patientChooser;

    @FXML
    private TableColumn patientCol;

    @FXML
    private TableColumn patientEditCol;

    @FXML
    private TableColumn patientNameCol;

    @FXML
    private ComboBox sessionIdChooser;

    @FXML
    private TableColumn sessionIdCol;

    @FXML
    private TextField sessionIdField;

    @FXML
    private ComboBox therapistChooser;

    @FXML
    private TableColumn therapistCol;

    @FXML
    private TableColumn therapistEditCol;

    @FXML
    private TableColumn therapistNameCol;

    @FXML
    private TextField timeField;
}
