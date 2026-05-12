package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TherapyProgramController {

    @FXML
    private TableColumn editCol;

    @FXML
    private ComboBox programComboBox;

    @FXML
    private TextField programCostField;

    @FXML
    private TextField programDescriptionField;

    @FXML
    private TextField programDurationField;

    @FXML
    private TableColumn programIdCol;

    @FXML
    private TextField programIdField;

    @FXML
    private TableColumn programNameCol;

    @FXML
    private TextField programNameField;

    @FXML
    private TableView programTbl;

    @FXML
    private ComboBox therapistIdComboBox;

    @FXML
    private TextField therapistIdField;

    @FXML
    private TableColumn therapistNameCol;

    @FXML
    private TableView therapistProgramTbl;
}
