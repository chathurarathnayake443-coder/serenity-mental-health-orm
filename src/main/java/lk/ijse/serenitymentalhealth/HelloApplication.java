package lk.ijse.serenitymentalhealth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.serenitymentalhealth.config.FactoryConfiguration;

import java.io.IOException;

public class HelloApplication extends Application {

    static {
        FactoryConfiguration.getInstance().getSession();
        System.out.println("Database tables created successfully!");
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patient.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Serenity - Login");
        stage.setScene(scene);
        stage.show();
    }
}
