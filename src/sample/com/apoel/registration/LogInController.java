package sample.com.apoel.registration;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;

public class LogInController {

    public Button registrationButton;
    public Button loginButton;
    public TextField login;
    public PasswordField password;
    public Label error;


    public void goToRegistration(ActionEvent actionEvent) {
    }

    public void logIn(ActionEvent actionEvent) {
        if(login.getText()==null|| password.getText()==null) return;

        User tmp=null;
        Session session = Main.factory.getCurrentSession();

        try {
            session.beginTransaction();
            String hql ="from User u where u.login='"+login.getText()+"' and u.password='"+password.getText()+"\'";
            try {
                tmp = (User)session.createQuery(hql).getSingleResult();
            }catch (Exception ex){
                error.setText("Login or password are incorrect!");
            }
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        if(tmp!=null){
            System.out.println("sADSA");
        }
    }
}
