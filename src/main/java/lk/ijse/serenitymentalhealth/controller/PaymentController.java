package lk.ijse.serenitymentalhealth.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealth.bo.custom.PaymentBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.PaymentDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField amountBox;

    @FXML
    private TableColumn amountCol;

    @FXML
    private ComboBox idChooser;

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn patientCol;

    @FXML
    private TextField patientIdBox;

    @FXML
    private TableView paymentTbl;

    @FXML
    private TableColumn statusCol;

    @FXML
    private TableColumn therapistCol;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.PAYMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Payment view Loaded");

        idCol.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        therapistCol.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        patientCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("sessionFee"));

        loadSessionIds();
    }

    @FXML
    private void loadSessionIds(){
        try{
            List<Integer> sessionIds = paymentBO.loadTherapySessionIds();

            ObservableList<Integer> ids = FXCollections.observableArrayList();
            for(Integer id : sessionIds){
                ids.add(id);
            }
            idChooser.setItems(ids);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickIdChooser(){
        try{
            String sessionId = idChooser.getSelectionModel().getSelectedItem().toString();
            List<PaymentDTO> list = paymentBO.loadPaymentDTOs(Integer.parseInt(sessionId));

            ObservableList<PaymentDTO> obList = FXCollections.observableArrayList();

            for(PaymentDTO paymentDTO : list){
                obList.add(paymentDTO);
            }

            paymentTbl.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void goToDashBoard(){
        try{
            Stage stage = (Stage) idChooser.getScene().getWindow();
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
