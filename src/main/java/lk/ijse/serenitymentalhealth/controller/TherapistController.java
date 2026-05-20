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
import lk.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapySessionDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {

    @FXML
    private TableColumn dateCol;

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

    private final String AGE_REGEX = "^[0-9]+$";
    private final String NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String CONTACT_REGEX = "^[0-9]{10}$";

    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.THERAPIST);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Therapist View Loaded");

        therapistIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        therapistNameCol.setCellValueFactory(new PropertyValueFactory<>("therapistName"));

        therapistTbl.setOnMouseClicked(event -> {
            Object object = therapistTbl.getSelectionModel().getSelectedItem();
            TherapistDTO selected = (TherapistDTO)object;
            if (selected != null) {
                therapistIdField.setText(String.valueOf(selected.getTherapistId()));
                therapistNameField.setText(selected.getTherapistName());
                therapistEmailField.setText(selected.getTherapistEmail());
                therapistPhoneField.setText(selected.getTherapistPhone());
                therapistAddressField.setText(selected.getTherapistAddress());
                String therapistId = String.valueOf(selected.getTherapistId());
                loadTherapistSessionTbl(Integer.parseInt(therapistId));
            }
        });

        loadTherapistTable();
        showNextId();

        sessionIdCol.setCellValueFactory(new PropertyValueFactory<>("therapySessionId"));
        programCol.setCellValueFactory(new PropertyValueFactory<>("programName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("therapyDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("therapyTime"));

    }

    @FXML
    private void clickSaveBtn(){
        try{
            String therapistName = therapistNameField.getText();
            String therapistEmail = therapistEmailField.getText();
            String therapistPhone = therapistPhoneField.getText();
            String therapistAddress = therapistAddressField.getText();

            if(!therapistName.matches(NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Therapist Name").show();
                return;
            }
            if(!therapistPhone.matches(CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Therapist Contact").show();
                return;
            }
            if(!therapistEmail.matches(EMAIL_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Therapist Email").show();
                return;
            }

            boolean result = therapistBO.saveTherapist(new TherapistDTO(therapistName,therapistEmail,therapistPhone,therapistAddress));
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Therapist Added Successfully !").show();
                showNextId();
                clickResetBtn();
                loadTherapistTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Therapist").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickUpdateBtn(){
        try{
            String therapistName = therapistNameField.getText();
            String therapistEmail = therapistEmailField.getText();
            String therapistPhone = therapistPhoneField.getText();
            String therapistAddress = therapistAddressField.getText();

            if(!therapistName.matches(NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Therapist Name").show();
                return;
            }
            if(!therapistPhone.matches(CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Therapist Contact").show();
                return;
            }
            if(!therapistEmail.matches(EMAIL_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Therapist Email").show();
                return;
            }

            boolean result = therapistBO.updateTherapist(new TherapistDTO(therapistName,therapistEmail,therapistPhone,therapistAddress));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Therapist Updated Successfully !").show();
                clickResetBtn();
                loadTherapistTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Update Therapist").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickDeleteBtn(){
        try{
            int therapistId = Integer.parseInt(therapistIdField.getText());

            boolean result = therapistBO.deleteTherapist(therapistId);

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Therapist Deleted Successfully !").show();
                clickResetBtn();
                loadTherapistTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Delete Therapist").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickResetBtn(){
        therapistIdField.setText("");
        therapistNameField.setText("");
        therapistEmailField.setText("");
        therapistPhoneField.setText("");
        therapistAddressField.setText("");
        showNextId();
    }

    @FXML
    private void loadTherapistTable(){
        try{
            List<TherapistDTO> therapistList = therapistBO.loadTherapistTable();

            ObservableList<TherapistDTO> obList = FXCollections.observableArrayList();

            for(TherapistDTO therapistDTO : therapistList){
                obList.add(therapistDTO);
            }

            therapistTbl.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void showNextId(){
        try{
            String id = therapistBO.showNextId();
            therapistIdField.setText(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void goToDashBoard(){
        try{
            Stage stage = (Stage) therapistIdField.getScene().getWindow();
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
    private void loadTherapistSessionTbl(int id){
        try{
            List<TherapySessionDTO> therapistSessionList = therapistBO.getSessionsByTherapistId(id);

            ObservableList<TherapySessionDTO> obList = FXCollections.observableArrayList();

            for(TherapySessionDTO therapistSessionDTO : therapistSessionList){
                obList.add(therapistSessionDTO);
            }

            therapistScheduleTbl.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
