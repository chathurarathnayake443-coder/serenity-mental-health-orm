package lk.ijse.serenitymentalhealth.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.bo.custom.RegistrationBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private DatePicker dateBox;

    @FXML
    private TextField feeField;

    @FXML
    private TextField idField;

    @FXML
    private ComboBox nameBox;

    @FXML
    private TableColumn patientCol;

    @FXML
    private TableColumn patientIdCol;

    @FXML
    private TableColumn patientNameCol;

    @FXML
    private TextField patientNameField;

    @FXML
    private TableView patientTbl;

    @FXML
    private TableColumn paymentCol;

    @FXML
    private TableView registerTbl;

    @FXML
    private ComboBox smallNameBox;

    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.PATIENT);
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.REGISTRATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Registration View Loaded");

        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));

        patientTbl.setOnMouseClicked(event -> {
            Object object = patientTbl.getSelectionModel().getSelectedItem();
            PatientDTO selected = (PatientDTO)object;
            if (selected != null) {
                patientNameField.setText(selected.getPatientName());
            }
        });

        loadPatientTable();
        showNextId();
    }

    @FXML
    private void clickSaveBtn(){
        try{

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void loadPatientTable(){
        try{
            List<PatientDTO> patientList = patientBO.loadPatientTable();

            ObservableList<PatientDTO> obList = FXCollections.observableArrayList();

            for(PatientDTO patientDTO : patientList){
                obList.add(patientDTO);
            }

            patientTbl.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void goToDashBoard(){
        try{
            Stage stage = (Stage) patientNameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/serenitymentalhealth/dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void showNextId(){
        try{
            String id = registrationBO.showNextId();
            idField.setText(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
