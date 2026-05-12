package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class TherapyProgramController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Therapist View Loaded");

//        therapistIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
//        therapistNameCol.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
//
//        therapistTbl.setOnMouseClicked(event -> {
//            Object object = therapistTbl.getSelectionModel().getSelectedItem();
//            TherapistDTO selected = (TherapistDTO)object;
//            if (selected != null) {
//                therapistIdField.setText(String.valueOf(selected.getTherapistId()));
//                therapistNameField.setText(selected.getTherapistName());
//                therapistEmailField.setText(selected.getTherapistEmail());
//                therapistPhoneField.setText(selected.getTherapistPhone());
//                therapistAddressField.setText(selected.getTherapistAddress());
//            }
       // });

        //loadTherapistTable();
        //showNextId();

    }

    @FXML
    private void clickSaveButton(){
        try{
            String therapyProgramId = programIdField.getText();
            String therapyProgramName = programNameField.getText();
            String therapyProgramDescription = programDescriptionField.getText();
            String therapyProgramDuration = programDurationField.getText();
            double therapyProgramCost = Double.parseDouble(programCostField.getText());


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
