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
import lk.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import lk.ijse.serenitymentalhealth.bo.custom.TherapyProgramBO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
    private TableColumn programTblNameCol;

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

    TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.THERAPY_PROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Therapy Program View Loaded");

        programIdCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));
        programTblNameCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramName"));

        programTbl.setOnMouseClicked(event -> {
            Object object = programTbl.getSelectionModel().getSelectedItem();
            TherapyProgramDTO selected = (TherapyProgramDTO)object;
            if (selected != null) {
                programIdField.setText(selected.getTherapyProgramId());
                programNameField.setText(selected.getTherapyProgramName());
                programCostField.setText(String.valueOf(selected.getTherapyProgramCost()));
                programDurationField.setText(String.valueOf(selected.getTherapyProgramDuration()));
                programDescriptionField.setText(selected.getTherapyProgramDescription());
            }
        });

        loadTherapyProgramTable();
        loadProgramComboBox();

    }

    @FXML
    private void clickSaveButton(){
        try{
            String therapyProgramId = programIdField.getText();
            String therapyProgramName = programNameField.getText();
            String therapyProgramDescription = programDescriptionField.getText();
            String therapyProgramDuration = programDurationField.getText();
            double therapyProgramCost = Double.parseDouble(programCostField.getText());

            boolean result = therapyProgramBO.saveTherapyProgram(new TherapyProgramDTO(therapyProgramId,therapyProgramName,therapyProgramDescription,therapyProgramDuration,therapyProgramCost));
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Therapy Program Added Successfully !").show();
                clickResetBtn();
                loadTherapyProgramTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Therapy Program").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickUpdateBtn(){
        try{
            String therapyProgramId = programIdField.getText();
            String therapyProgramName = programNameField.getText();
            String therapyProgramDescription = programDescriptionField.getText();
            String therapyProgramDuration = programDurationField.getText();
            double therapyProgramCost = Double.parseDouble(programCostField.getText());

            boolean result = therapyProgramBO.updateTherapyProgram(new TherapyProgramDTO(therapyProgramId,therapyProgramName,therapyProgramDescription,therapyProgramDuration,therapyProgramCost));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Therapy Program Updated Successfully !").show();
                clickResetBtn();
                loadTherapyProgramTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Update Therapy Program").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickDeleteBtn(){
        try{
            String therapyProgramId = programIdField.getText();

            boolean result = therapyProgramBO.deleteTherapyProgram(therapyProgramId);
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Therapy Program deleted Successfully !").show();
                clickResetBtn();
                loadTherapyProgramTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Delete Therapy Program").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickResetBtn(){
        programIdField.setText("");
        programNameField.setText("");
        programDescriptionField.setText("");
        programDurationField.setText("");
        programCostField.setText("");
    }

    @FXML
    private void loadProgramComboBox(){
        try {
            List<TherapyProgramDTO> names = therapyProgramBO.loadProgramNames();
            ObservableList<String> programNames = FXCollections.observableArrayList();
            for(TherapyProgramDTO dto : names){
                programNames.add(dto.getTherapyProgramName());
            }
            programComboBox.setItems(programNames);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void loadTherapistIdBox(){

    }

    @FXML
    private void loadTherapyProgramTable(){
        try{
            List<TherapyProgramDTO> therapyProgramList = therapyProgramBO.loadTherapyProgramTable();

            ObservableList<TherapyProgramDTO> obList = FXCollections.observableArrayList();

            for(TherapyProgramDTO therapyProgramDTO : therapyProgramList){
                System.out.println(therapyProgramDTO.getTherapyProgramName());
                obList.add(therapyProgramDTO);
            }

            programTbl.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void goToDashBoard(){
        try{
            Stage stage = (Stage) programIdField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/serenitymentalhealth/dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
