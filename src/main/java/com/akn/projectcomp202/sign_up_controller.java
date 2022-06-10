package com.akn.projectcomp202;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class sign_up_controller {
    @FXML
    private TextField signup_usernameid;
    @FXML
    private PasswordField signup_password;
    @FXML
    private Button signup_signup;
    @FXML
    private Label errorLabel;

    static List<String> UserNameList=new ArrayList<String>();
    static List<String> UserPasswordList=new ArrayList<String>();

    @FXML
    protected void kayitolfunc() throws Exception
    {

        String aranan=signup_usernameid.getText();
        if (UserNameList.indexOf(aranan) ==  -1) {
            System.out.println("kayit olundu");
            UserNameList.add(signup_usernameid.getText());
            UserPasswordList.add(signup_password.getText());

            createUserTxt(signup_usernameid.getText());

            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("log_in.fxml"));

            Stage window = (Stage) signup_signup.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 410, 300);
            window.setTitle("Log in Screen");
            window.setScene(scene);
        }
        else
        {
            errorLabel.setText("Zaten bu isimle kayitli kullanici var!");
        }
    }

    public void createUserTxt(String username)
    {
        try {
            File myFile = new File(username + ".txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


