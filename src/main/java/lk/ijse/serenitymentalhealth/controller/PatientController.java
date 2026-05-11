package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
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

    PatientBO customerBO = (PatientBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Customer View Loaded");

        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("patient_name"));
        patientPhoneCol.setCellValueFactory(new PropertyValueFactory<>("patient_phone"));

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

        //loadCustomerTable();

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

            boolean result = customerBO.savePatient(new PatientDTO(patientName,Integer.parseInt(patientAge),patientAddress,patientPhone,guardianName,guardianPhone));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Patient Added Successfully !").show();
//                loadCustomerTable();
//                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Patient").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
