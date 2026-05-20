package lk.ijse.serenitymentalhealth.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField amountBox;

    @FXML
    private TableColumn amountCol;

    @FXML
    private ComboBox<?> idChooser;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> patientCol;

    @FXML
    private TextField patientIdBox;

    @FXML
    private TableView<?> paymentTbl;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private TableColumn<?, ?> therapistCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Payment view Loaded");
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
