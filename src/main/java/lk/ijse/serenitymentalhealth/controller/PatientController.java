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
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private TableColumn dateCol;

    @FXML
    private TextField guardianNameField;

    @FXML
    private TextField guardianPhoneField;

    @FXML
    private TextField patientAddressField;

    @FXML
    private TextField patientAgeField;

    @FXML
    private Button patientDeleteBtn;

    @FXML
    private TableColumn patientIdCol;

    @FXML
    private TextField patientIdField;

    @FXML
    private TableColumn patientNameCol;

    @FXML
    private TextField patientNameField;

    @FXML
    private TableColumn patientPhoneCol;

    @FXML
    private TextField patientPhoneField;

    @FXML
    private Button patientResetBtn;

    @FXML
    private Button patientSaveBtn;

    @FXML
    private TableView patientTbl;

    @FXML
    private Button patientUpdateBtn;

    @FXML
    private TableColumn sessionIdCol;

    @FXML
    private TableColumn therapistCol;

    @FXML
    private TableView therapyHistoryTbl;

    private final String AGE_REGEX = "^[0-9]+$";
    private final String NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";
    private final String ADDRESS_REGEX = "^[A-Za-z0-9\\s,]{3,}$";
    private final String CONTACT_REGEX = "^[0-9]{10}$";

    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Patient View Loaded");

        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        patientPhoneCol.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));

        patientTbl.setOnMouseClicked(event -> {
            Object object = patientTbl.getSelectionModel().getSelectedItem();
            PatientDTO selected = (PatientDTO)object;
            if (selected != null) {
                patientIdField.setText(String.valueOf(selected.getPatientId()));
                patientNameField.setText(selected.getPatientName());
                patientAgeField.setText(String.valueOf(selected.getPatientAge()));
                patientAddressField.setText(selected.getPatientAddress());
                patientPhoneField.setText(selected.getPatientPhone());
                guardianNameField.setText(selected.getGuardianName());
                guardianPhoneField.setText(selected.getGuardianPhone());
            }
        });

        loadPatientTable();
        showNextId();

    }

    @FXML
    private void clickSaveBtn(){
        try{
            String patientName = patientNameField.getText();
            String patientPhone = patientPhoneField.getText();
            String patientAddress = patientAddressField.getText();
            String patientAge = patientAgeField.getText();
            String guardianName = guardianNameField.getText();
            String guardianPhone = guardianPhoneField.getText();

            if(!patientName.matches(NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Name").show();
                return;
            }
            if(!patientAddress.matches(ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Address").show();
                return;
            }
            if(!patientPhone.matches(CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Phone").show();
                return;
            }
            if(!patientAge.matches(AGE_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Age").show();
                return;
            }
            if(!guardianName.matches(NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Guardian Name").show();
                return;
            }
            if(!guardianPhone.matches(CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Guardian Contact").show();
                return;
            }

            boolean result = patientBO.savePatient(new PatientDTO(patientName,Integer.parseInt(patientAge),patientAddress,patientPhone,guardianName,guardianPhone));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Patient Added Successfully !").show();
                showNextId();
                clickResetBtn();
                loadPatientTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Patient").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickUpdateBtn(){
        try{
            String patientId = patientIdField.getText();
            String patientName = patientNameField.getText();
            String patientPhone = patientPhoneField.getText();
            String patientAddress = patientAddressField.getText();
            String patientAge = patientAgeField.getText();
            String guardianName = guardianNameField.getText();
            String guardianPhone = guardianPhoneField.getText();

            if(!patientName.matches(NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Name").show();
                return;
            }
            if(!patientAddress.matches(ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Address").show();
                return;
            }
            if(!patientPhone.matches(CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Phone").show();
                return;
            }
            if(!patientAge.matches(AGE_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Patient Age").show();
                return;
            }
            if(!guardianName.matches(NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Guardian Name").show();
                return;
            }
            if(!guardianPhone.matches(CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Guardian Contact").show();
                return;
            }

            boolean result = patientBO.updatePatient(new PatientDTO(Integer.parseInt(patientId),patientName,Integer.parseInt(patientAge),patientAddress,patientPhone,guardianName,guardianPhone));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Patient Updated Successfully !").show();
                clickResetBtn();
                loadPatientTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Update Patient").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickDeleteBtn(){
        try{
            int patientId = Integer.parseInt(patientIdField.getText());

            boolean result = patientBO.deletePatient(patientId);

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Patient Deleted Successfully !").show();
                clickResetBtn();
                loadPatientTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Delete Patient").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void showNextId(){
        try{
            String id = patientBO.showNextId();
            patientIdField.setText(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickResetBtn(){
        patientIdField.setText("");
        patientNameField.setText("");
        patientPhoneField.setText("");
        patientAddressField.setText("");
        patientAgeField.setText("");
        guardianNameField.setText("");
        guardianPhoneField.setText("");
        showNextId();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/serenitymentalhealth/patient.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
