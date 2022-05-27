package com.lms;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// import Libaries
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.Group;
// import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void loginButtonAction(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();
        System.out.println("Email: " + email);
        Parent root = null;
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
                        "SELECT * FROM users WHERE Email = '" + email + "' AND Password = '" + password
                                + "' AND user_type = 'Admin'");
                System.out.println("Here");
                System.out.println("Rs" + rs);
                if (rs.next()) {

                    System.out.println("Login Successful");

                    // App.setRoot("announcement-teacher");

                    FXMLLoader loader = App.loadFXML("admin-dashboard");
                    root = loader.load();
                    AdminController Admin = loader.getController();
                    Admin.setUserInformation(rs.getString(2));
                    System.out.println("changing");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Admin Dashboard");
                    stage.setScene(new Scene(root, 989, 691));
                    stage.show();
                    // Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
                    // rs.getString(4));
                    // AdminController Admin = new AdminController();
                    // App.changeScene(event, rs.getString(2), "admin-dashboard");

                    // System.out.println(Admin.getName());

                    // Admin.setId(Integer.parseInt(rs.getString(1)));
                    // Admin.setName(rs.getString(2));
                    // Admin.updateNameLabel(rs.getString(2));
                    // Admin.setEmail(rs.getString(3));
                    // Admin.setPassword(rs.getString(4));
                    // App.setRoot("admin-dashboard");
                    // adminController.setName(rs.getString(2));
                } else {
                    System.out.println("Wrong email or password");
                }
            } else if (roleBox.getValue().equals("Teacher")) {
                rs = App.getStatement().executeQuery(
                        "SELECT * FROM users WHERE email = '" + email + "' AND password = '" +
                                password + "' AND user_type = 'Teacher'");
                if (rs.next()) {

                    // Teacher.setId(Integer.parseInt(rs.getString(1)));
                    // Teacher.setName(rs.getString(2));
                    // Teacher.setEmail(rs.getString(3));
                    // Teacher.setPassword(rs.getString(4));
                    FXMLLoader loader = App.loadFXML("teacher");
                    root = loader.load();
                    System.out.println("Hello");
                    // System.out.println(loader.getController());
                    TeacherController Teacher = loader.getController();
                    System.out.println("Teacher: " + Teacher);
                    Teacher.setUserInformation(rs.getString(1), rs.getString(2), rs.getString(3));
                    System.out.println("changing");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Teacher Dashboard");
                    stage.setScene(new Scene(root, 989, 691));
                    stage.show();
                    // App.setRoot("announcement-teacher");
                }
            } else if (roleBox.getValue().equals("Student")) {

                rs = App.getStatement().executeQuery(
                        "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password
                                + "' AND user_type = 'Student'");
                if (rs.next()) {
                    // StudentController student = new StudentController(
                    // Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
                    // rs.getString(4));
                    System.out.println("Login Successful");

                    // App.setRoot("announcement-teacher");

                    FXMLLoader loader = App.loadFXML("student");
                    root = loader.load();
                    // System.out.println(loader.getController());
                    StudentController Student = loader.getController();
                    System.out.println("Student: " + Student);
                    Student.setUserInformation(rs.getString(1), rs.getString(2), rs.getString(3));
                    System.out.println("changing");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Student Dashboard");
                    stage.setScene(new Scene(root, 989, 691));
                    stage.show();
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

    ObservableList<String> roleList = FXCollections.observableArrayList("Admin",
            "Teacher", "Student");
}
