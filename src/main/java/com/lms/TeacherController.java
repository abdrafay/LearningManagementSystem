package com.lms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class TeacherController implements Initializable {
    private int id;
    private String name;
    private String email;
    private String password;

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @FXML
    private ComboBox<String> courseBox;
    @FXML
    private TextArea anouncementField;

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle rs) {
        courseBox.setItems(courseList);
    }

    public void postAnnouncmentAction(ActionEvent event) {
        // Done Button Click
        System.out.println("Clicked ON DONE ..");
        String announcement = anouncementField.getText();
        String course = courseBox.getValue();
        System.out.println(announcement);
        System.out.println(course);
    }

    ObservableList<String> courseList = FXCollections.observableArrayList("OOP",
            "DLD");
}
