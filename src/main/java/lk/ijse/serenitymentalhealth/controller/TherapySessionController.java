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
    private TableColumn sessionIdCol;

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

        loadTherapistIds();
        loadPatientIds();

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

            List<PatientDTO> patientDTOList = new ArrayList<>();
            for(PatientDTO patientDTO : patientObList){
                patientDTOList.add(patientDTO);
            }

            boolean result = therapySessionBO.createSession(hours,minutes,duration,date,therapistId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickResetBtn(){
        hourTimeBox.getValueFactory().setValue(0);
        minuteTimeBox.getValueFactory().setValue(0);
        durationBox.setText("");
        showNextId();
    }

    @FXML
    private void loadTherapistIds(){
        try {
            List<TherapistDTO> names = therapySessionBO.loadTherapistIds();
            ObservableList<Integer> therapistIds = FXCollections.observableArrayList();
            for(TherapistDTO dto : names){
                therapistIds.add(dto.getTherapistId());
            }
            therapistChooser.setItems(therapistIds);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void loadPatientIds(){
        try {
            List<PatientDTO> names = therapySessionBO.loadPatientIds();
            ObservableList<Integer> patientIds = FXCollections.observableArrayList();
            for(PatientDTO dto : names){
                patientIds.add(dto.getPatientId());
            }
            patientChooser.setItems(patientIds);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clickTherapistIdBox(){
        try{
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
    private void AddToPatients(ActionEvent event) {

        try{
            int patientId = (int)patientChooser.getValue();
            String patientName = therapySessionBO.getPatientNameById(patientId);

            patientObList.add(new PatientDTO(patientName));
            patientNameBox.setText("");
            patientChooser.setValue(null);
            loadChoosePatientTbl();
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    private void loadChoosePatientTbl() {

        createSessionPatientTbl.setItems(patientObList);

    }
}
