package com.lms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class TeacherController implements Initializable {
    private String ID;
    private String NAME;
    private String EMAIL;
    private String password;

    // setters and getters
    public String getId() {
        return ID;
    }

    public void setId(String ID) {
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
    TableView<Person> tableView;
    @FXML
    ChoiceBox<Course> courseBox;
    @FXML
    private TextArea anouncementField;
    @FXML
    private Label welcomeLabel;

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
    @FXML
    TableColumn<Person, Void> actionColumn;

    ObservableList<Course> listCourse = FXCollections.observableArrayList();
    ObservableList<Person> list = FXCollections.observableArrayList();

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle rs) {
        // courseBox.setItems(courseList);
        courseBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println(newValue);
                tableView.getItems().clear();
                list.clear();
                viewData(newValue.getID());

            }
        });
    }

    void setUserInformation(String ID, String nm, String EMAIL) {
        welcomeLabel.setText("Welcome " + nm);
        this.ID = ID;
        this.NAME = nm;
        this.EMAIL = EMAIL;
        getCourses();
    }

    void addEditButton() {
        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FXMLLoader loader = App.loadFXML("updateMarksAttendance");
                            Parent root = null;
                            Person data = getTableView().getItems().get(getIndex());
                            try {
                                System.out.println("Edit");

                                System.out.println("selectedData: " + data.getId());
                                // getAddView(event);
                                root = loader.load();

                            } catch (IOException e) {
                                e.getMessage();
                                // Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
                            }
                            updateMarksAttendanceController controller = loader.getController();
                            controller.setUserInfo(data.getId(), data.getCourseID());
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        actionColumn.setCellFactory(cellFactory);
    }

    public void viewData(String courseID) {
        System.out.println("View Data");
        System.out.println(courseID);
        try {
            // PreparedStatement stmt = App.getConnection().prepareStatement(
            // "SELECT * FROM user_courses JOIN users ON users.id = user_courses.userID
            // WHERE courseID = '"
            // + courseID + "'");
            PreparedStatement stmt = App.getConnection().prepareStatement(
                    "SELECT u.id, u.name as username, u.email, c.name as coursename, m.marks, m.attendance FROM courses c INNER JOIN user_courses uc ON c.id = uc.courseID INNER JOIN users u ON u.id = uc.userID INNER JOIN marks as m ON m.userID = u.id Where uc.courseID = ? AND c.TeacherID = ? AND u.user_type = ? ");
            stmt.setString(1, courseID);
            stmt.setString(2, ID);
            stmt.setString(3, "Student");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("username");
                String email = rs.getString("email");
                String marks = rs.getString("marks");
                String attendance = rs.getString("attendance");
                // System.out.println(rs.getString("username"));
                list.add(new Person(id, email, name, marks, attendance, "Student", courseID));
            }
            System.out.println("Data----------");
            System.out.println("List: " + list);

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
            attendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));

            // actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
            // addButtonToTable();
            addEditButton();
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

    private void getCourses() {
        try {

            PreparedStatement stmt = App.getConnection().prepareStatement("SELECT * FROM courses");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Course course = new Course(rs.getString("id"), rs.getString("name"), rs.getString("TeacherId"));

                listCourse.add(course);

                // stmt.setString(1, courseBox.getValue());

                // courseList3.add(crs);
                // System.out.println(courseList3);
                // System.out.println("Assinged Course : " + courseList.get(i).getName());
                // courseList4.add(crs);

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

            }
            courseBox.setItems(listCourse);
            if (courseBox.getItems().size() > 0) {
                courseBox.setConverter(new StringConverter<Course>() {
                    @Override
                    public String toString(Course object) {
                        return object.getName();
                    }

                    @Override
                    public Course fromString(String string) {
                        return courseBox.getItems().stream().filter(ap -> ap.getName().equals(string)).findFirst()
                                .orElse(null);
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    ObservableList<String> courseList = FXCollections.observableArrayList("OOP",
            "DLD");
}
