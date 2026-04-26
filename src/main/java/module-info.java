module lk.ijse.serenitymentalhealth {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.serenitymentalhealth to javafx.fxml;
    exports lk.ijse.serenitymentalhealth;
}