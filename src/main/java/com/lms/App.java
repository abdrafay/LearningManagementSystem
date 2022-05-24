package com.lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Statement st;

    @Override
    public void start(Stage stage) throws IOException {
        // try {
        scene = new Scene(loadFXML("admin-dashboard"));
        // } catch (Exception e) {
        // e.getMessage();
        // }
        stage.setScene(scene);
        // stage.setResizable(false);
        stage.show();
    }

    static Statement getStatement() {
        return st;
    }

    static void setRoot(String fxml) throws IOException {
        System.out.println("FXML FILE: " + fxml);
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        DBConnection con = new DBConnection();
        // setStatment(con.getStatement());
        App.st = con.getStatement();
        launch();
    }

}