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
import lk.ijse.serenitymentalhealth.bo.custom.PaymentBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.PaymentDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.enums.PaymentStatus;

import java.net.URL;
import java.sql.SQLException;
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

        paymentTbl.setOnMouseClicked(event -> {
            Object object = paymentTbl.getSelectionModel().getSelectedItem();
            PaymentDTO selected = (PaymentDTO)object;
            if (selected != null) {
                patientIdBox.setText(String.valueOf(selected.getPatientId()));
            }
        });

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
            if (idChooser.getValue() == null) return;
            String sessionId = idChooser.getSelectionModel().getSelectedItem().toString();
            loadPaymentTbl(sessionId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void loadPaymentTbl(String sessionId){
        try{
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
    private void clickSaveBtn(){
        try{
            int sessionId = (int)idChooser.getValue();
            int patientId = Integer.parseInt(patientIdBox.getText());
            double fee = Double.parseDouble(amountBox.getText());
            PaymentStatus paymentStatus = PaymentStatus.PENDING;

            boolean result = paymentBO.makePayment(fee,patientId,sessionId,paymentStatus);
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Payment Saved Successfully !").show();
                loadPaymentTbl(String.valueOf(sessionId));
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Payment").show();
            }
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
