package com.lms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.ResourceBundle;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class TeacherController implements Initializable {
    private String ID;
    private String NAME;
    private String EMAIL;
    private String password;

    // setters and getters
    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = ID;
    }

    public String getName() {
        return NAME;
    }

    public void setName(String NAME) {
        this.NAME = NAME;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @FXML
    private ChoiceBox<String> courseBox;
    @FXML
    private TextArea anouncementField;
    @FXML
    private Label welcomeLabel;
    @FXML
    TableView<Person> tableView;
    @FXML
    TableColumn<Person, String> id;
    @FXML
    TableColumn<Person, String> name;
    @FXML
    TableColumn<Person, String> email;
    @FXML
    TableColumn<Person, String> marks;
    @FXML
    TableColumn<Person, String> attendance;

    ObservableList<Person> list = FXCollections.observableArrayList();

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle rs) {
        courseBox.setItems(courseList);
        courseBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println(newValue);
                // viewData(newValue);
            }
        });
    }

    void setUserInformation(String id, String nm, String email) {
        welcomeLabel.setText("Welcome " + nm);
        this.ID = ID;
        this.NAME = nm;
        this.EMAIL = EMAIL;
    }

    public void viewData() {

        try {
            PreparedStatement stmt = App.getConnection().prepareStatement(
                    "SELECT * FROM user_courses JOIN users ON users.id = user_courses.userID WHERE courseID = ?");
            stmt.setString(1, courseBox.getValue());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Student Result");
                System.out.println(rs.getString(2));
                // if (!rs.getString("user_type").equals("Admin")) {
                // // System.out.println(rs.getString(2));
                // // tableView.setItems();
                // list.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3),
                // rs.getString(4),
                // rs.getString(5)));
                // }

                // System.out.println(rs.getString("name"));
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            marks.setCellValueFactory(new PropertyValueFactory<>("user_type"));
            attendance.setCellValueFactory(new PropertyValueFactory<>("user_type"));

            // actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
            // addButtonToTable();
            tableView.setItems(list);
        } catch (Exception e) {
            e.getMessage();
        }

        // tableView.setItems(list);
    }
    // public void postAnnouncmentAction(ActionEvent event) {
    // Done Button Click
    // System.out.println("Clicked ON DONE ..");
    // String announcement = anouncementField.getText();
    // String course = courseBox.getValue();
    // System.out.println(announcement);
    // System.out.println(course);
    // }

    ObservableList<String> courseList = FXCollections.observableArrayList("OOP",
            "DLD");
}
