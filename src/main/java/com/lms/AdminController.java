package com.lms;

import java.io.IOException;

import javafx.fxml.FXML;

public class AdminController {
    // AdminController() {
    // System.out.println("Hello from admin");
    // }
    // @FXML
    // private String name;
    // @FXML
    // private String email;

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    @FXML
    public void createAccount() {
        System.out.println("Account created");
        try {
            App.setRoot("admin-update-account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
