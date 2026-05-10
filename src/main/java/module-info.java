//module lk.ijse.serenitymentalhealth {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires static lombok;
//    requires jakarta.persistence;
//    requires org.hibernate.orm.core;
//
//
//    opens lk.ijse.serenitymentalhealth to javafx.fxml;
//    exports lk.ijse.serenitymentalhealth;
//}
module lk.ijse.serenitymentalhealth {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;              // ← fixes javax.naming.Referenceable error
    requires java.sql;
    requires lk.ijse.serenitymentalhealth;                 // ← required for database connection

    opens lk.ijse.serenitymentalhealth to javafx.fxml;
    opens lk.ijse.serenitymentalhealth.entity to org.hibernate.orm.core;  // ← Hibernate needs to access entity classes
    opens lk.ijse.serenitymentalhealth.enums to org.hibernate.orm.core;   // ← Hibernate needs to access enums

    exports lk.ijse.serenitymentalhealth;
}