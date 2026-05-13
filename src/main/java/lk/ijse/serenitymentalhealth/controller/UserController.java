package lk.ijse.serenitymentalhealth.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealth.bo.BOFactory;
import lk.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import lk.ijse.serenitymentalhealth.bo.custom.UserBO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.dto.UserDTO;
import lk.ijse.serenitymentalhealth.enums.UserType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TableColumn addressCol;

    @FXML
    private TextField addressField;

    @FXML
    private TableColumn contactCol;

    @FXML
    private TextField contactField;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TableColumn roleCol;

    @FXML
    private TextField roleField;

    @FXML
    private TextField userNameField;

    @FXML
    private TableView userTbl;

    @FXML
    private ComboBox<UserType> cmbUserType;

    @FXML
    private TableColumn usernameCol;

    private final String NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String CONTACT_REGEX = "^[0-9]{10}$";
    private final String PASSWORD_REGEX = "^[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{5,}$";
    private final String ADDRESS_REGEX = "^[A-Za-z0-9\\s,]{3,}$";

    UserBO userBO = (UserBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("User View Loaded");

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("userType"));

        userTbl.setOnMouseClicked(event -> {
            Object object = userTbl.getSelectionModel().getSelectedItem();
            UserDTO selected = (UserDTO)object;
            if (selected != null) {
                userNameField.setText(selected.getUsername());
                nameField.setText(selected.getName());
                passwordField.setText(selected.getPassword());
                contactField.setText(selected.getContact());
                addressField.setText(selected.getAddress());
                roleField.setText(selected.getUserType().toString());
            }
        });

        loadUserTable();

        cmbUserType.getItems().addAll(UserType.values());

        cmbUserType.setValue(UserType.ADMIN);

    }

    @FXML
    private void clickSaveBtn(){
        try{
            String userName = userNameField.getText();
            String name = nameField.getText();
            String password = passwordField.getText();
            String contact = contactField.getText();
            String address = addressField.getText();
            UserType role = cmbUserType.getValue();

            if(!userName.matches(EMAIL_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid User Name").show();
                return;
            }
            if(!contact.matches(CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid User contact").show();
                return;
            }
            if(!name.matches(NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Name").show();
                return;
            }
            if(!address.matches(ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Address").show();
                return;
            }
            if (cmbUserType.getValue() == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a user role").show();
                return;
            }

            boolean result = userBO.saveUser(new UserDTO(userName,name,password,contact,address,role));

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"User Added Successfully !").show();
                clickResetBtn();
                loadUserTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Therapist").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickResetBtn(){
        userNameField.setText("");
        nameField.setText("");
        passwordField.setText("");
        contactField.setText("");
        addressField.setText("");
        roleField.setText("");
    }

    @FXML
    private void loadUserTable(){
        try{
            List<UserDTO> userList = userBO.loadUserTable();

            ObservableList<UserDTO> obList = FXCollections.observableArrayList();

            for(UserDTO userDTO : userList){
                obList.add(userDTO);
            }

            userTbl.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
