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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class AssignRemoveCourseController implements Initializable {
    private String id;
    private String name;
    private String userType;
    private String selectedCourseId;

    // setters and getters
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSelectedCourseID() {
        return selectedCourseId;
    }

    public void setSelectedCourseID(String selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public void setUserInfo(String id, String name, String userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        getCourses();
    }

    @FXML
    ComboBox<Course> allCourseField, removeCourseField;

    @FXML
    private void assignCourse(ActionEvent event) {
        System.out.println("Assign Course");
        System.out.println(getSelectedCourseID());
        // Course selectedCourse = allCourseField.getSelectionModel().getSelectedItem();
        if (getSelectedCourseID() != null) {
            String sql = "INSERT INTO user_courses (courseID, UserID) VALUES (?, ?)";
            String sql2 = "UPDATE courses SET TeacherId = ? WHERE id = ?";
            String sql3 = "SELECT EXISTS(SELECT * from user_courses WHERE UserID='" + getID() + " AND CourseID='"
                    + getSelectedCourseID() + "')";

            try {
                System.out.println("Assigning course");
                PreparedStatement pstm = App.getConnection().prepareStatement(sql3);
                ResultSet rs = pstm.executeQuery();

                // if the user is not assigned to the course
                if (!rs.next()) {
                    PreparedStatement pstmt = App.getConnection().prepareStatement(sql);
                    pstmt.setString(1, getSelectedCourseID());
                    pstmt.setString(2, getID());
                    pstmt.executeUpdate();
                    pstmt.close();

                    if (userType.equals("Teacher")) {
                        PreparedStatement pstmt2 = App.getConnection().prepareStatement(sql2);
                        pstmt2.setString(1, getID());
                        pstmt2.setString(2, getSelectedCourseID());
                        pstmt2.executeUpdate();
                        pstmt2.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Assigned!!");
                    alert.setContentText("Course Already Assigned");
                    alert.showAndWait();
                }

                getCourses();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ObservableList<Course> courseList = FXCollections.observableArrayList();
    ObservableList<UserCourses> courseList2 = FXCollections.observableArrayList();
    ObservableList<Course> courseList3 = FXCollections.observableArrayList();
    ObservableList<Course> courseList4 = FXCollections.observableArrayList();

    @FXML
    private void removeCourse(ActionEvent event) {
        System.out.println("Remove Course");
        System.out.println(getSelectedCourseID());
        // Course selectedCourse =
        // removeCourseField.getSelectionModel().getSelectedItem();
        if (getSelectedCourseID() != null) {
            String sql = "DELETE FROM user_courses WHERE courseID = ? AND UserID = ?";
            String sql2 = "UPDATE courses SET TeacherId = ? WHERE id = ?";
            // delete marks
            String sql3 = "DELETE FROM marks WHERE courseID = ?";

            try {
                System.out.println("Removing course");
                PreparedStatement pstmt = App.getConnection().prepareStatement(sql);
                pstmt.setString(1, getSelectedCourseID());
                pstmt.setString(2, getID());
                pstmt.executeUpdate();
                pstmt.close();
                PreparedStatement pstmt1 = App.getConnection().prepareStatement(sql3);
                pstmt1.setString(1, getSelectedCourseID());
                pstmt1.executeUpdate();
                pstmt1.close();
                if (userType.equals("Teacher")) {
                    PreparedStatement pstmt2 = App.getConnection().prepareStatement(sql2);
                    pstmt2.setString(1, null);
                    pstmt2.setString(2, getSelectedCourseID());
                    pstmt2.executeUpdate();
                    pstmt2.close();
                }

                getCourses();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allCourseField.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                setSelectedCourseID(newval.getID());
                System.out.println("Selected airport: " + newval.getName()
                        + ". ID: " + newval.getID());
            }

        });

        // if (removeCourseField.getItems().size() > 0) {
        // removeCourseField.valueProperty().addListener((obs, oldval, newval) -> {
        // if (newval != null)
        // System.out.println("Selected airport: " + newval.getName()
        // + ". ID: " + newval.getID());
        // });
        // }
    }

    private void getCourses() {
        courseList.clear();
        courseList2.clear();
        courseList3.clear();
        courseList4.clear();
        System.out.println("Size at start" + courseList.size());
        try {
            PreparedStatement stmt = App.getConnection().prepareStatement("SELECT * FROM courses");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Course course = new Course(rs.getString("id"), rs.getString("name"), rs.getString("TeacherId"));

                courseList.add(course);

            }
            stmt = App.getConnection().prepareStatement("SELECT * FROM user_courses WHERE UserID = '" + getID() + "'");
            System.out.println("UserID: " + getID());
            rs = stmt.executeQuery();
            while (rs.next()) {
                UserCourses course = new UserCourses(rs.getString("id"),
                        rs.getString("userID"),
                        rs.getString("courseID"));
                courseList2.add(course);
            }

            // filter the list of courses to remove the ones that are already existed in the
            // user_courses table
            for (int i = 0; i < courseList.size(); i++) {
                System.out.println("Course Name- ---: " + courseList.get(i).getName());
            }
            // courseList4.addAll(courseList);
            for (int i = 0; i < courseList.size(); i++) {
                Course crs = new Course(courseList.get(i).getID(), courseList.get(i).getName(),
                        courseList.get(i).getTeacher());
                if (getUserType().equals("Teacher")) {
                    if ((courseList.get(i).getTeacher().equals("0"))) {
                        System.out.println("hello in there Teacher");
                        // courseList3.add(crs);
                        if (courseList2.size() > 0) {
                            for (int j = 0; j < courseList2.size(); j++) {

                                if (!(courseList.get(i).getID().equals(courseList2.get(j).getCourseId()))) {
                                    courseList3.add(crs);
                                    System.out.println(courseList3);
                                    courseList4.add(crs);
                                    break;
                                }
                            }
                        } else {
                            courseList3.add(crs);
                        }
                    }
                } else if (getUserType().equals("Student")) {
                    // courseList3.add(crs);
                    System.out.println("hello in there");
                    if (courseList2.size() > 0) {
                        for (int j = 0; j < courseList2.size(); j++) {
                            if (!(courseList.get(i).getID().equals(courseList2.get(j).getCourseId()))) {
                                System.out.println("Nrealomg");
                                courseList3.add(crs);
                                break;
                            }
                        }
                    } else {
                        courseList3.add(crs);
                    }
                }
            }

            // courseList3.add(crs);
            // System.out.println(courseList3);
            // System.out.println("Assinged Course : " + courseList.get(i).getName());
            // courseList4.add(crs);

            System.out.println("Size of " + courseList4.size());
            courseList.removeAll(courseList3);
            // remove all courses present in courslist 3 from coursList

            System.out.println("Size of Course List" + courseList.size());
            allCourseField.setItems(courseList3);
            // removeCourseField.setItems(courseList3);
            // System.out.println(removeCourseField.getItems().size());
            // if (removeCourseField.getItems().size() > 0) {
            // removeCourseField.setConverter(new StringConverter<Course>() {
            // @Override
            // public String toString(Course object) {
            // return object.getName();
            // }

            // @Override
            // public Course fromString(String string) {
            // return allCourseField.getItems().stream().filter(ap ->
            // ap.getName().equals(string)).findFirst()
            // .orElse(null);
            // }
            // });
            // }
            // if (removeCourseField != null) {

            // }

            // }
            if (allCourseField.getItems().size() > 0) {
                allCourseField.setConverter(new StringConverter<Course>() {
                    @Override
                    public String toString(Course object) {
                        return object.getName();
                    }

                    @Override
                    public Course fromString(String string) {
                        return allCourseField.getItems().stream().filter(ap -> ap.getName().equals(string)).findFirst()
                                .orElse(null);
                    }
                });
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
