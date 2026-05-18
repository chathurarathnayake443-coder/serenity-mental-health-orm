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
import lk.ijse.serenitymentalhealth.dto.RegistrationDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private TextField patientIdField;

    @FXML
    private TextField programFeeField;

    @FXML
    private TableView patientTbl;

    @FXML
    private TableColumn paymentCol;

    @FXML
    private TableView registerTbl;

    @FXML
    private ComboBox smallNameBox;

    @FXML
    private TableColumn statusCol;

    @FXML
    private TextField programIdField;

    private final String NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.REGISTRATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Registration View Loaded");

        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));

        patientCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("fee"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

        patientTbl.setOnMouseClicked(event -> {
            Object object = patientTbl.getSelectionModel().getSelectedItem();
            PatientDTO selected = (PatientDTO)object;
            if (selected != null) {
                patientIdField.setText(String.valueOf(selected.getPatientId()));
            }
        });

        registerTbl.setOnMouseClicked(event -> {
            Object object = registerTbl.getSelectionModel().getSelectedItem();
            RegistrationDTO selected = (RegistrationDTO)object;
            if (selected != null) {
                idField.setText(String.valueOf(selected.getRegistrationId()));
                feeField.setText(String.valueOf(selected.getFee()));
                programIdField.setText(String.valueOf(selected.getProgramId()));
                dateBox.setValue(selected.getDate());
                patientIdField.setText(String.valueOf(selected.getPatientId()));
            }
        });

        loadPatientTable();
        showNextId();
        loadProgramNames();
    }

    @FXML
    private void clickSaveBtn(){
        try{
            int patientId = Integer.parseInt(patientIdField.getText());
            double fee = Double.parseDouble(feeField.getText());
            String programId = programIdField.getText();
            LocalDate date = dateBox.getValue();

            boolean result = registrationBO.saveRegistration(new RegistrationDTO(patientId,programId,fee,date));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Registration Added Successfully !").show();
                showNextId();
                clickResetBtn();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Registration").show();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickUpdateBtn(){
        try{
            int registrationId = Integer.parseInt(idField.getText());
            int patientId = Integer.parseInt(patientIdField.getText());
            double fee = Double.parseDouble(feeField.getText());
            String programId = programIdField.getText();
            LocalDate date = dateBox.getValue();

            System.out.println("Registration ID: " + registrationId);
            System.out.println("Program ID: " + programId);
            System.out.println("Fee: " + fee);

            boolean result =  registrationBO.updateRegistration(new RegistrationDTO(registrationId,patientId,programId,fee,date));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Registration Updated Successfully !").show();
                showNextId();
                clickResetBtn();
                String selectedProgram = (smallNameBox.getValue() != null) ? smallNameBox.getValue().toString() : null;

                clickResetBtn();

                if (selectedProgram != null) {
                    smallNameBox.setValue(selectedProgram);
                    clickSmallNameBox();
                }
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Update Registration").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickResetBtn(){
        try{
            patientIdField.setText("");
            feeField.setText("");
            programIdField.setText("");
            dateBox.setValue(null);
            nameBox.setValue(null);
            smallNameBox.setValue(null);
            showNextId();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void loadPatientTable(){
        try{
            List<PatientDTO> patientList = registrationBO.loadPatientTable();

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
    private void loadProgramNames(){
        try {
            List<TherapyProgramDTO> programDTOs = registrationBO.loadProgramNames();
            ObservableList<String> programNames = FXCollections.observableArrayList();
            for(TherapyProgramDTO dto : programDTOs){
                programNames.add(dto.getTherapyProgramName());
            }
            nameBox.setItems(programNames);
            smallNameBox.setItems(programNames);
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    @FXML
    private void goToDashBoard(){
        try{
            Stage stage = (Stage) patientIdField.getScene().getWindow();
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

    @FXML
    private void clickProgramNameBox(){
        try {
            if (nameBox.getSelectionModel().getSelectedItem() == null) return;

            String programName = nameBox.getSelectionModel().getSelectedItem().toString();
            String id = registrationBO.getIdByName(programName);
            double price = registrationBO.getPriceById(id);
            programIdField.setText(id);
            programFeeField.setText(String.valueOf(price));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickSmallNameBox(){
        try {
            if (smallNameBox.getSelectionModel().getSelectedItem() == null) return;

            String programName = smallNameBox.getSelectionModel().getSelectedItem().toString();
            List<RegistrationDTO> regList = registrationBO.loadRegistrationData(programName);

            ObservableList<RegistrationDTO> obList = FXCollections.observableArrayList();
            for (RegistrationDTO regDTO : regList) {
                obList.add(regDTO);
            }
            registerTbl.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
