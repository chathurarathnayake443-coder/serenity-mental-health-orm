package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TableColumn addressCol;

    @FXML
    private TextField addressField;

    @FXML
    private TableColumn contactCol;

    @FXML
    private TextField contactField;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn passwordCol;

    @FXML
    private TextField passwordField;

    @FXML
    private TableColumn roleCol;

    @FXML
    private TextField roleField;

    @FXML
    private TextField userNameField;

    @FXML
    private TableView userTbl;

    @FXML
    private TableColumn usernameCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("User View Loaded");
//
//        programIdCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));
//        programTblNameCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramName"));
//
//        programTbl.setOnMouseClicked(event -> {
//            Object object = programTbl.getSelectionModel().getSelectedItem();
//            TherapyProgramDTO selected = (TherapyProgramDTO)object;
//            if (selected != null) {
//                programIdField.setText(selected.getTherapyProgramId());
//                programNameField.setText(selected.getTherapyProgramName());
//                programCostField.setText(String.valueOf(selected.getTherapyProgramCost()));
//                programDurationField.setText(String.valueOf(selected.getTherapyProgramDuration()));
//                programDescriptionField.setText(selected.getTherapyProgramDescription());
//            }
//        });

        //loadTherapyProgramTable();

    }
}
