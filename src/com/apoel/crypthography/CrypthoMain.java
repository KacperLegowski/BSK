package com.apoel.crypthography;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sample.com.apoel.registration.User;

import java.io.IOException;

public class CrypthoMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static SessionFactory factory;
    public static Stage window;
    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        Parent root = FXMLLoader.load(getClass().getResource("cryptho.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 450));
        primaryStage.show();
    }
}
