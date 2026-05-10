module lk.ijse.serenitymentalhealth {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;


    opens lk.ijse.serenitymentalhealth to javafx.fxml;
    exports lk.ijse.serenitymentalhealth;
}