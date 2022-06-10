package com.akn.projectcomp202;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class log_in_controller extends Username {
    @FXML
    private TextField user_namefield;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private Button log_in_button;
    @FXML
    private Button sign_up_button;
    @FXML
    private Label errorLabel;

    int index;

    @FXML
    protected void girisyap() throws Exception {
        String aranan=user_namefield.getText();
        if (sign_up_controller.UserNameList.indexOf(aranan) ==  -1) {
            errorLabel.setText("Gecersiz Kullanici");
        }
        else {
            index = sign_up_controller.UserNameList.indexOf(aranan);
            if(sign_up_controller.UserPasswordList.get(index).equals(passwordfield.getText()))
            {
                //yeni sahneye geçiş
                System.out.println("Hosgeldiniz..");
                setUsername(user_namefield.getText());
                FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main.fxml"));

                Stage window = (Stage) log_in_button.getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 514, 384);
                window.setTitle("Log in");

                window.setScene(scene);
            }
            else
            {
                errorLabel.setText("Wrong username/password..");
            }
        }

    }
    @FXML //yeni sahneye geçiş
    protected void kayitol() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("sign_up.fxml"));

        Stage window = (Stage) sign_up_button.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 400, 150);
        window.setTitle("Sign Up");
        window.setScene(scene);

    }
}