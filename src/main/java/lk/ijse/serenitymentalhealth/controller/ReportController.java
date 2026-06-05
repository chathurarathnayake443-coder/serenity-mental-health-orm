package lk.ijse.serenitymentalhealth.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.bo.custom.ReportBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private TableColumn idField;

    @FXML
    private TableColumn nameField;

    @FXML
    private TableView patientTbl;

    @FXML
    private Text text;

    ReportBO reportBO = (ReportBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.REPORT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Report view loaded");

        idField.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        nameField.setCellValueFactory(new PropertyValueFactory<>("patientName"));

        getAllPatientsInAllPrograms();
    }

    @FXML
    private void goToDashBoard(){
        try{
            Stage stage = (Stage) text.getScene().getWindow();
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
    private void getAllPatientsInAllPrograms(){
        try{
            List<PatientDTO> patientDTOList = reportBO.getAllPatientsInAllPrograms();

            ObservableList<PatientDTO> obList = FXCollections.observableArrayList();

            for(PatientDTO patientDTO : patientDTOList){
                obList.add(patientDTO);
            }

            patientTbl.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
