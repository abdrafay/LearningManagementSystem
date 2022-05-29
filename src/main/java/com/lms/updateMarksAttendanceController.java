package com.lms;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class updateMarksAttendanceController implements Initializable {
    private String id;
    private String courseID;
    @FXML
    private TextField marks;
    @FXML
    private TextField attendance;
    @FXML
    private Button UpdateMarks;
    @FXML
    private Button UpdateAttendance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // alert on update marks button click
        UpdateMarks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateMarks();
            }
        });
        UpdateAttendance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateAttendance();
            }
        });
    }

    public void updateMarks() {
        try {
            String sql = "UPDATE marks SET marks = ? WHERE userID = ? AND courseID = ?";
            PreparedStatement pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, marks.getText());
            pst.setString(2, id);
            pst.setString(3, courseID);
            pst.executeUpdate();
            pst.close();
            // Success Alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Marks Updated");
            alert.setHeaderText("Marks Updated");
            alert.setContentText("Marks updated successfully");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateAttendance() {
        try {
            String sql = "UPDATE marks SET attendance = ? WHERE userID = ? AND courseID = ?";
            PreparedStatement pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, attendance.getText());
            pst.setString(2, id);
            pst.setString(3, courseID);
            pst.executeUpdate();
            pst.close();
            // Success Alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attendance Updated");
            alert.setHeaderText("Attendance Updated");
            alert.setContentText("Attendance updated successfully");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setUserInfo(String id, String courseID) {
        this.id = id;
        this.courseID = courseID;
    }
}
