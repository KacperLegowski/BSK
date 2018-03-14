package com.apoel.crypthography;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.hibernate.Session;

import java.io.File;
import java.util.List;


public class CrypthoController {
    //encrypt side
    ObservableList<String> modes =
            FXCollections.observableArrayList(
                    "ECB",
                    "CBC",
                    "CFB",
                    "OFB"
            );

    public ProgressBar encryptBar;
    public Button encryptButton;
    public Button fileButton;
    public ComboBox modeBox ;
    public ComboBox receiverBox;
    public Button generateButton;

    //decrypt side
    public Button decryptButton;
    public ProgressBar decryptBarr;
    public Label modeField;
    public ComboBox loginBox;
    public TextField fileName;


    public void decrypt(ActionEvent actionEvent) {

    }

    public void encrypt(ActionEvent actionEvent) {

    }

    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(CrypthoMain.window);
    }

    public void generateSessionKey(ActionEvent actionEvent) {

    }

    public void initialize() {
        modeBox.getItems().addAll(modes);
        Session session = CrypthoMain.factory.getCurrentSession();

        modeBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                modeField.setText(modeBox.getSelectionModel().getSelectedItem().toString());
            }
        });

        try {
            session.beginTransaction();

            String hql ="select login from User u";
            List<String> list= session.createQuery(hql).getResultList();
            ObservableList<String> users = FXCollections.observableArrayList(list);

            loginBox.getItems().addAll(users);
            receiverBox.getItems().addAll(users);

            session.getTransaction().commit();

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
    }
}
