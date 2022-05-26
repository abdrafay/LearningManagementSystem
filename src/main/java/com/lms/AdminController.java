package com.lms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    @FXML
    ComboBox<String> designation;
    @FXML
    TextField fullNameField;
    @FXML
    TextField emailField;
    @FXML
    TextField passwordField;
    @FXML
    Label nameLabel;
    // @FXML
    // Button create_acc_button;

    /**
     * @Admin Methods
     */
    void changeScene(ActionEvent event, String fxml, String title) throws IOException {
        Parent root = null;
        try {
            FXMLLoader loader = App.loadFXML(fxml);
            root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 806, 691));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rs
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rs) {
        // designation.setItems(App.roleList);
        /**
         * @Admin Methods
         */
        // create_acc_button.setOnAction(new EventHandler<ActionEvent>() {
        // @Override
        // public void handle(ActionEvent event) {
        // try {
        // createAccount(event);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
        // });
    }

    public void setUserInformation(String name) {
        nameLabel.setText("Welcome " + name);
    }
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
    public void createAccountWindow(ActionEvent event) throws IOException {
        System.out.println("Account created");
        // App.setRoot("create-account");
        changeScene(event, "create-account", "Create Account");
    }

    @FXML
    private void createAccount(ActionEvent event) {
        System.out.println("Create Account");
        try {
            App.getStatement()
                    .executeQuery("INSERT INTO users VALUES ('" + fullNameField.getText() + "','" + emailField.getText()
                            + "','" + passwordField.getText() + "','" + designation.getValue() + "')");
            // changeScene(event, "admin-dashboard.fxml", "Admin Dashboard");
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}
