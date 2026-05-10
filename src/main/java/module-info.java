module lk.ijse.serenitymentalhealth {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens lk.ijse.serenitymentalhealth to javafx.fxml;
    exports lk.ijse.serenitymentalhealth;
}