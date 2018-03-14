package sample.com.apoel.registration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.Root;

public class Main extends Application {

    public static SessionFactory factory;
    public static Stage window;
    public static Root globalRoot;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        factory = new Configuration()
                      .configure()
                      .addAnnotatedClass(User.class)
                      .buildSessionFactory();

        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 500));

        primaryStage.setOnCloseRequest((WindowEvent event1) -> {
            factory.close();
        });
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
