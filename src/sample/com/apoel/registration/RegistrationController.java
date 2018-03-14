package sample.com.apoel.registration;

import com.apoel.crypthography.CrypthoFunctions;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;

public class RegistrationController {
    public TextField login;
    public PasswordField password;
    public PasswordField repeatPassword;
    public Button registrationButton;
    public Label errorLabel;


    public void register(ActionEvent actionEvent) {
        System.out.println("User: "+password.getText()+" "+login.getText()+" "+repeatPassword.getText());
        if(login.getText()== null || password.getText()== null || repeatPassword.getText()== null) return;
        if(!password.getText().equals(repeatPassword.getText())) return;
        System.out.println("jestem tutaj");
        Session session = Main.factory.getCurrentSession();

        try {
            session.beginTransaction();
            User tempUser = new User(login.getText(), password.getText());
            session.save(tempUser);
            CrypthoFunctions.createKeyPairs(tempUser.getId(), tempUser.getPassword());
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
    }
}
