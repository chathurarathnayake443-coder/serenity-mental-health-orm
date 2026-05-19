package lk.ijse.serenitymentalhealth.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TherapySessionController implements Initializable {

    @FXML
    private TableView createSessionTbl;

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
    private TableColumn therapistEditCol;

    @FXML
    private TableColumn therapistNameCol;

    @FXML
    private TextField timeField;

    @FXML
    private TextField durationBox;

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
            hourTimeBox.getValue();
            minuteTimeBox.getValue();
            durationBox.getText();
            dateBox.getValue();

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
}
