package com.lms;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController implements Initializable {
    // private int id;
    // private String name;
    // private String email;
    // private String password;

    @FXML
    Label welcomeLabel;
    @FXML
    TableView<Course> tableView;
    @FXML
    TableColumn<Course, String> tblId;
    @FXML
    TableColumn<Course, String> tblTeacherName;
    @FXML
    TableColumn<Course, String> tblCourseName;
    @FXML
    TableColumn<Course, String> tblTeacherEmail;
    @FXML
    TableColumn<Course, String> tblAttendance;
    @FXML
    TableColumn<Course, String> tblMarks;
    private String id;
    private String email;
    private String name;
    ObservableList<Course> list = FXCollections.observableArrayList();
    ObservableList<Course> list1 = FXCollections.observableArrayList();
    ObservableList<UserCourses> list2 = FXCollections.observableArrayList();

    // ObservableList<Person> list2 = FXCollections.observableArrayList();
    @FXML
    void logout(ActionEvent event) {
        System.out.println("Lgout");
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rs) {
        // welcomeLabel.setText("Welcome " + name);

    }

    public void setUserInformation(String id, String name, String email) {
        welcomeLabel.setText("Welcome " + name);
        System.out.println("Welcome " + id);
        this.id = id;
        this.name = name;
        this.email = email;
        viewData();
    }

    /**
     * PreparedStatement stmt1 = App.getConnection().prepareStatement(
     * "SELECT * FROM courses WHERE CourseID = '"
     * + rs.getString("courseID") + "'");
     * ResultSet rs1 = stmt1.executeQuery();
     * while (rs1.next()) {
     * Course course1 = new Course(rs.getString("id"),
     * rs.getString("name"), rs.getString("assigned"));
     * list1.add(course1);
     * System.out.println("Course NAme : " + rs1.getString("name"));
     * }
     */

    //
    // INNER JOIN user_courses ON user_courses.CourseID = user_courses.CourseID
    // INNER JOIN users ON user_courses.UserID = users.UserID
    public void viewData() {
        System.out.println("View Data: " + id);
        try {

            PreparedStatement stmt = App.getConnection().prepareStatement(
                    "SELECT * FROM user_courses JOIN marks ON user_courses.CourseID = marks.CourseID JOIN courses ON courses.id = user_courses.CourseID JOIN users ON users.id = courses.TeacherId WHERE user_courses.UserID = '"
                            + id + "'");

            ResultSet rs = stmt.executeQuery();
            System.out.println("View Data");
            while (rs.next()) {
                // UserCourses course = new UserCourses(rs.getString("id"),
                // rs.getString("userID"),
                // rs.getString("courseID"));
                // list2.add(course);
                System.out.println(rs.getString("marks"));
                System.out.println(rs.getString("users.name"));
                System.out.println(rs.getString("name"));
                System.out.println(rs);
                Course course1 = new Course(rs.getString("id"),
                        rs.getString("name"), rs.getString("users.name"),
                        rs.getString("attendance"), rs.getString("marks"), rs.getString("users.email"));
                list.add(course1);
            }

            tblId.setCellValueFactory(new PropertyValueFactory<>("tblId"));
            tblTeacherName.setCellValueFactory(new PropertyValueFactory<>("tblTeacherName"));
            tblTeacherEmail.setCellValueFactory(new PropertyValueFactory<>("tblTeacherEmail"));
            tblCourseName.setCellValueFactory(new PropertyValueFactory<>("tblCourseName"));
            tblAttendance.setCellValueFactory(new PropertyValueFactory<>("tblAttendance"));
            tblMarks.setCellValueFactory(new PropertyValueFactory<>("tblMarks"));
            // actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
            // addButtonToTable();
            tableView.setItems(list);
        } catch (Exception e) {
            e.getMessage();
        }

        // tableView.setItems(list);
    }

    // // setters and getters
    // public int getId() {
    // return id;
    // }

    // public void setId(int id) {
    // this.id = id;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public String getName() {
    // return name;
    // }

    // public String getEmail() {
    // return email;
    // }

    // public String getPassword() {
    // return password;
    // }

}
