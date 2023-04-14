package com.example.myfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnLog;
    @FXML
    private TextField pass;
    @FXML
    private TextField admin;
    @FXML
    private Label message;
    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        checkLogin();
    }
    private void checkLogin() throws IOException {
        HelloApplication m = new HelloApplication();
        if (admin.getText().toString().equals("admin") && pass.getText().toString().equals("1234")) {
            m.changeScene("afterLogin.fxml");
        }

        else {
            message.setText("Access Denied ");
        }

    }
}