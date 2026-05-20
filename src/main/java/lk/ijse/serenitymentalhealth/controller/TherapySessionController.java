package lk.ijse.serenitymentalhealth.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.dto.TherapySessionDTO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TherapySessionController implements Initializable {

    @FXML
    private TableView createSessionPatientTbl;

    @FXML
    private TableView createSessionTherapistTbl;

    @FXML
    private DatePicker dateBox;

    @FXML
    private TextField dateField;

    @FXML
    private Spinner<Integer> hourTimeBox;

    @FXML
    private Spinner<Integer> minuteTimeBox;

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
    private TableColumn programNameCol;

    @FXML
    private TextField sessionIdField;

    @FXML
    private ComboBox therapistChooser;

    @FXML
    private TableColumn therapistCol;

    @FXML
    private TextField statusBox;

    @FXML
    private TableColumn therapistEditCol;

    @FXML
    private TableColumn therapistNameCol;

    @FXML
    private TextField timeField;

    @FXML
    private ComboBox programChooser;

    @FXML
    private TextField programNameBox;

    @FXML
    private TextField durationBox;

    @FXML
    private TextField therapistNameBox;

    @FXML
    private TextField patientNameBox;

    private final ObservableList<TherapistDTO> therapistObList = FXCollections.observableArrayList();
    private final ObservableList<PatientDTO> patientObList = FXCollections.observableArrayList();

    TherapySessionBO therapySessionBO = (TherapySessionBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.THERAPY_SESSION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Therapy Session view Loaded");

        showNextId();

        hourTimeBox.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 8)
        );
        hourTimeBox.setEditable(true);

        minuteTimeBox.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0)
        );
        minuteTimeBox.setEditable(true);

        loadProgramIds();

        patientCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        patientEditCol.setCellFactory(cell -> new TableCell<PatientDTO, Void>(){
            Button btn = new Button("Remove");

            {

                btn.setStyle(
                        "-fx-background-color: #4b9e34;" +
                                "-fx-text-fill: white;" +
                                "-fx-padding: 6 12 6 12;" +
                                "-fx-background-radius: 5;"
                );

                btn.setOnAction(event -> {
                    PatientDTO item = getTableView().getItems().get(getIndex());
                    patientObList.remove(item);
                    loadChoosePatientTbl();
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(btn);
                    box.setStyle("-fx-padding: 5;");
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });

        loadSessionIds();

    }

    @FXML
    private void showNextId(){
        try{
            String id = therapySessionBO.showNextId();
            sessionIdField.setText(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void goToDashBoard(){
        try{
            Stage stage = (Stage) sessionIdField.getScene().getWindow();
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
    private void clickCreateBtn(){
        try{
            int hours = hourTimeBox.getValue();
            int minutes = minuteTimeBox.getValue();
            int duration = Integer.parseInt(durationBox.getText());
            LocalDate date = dateBox.getValue();
            int therapistId = (int)therapistChooser.getValue();
            String programId = String.valueOf(programChooser.getValue());

            List<PatientDTO> patientDTOList = new ArrayList<>();
            for(PatientDTO patientDTO : patientObList){
                patientDTOList.add(patientDTO);
            }

            boolean result = therapySessionBO.createSession(hours,minutes,duration,date,therapistId, programId,patientDTOList);

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Session Created Successfully !").show();
                showNextId();
                clickResetBtn();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Create Session").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//    @FXML
//    private void clickResetBtn(){
//        hourTimeBox.getValueFactory().setValue(0);
//        minuteTimeBox.getValueFactory().setValue(0);
//        durationBox.setText("");
//        showNextId();
//    }

    @FXML
    private void clickResetBtn(){
        hourTimeBox.getValueFactory().setValue(8);
        minuteTimeBox.getValueFactory().setValue(0);
        durationBox.setText("");
        dateBox.setValue(null);
        programChooser.setValue(null);
        therapistChooser.setValue(null);
        patientChooser.setValue(null);
        programNameBox.setText("");
        therapistNameBox.setText("");
        patientNameBox.setText("");
        patientObList.clear();
        createSessionPatientTbl.setItems(patientObList);
        sessionIdChooser.setValue(null);
        dateField.setText("");
        timeField.setText("");
        statusBox.setText("");
        showNextId();
    }

    @FXML
    private void loadTherapistIds(List<TherapistDTO> therapistDTOList){
        try {
            List<TherapistDTO> names = therapistDTOList;
            ObservableList<Integer> therapistIds = FXCollections.observableArrayList();
            for(TherapistDTO dto : names){
                therapistIds.add(dto.getTherapistId());
            }
            therapistChooser.setItems(therapistIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadPatientIds(List<PatientDTO> patientDTOList){
        try {
            List<PatientDTO> names = patientDTOList;
            ObservableList<Integer> patientIds = FXCollections.observableArrayList();
            for(PatientDTO dto : names){
                patientIds.add(dto.getPatientId());
            }
            patientChooser.setItems(patientIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadProgramIds(){
        try {
            List<TherapyProgramDTO> names = therapySessionBO.loadTherapyProgramTable();
            ObservableList<String> programIds = FXCollections.observableArrayList();
            for(TherapyProgramDTO dto : names){
                programIds.add(dto.getTherapyProgramId());
            }
            programChooser.setItems(programIds);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clickTherapistIdBox(){
        try{
            if (therapistChooser.getValue() == null) return;
            int id = (int)therapistChooser.getValue();
            String therapistName = therapySessionBO.getTherapistNameById(id);
            therapistNameBox.setText(therapistName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickPatientIdBox(){
        try{
            if (patientChooser.getValue() == null) return;

            int id = (int)patientChooser.getValue();
            String patientName = therapySessionBO.getPatientNameById(id);
            patientNameBox.setText(patientName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickProgramIdBox(){
        try{
            if (programChooser.getValue() == null) return;

            String id = String.valueOf(programChooser.getValue());
            String programName = therapySessionBO.getProgramNameById(id);
            programNameBox.setText(programName);
            List<PatientDTO> patientDTOList = therapySessionBO.loadPatientIdsByProgram(id);
            loadPatientIds(patientDTOList);
            List<TherapistDTO> therapistDTOList = therapySessionBO.loadTherapistIdsByProgram(id);
            loadTherapistIds(therapistDTOList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void AddToPatients(ActionEvent event) {

        try{
            int patientId = (int)patientChooser.getValue();
            String patientName = therapySessionBO.getPatientNameById(patientId);

            patientObList.add(new PatientDTO(patientId,patientName));
            patientNameBox.setText("");
            patientChooser.setValue(null);
            loadChoosePatientTbl();
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    @FXML
    private void loadChoosePatientTbl() {

        createSessionPatientTbl.setItems(patientObList);

    }

    @FXML
    private void loadSessionIds(){
        try {
            List<Integer> ids = therapySessionBO.loadSessionIds();
            ObservableList<Integer> sessionIds = FXCollections.observableArrayList();
            for(Integer id : ids){
                sessionIds.add(id);
            }
            sessionIdChooser.setItems(sessionIds);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clickSessionIdChooser(){
        try{
            int id = (int)sessionIdChooser.getValue();
            loadSessionData(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void loadSessionData(int id){
        try{
            TherapySessionDTO sessionData = therapySessionBO.getSessionData(id);
            sessionIdField.setText(String.valueOf(id));
            durationBox.setText(Integer.toString(sessionData.getDuration()));
            dateBox.setValue(sessionData.getTherapyDate());
            dateField.setText(String.valueOf(sessionData.getTherapyDate()));
            timeField.setText(String.valueOf(sessionData.getTherapyTime()));
            statusBox.setText(sessionData.getStatus().toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
