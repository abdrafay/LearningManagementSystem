package com.lms;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;
import java.lang.Thread.State;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Connection connection;
    private static Statement st;

    @Override
    public void start(Stage stage) throws IOException {
        // try {
        scene = new Scene(loadFXML("login-window").load());
        // } catch (Exception e) {
        // e.getMessage();
        // }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static Connection getConnection() {
        return connection;
    }

    static Statement getStatement() {
        return st;
    }

    static void setRoot(String fxml) throws IOException {
        System.out.println("FXML FILE: " + fxml);
        scene.setRoot(loadFXML(fxml).load());

    }

    static FXMLLoader loadFXML(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return fxmlLoader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // public static void changeScene(ActionEvent event, String name, String fxml) {
    // Parent root = null;
    // FXMLLoader fxmlLoader = new
    // FXMLLoader(App.class.getResource("admin-dashboard.fxml"));
    // try {
    // root = fxmlLoader.load();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // }
    public static ObservableList<String> roleList = FXCollections.observableArrayList("Teacher", "Student");

    public static void main(String[] args) {
        DBConnection con = new DBConnection();
        // setStatment(con.getStatement());
        try {
            App.connection = con.getConnection();
            App.st = App.connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch();
    }

}