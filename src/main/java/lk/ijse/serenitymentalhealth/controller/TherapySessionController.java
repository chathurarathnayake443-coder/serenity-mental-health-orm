package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.bo.custom.TherapySessionBO;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class TherapySessionController implements Initializable {

    @FXML
    private TableView createSessionTbl;

    @FXML
    private DatePicker dateBox;

    @FXML
    private TextField dateField;

    @FXML
    private Spinner hourTimeBox;

    @FXML
    private Spinner minuteTimeBox;

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

    TherapySessionBO therapySessionBO = (TherapySessionBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.THERAPY_SESSION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Therapy Session view Loaded");

        showNextId();

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
}
