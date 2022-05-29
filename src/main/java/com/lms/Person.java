package com.lms;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private String courseID;
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private SimpleStringProperty user_type;
    private SimpleStringProperty action;
    private SimpleStringProperty marks;
    private SimpleStringProperty attendance;

    /**
     * @constructor to create a new person
     */
    public Person(String id, String name, String email, String password, String user_type) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.user_type = new SimpleStringProperty(user_type);
        this.password = new SimpleStringProperty(password);
    }

    public Person(String id, String email, String name, String marks, String attendance, String user_type,
            String courseID) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.marks = new SimpleStringProperty(marks);
        this.attendance = new SimpleStringProperty(attendance);
        this.user_type = new SimpleStringProperty(user_type);
        this.courseID = courseID;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * @return the password
     */
    public String getAction() {
        return action.get();
    }

    /**
     * @param password the password to set
     */
    public void setAction(String action) {
        this.action.set(action);
    }

    /**
     * @return the user_type
     */
    public String getUser_type() {
        return user_type.get();
    }

    public String getCourseID() {
        return courseID;
    }

    public String setCourseID(String courseID) {
        return this.courseID = courseID;
    }

    /**
     * @param user_type the user_type to set
     */
    public void setUser_type(String user_type) {
        this.user_type.set(user_type);
    }

    // setter and getter for marks, attendance and password
    public String getMarks() {
        return marks.get();
    }

    public void setMarks(String marks) {
        this.marks.set(marks);
    }

    public String getAttendance() {
        return attendance.get();
    }

    public void setAttendance(String attendance) {
        this.attendance.set(attendance);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
