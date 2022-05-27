package com.lms;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.util.Callback;
import javafx.util.Pair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminController implements Initializable, AdminActions {

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
    @FXML
    Button createAccButton;
    @FXML
    TableView<Person> tableView;
    @FXML
    TableColumn<Person, String> idColumn;
    @FXML
    TableColumn<Person, String> nameColumn;
    @FXML
    TableColumn<Person, String> emailColumn;
    @FXML
    TableColumn<Person, String> designationColumn;
    @FXML
    TableColumn<Person, Void> actionColumn;
    // @FXML
    // Button create_acc_button;
    ObservableList<Person> list = FXCollections.observableArrayList();

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

    @FXML
    void addRemoveCourseDialog() {
        System.out.println("Dialog");
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");
        // Set the button types.
        // ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        // dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        dialog.getDialogPane().setContent(grid);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.show();
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
        designation.setItems(App.roleList);
        viewData();
        /**
         * @Admin Methods
         */
        createAccButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    createAccount(event);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
    }

    @FXML
    private void getAddView(ActionEvent event) {
        System.out.println("Add View");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/lms/assign-remove-course.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void setUserInformation(String name) {
        nameLabel.setText("Welcome " + name);

    }

    @FXML
    public void handleLogout() throws IOException {
        System.out.println("Logout");
        // LoginController.loginStage.close();
        // LoginController.loginStage.show();
    }

    private void addButtonToTable() {
        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            FXMLLoader loader = App.loadFXML("assign-remove-course");
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
                            AssignRemoveCourseController controller = loader.getController();
                            controller.setUserInfo(data.getId(), data.getName(), data.getUser_type());
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
        // tableView.getColumns().add()

    }

    public void viewData() {

        try {
            PreparedStatement stmt = App.getConnection().prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (!rs.getString("user_type").equals("Admin")) {
                    // System.out.println(rs.getString(2));
                    // tableView.setItems();
                    list.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5)));
                }

                // System.out.println(rs.getString("name"));
            }
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            designationColumn.setCellValueFactory(new PropertyValueFactory<>("user_type"));
            // actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
            addButtonToTable();
            tableView.setItems(list);
        } catch (Exception e) {
            e.getMessage();
        }

        // tableView.setItems(list);
    }

    public void createAccount(ActionEvent event) {
        System.out.println("Create Account");
        try {
            // insert data into table users
            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String desig = designation.getValue();
            System.out.println(fullName + " " + email + " " + password + " " + desig);
            PreparedStatement preparedStatement = App.getConnection()
                    .prepareStatement("INSERT INTO users (name, email, password, user_type) VALUES ('" + fullName
                            + "', '" + email + "', '" + password + "', '" + desig + "')");
            preparedStatement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Account Created");
            alert.setContentText("Account Created Successfully");
            alert.showAndWait();
            // clear fields
            fullNameField.clear();
            emailField.clear();
            passwordField.clear();
            designation.setValue("");
            // App.db.insertUser(fullName, email, password, designation);
            // changeScene(event, "admin-dashboard.fxml", "Admin Dashboard");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
