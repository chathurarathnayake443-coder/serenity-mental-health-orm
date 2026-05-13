package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button invoiceTab;

    @FXML
    private Button patientTab;

    @FXML
    private Button programTab;

    @FXML
    private Button reportTab;

    @FXML
    private Button sessionTab;

    @FXML
    private Button therapistTab;

    @FXML
    private Button userTab;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Dashboard view loaded");
    }

    @FXML
    private void clickPatientTab(){
        try{
            Stage stage = (Stage) patientTab.getScene().getWindow();
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
