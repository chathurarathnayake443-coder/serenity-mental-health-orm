package lk.ijse.serenitymentalhealth.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.UserBO;
import lk.ijse.serenitymentalhealth.dto.UserDTO;
import lk.ijse.serenitymentalhealth.enums.UserType;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private ImageView imgEye;
    @FXML private Label lblTogglePassword;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfPasswordVisible;
    @FXML private TextField usernameField;
    @FXML
    private ComboBox cmbUserType;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.USER);

    private boolean isPasswordVisible = false;

    private Image eyeOpen;
    private Image eyeClosed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eyeOpen  = new Image(getClass().getResourceAsStream("/images/eye.png"));
        eyeClosed = new Image(getClass().getResourceAsStream("/images/eye-close.png"));

        imgEye.setImage(eyeClosed);

        cmbUserType.getItems().addAll(UserType.values());

        cmbUserType.setValue(UserType.ADMIN);
    }

    @FXML
    void onEyeIconClicked() {
        isPasswordVisible = !isPasswordVisible;

        if (isPasswordVisible) {
            tfPasswordVisible.setText(pfPassword.getText());
            tfPasswordVisible.setVisible(true);
            tfPasswordVisible.setManaged(true);
            pfPassword.setVisible(false);
            pfPassword.setManaged(false);
            imgEye.setImage(eyeOpen);
        } else {
            pfPassword.setText(tfPasswordVisible.getText());
            pfPassword.setVisible(true);
            pfPassword.setManaged(true);
            tfPasswordVisible.setVisible(false);
            tfPasswordVisible.setManaged(false);
            imgEye.setImage(eyeClosed);
        }
    }

    @FXML
    void onExitButtonClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    private void login(){
        try{
            List<UserDTO> userList = userBO.loadUserTable();

            for(UserDTO user : userList){
                if(user.getUsername().equals(usernameField.getText()) && user.getPassword().equals(pfPassword.getText())){
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/serenitymentalhealth/dashboard.fxml"));
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}