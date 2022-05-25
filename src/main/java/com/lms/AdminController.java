package com.lms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminController implements Initializable {
    /**
     * @Admin Properties
     */
    private int id;
    private String email;
    private String password;
    private String name;
    @FXML
    private Label nameLabel = new Label();

    /**
     * Setters and Getters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void updateNameLabel(String nm) {
        nameLabel.setText(nm);
    }

    public void setName(String name) {
        this.name = name;
        // updateNameLabel(name);
        // System.out.println("Name: " + name);

    }

    /**
     * Initializes the controller class.
     * 
     * @param id,
     * @param email,
     * @param password,
     * @param name
     */
    // AdminController(int id, String name, String email, String password) {
    // this.id = id;
    // this.name = name;
    // this.email = email;
    // this.password = password;
    // }

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rs
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rs) {

    }

    @FXML
    ComboBox<String> designation;
    @FXML
    TextField fullNameField;
    @FXML
    TextField emailField;
    @FXML
    TextField passwordField;

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
    public void handleLogout() throws IOException {
        System.out.println("Logout");
        // LoginController.loginStage.close();
        // LoginController.loginStage.show();
    }

    @FXML
    public void viewAccountWindow() throws IOException {
        System.out.println("View Account");

        App.setRoot("admin-view-account");

    }

    @FXML
    public void assignCourseWindow() throws IOException {
        System.out.println("Assign Course");

        App.setRoot("assign-course");

    }

    @FXML
    public void deleteAccountWindow() throws IOException {
        System.out.println("Delete Account");

        App.setRoot("admin-delete");

    }

    @FXML
    public void updateAccountWindow() throws IOException {
        System.out.println("Update Account");

        App.setRoot("admin-update-account");

    }

    @FXML
    public void createAccountWindow() throws IOException {
        System.out.println("Account created");

        App.setRoot("create-account");

    }

    @FXML
    private void createAccount(ActionEvent event) {
        System.out.println("Create Account");
        try {
            App.getStatement()
                    .executeQuery("INSERT INTO users VALUES ('" + fullNameField.getText() + "','" + emailField.getText()
                            + "','" + passwordField.getText() + "','" + designation.getValue() + "')");
            App.setRoot("admin-dashboard");
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
