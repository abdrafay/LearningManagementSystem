package com.lms;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

// import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// import Libaries
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
// import javafx.scene.control.Button;
// import javafx.scene.Group;
// import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
    private ResultSet rs;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;
    @FXML
    // private Button loginButton;
    private ComboBox<String> roleBox;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rs) {
        // roleBox.setValue("Admin");
        roleBox.setItems(roleList);
    }
    // @FXML private void initialize() {

    // }

    // @FXML
    // void LogiinButtonAction(ActionEvent event) {

    // }
    // loginButton.onAction(new EventHandler() {
    // @Override
    // public void handle(ActionEvent event) {
    // loginButtonAction(event);
    // }
    // })
    @FXML
    private void loginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        System.out.println("Email: " + email);
        try {
            // rs = App.getStatement().executeQuery("select * from admin");
            // if (roleBox.getValue().equals("Admin")) {
            // ResultSet rs = App.getStatement().executeQuery("select * from admin");
            // while (rs.next()) {
            // System.out.println(rs.getString(1));
            // if (rs.getString(4).equals(email) && rs.getString(5).equals(password)) {
            // System.out.println("Login Successful");
            // App.setRoot("admin-dashboard");
            // break;
            // }
            // }
            // }
            if (roleBox.getValue().equals("Admin")) {
                rs = App.getStatement().executeQuery(
                        "SELECT * FROM admin WHERE Email = '" + email + "' AND Password = '" + password + "'");
                System.out.println("Here");
                System.out.println("Rs" + rs);
                if (rs.next()) {
                    System.out.println("Password: " + rs.getString(5));
                    // App.setRoot("announcement-teacher");

                    AdminController adminController = new AdminController();
                    App.setRoot("admin-dashboard");
                    // adminController.setName(rs.getString(2));
                } else {
                    System.out.println("Wrong email or password");
                }
            } else if (roleBox.getValue().equals("Teacher")) {
                // rs = App.getStatement().executeQuery(
                // "SELECT * FROM teacher WHERE email = '" + email + "' AND password = '" +
                // password + "'");
                // if (rs.next()) {
                App.setRoot("announcement-teacher");
                // }
            } else if (roleBox.getValue().equals("Student")) {
                rs = App.getStatement().executeQuery(
                        "SELECT * FROM student WHERE email = '" + email + "' AND password = '" + password + "'");
                if (rs.next()) {
                    App.setRoot("student-window");
                }
            }
            rs.close();

            // while (rs.next()) {
            // if (rs.getString(4).equals(email) && rs.getString(5).equals(password)) {

            // // App.setRoot("admin-window");
            // }
            // System.out.println(rs.getString(4));
            // }
        } catch (Exception e) {
            e.getMessage();
        }

        // System.out.println(passwordField.getText());
        // if (UsernameField.getText().equals("admin") &&
        // passwordField.getText().equals("admin")) {
        // try {
        // App.setRoot("admin-window");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // } else if (UsernameField.getText().equals("user") &&
        // passwordField.getText().equals("user")) {
        // try {
        // App.setRoot("user-window");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // } else {
        // System.out.println("Wrong username or password");
        // }
    }

    ObservableList<String> roleList = FXCollections.observableArrayList("Admin", "Teacher", "User");
}
