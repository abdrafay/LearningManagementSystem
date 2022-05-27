package com.lms;

import javafx.beans.property.SimpleStringProperty;

public class Course {
    private String id;
    private String name;
    private String teacherID;

    private SimpleStringProperty tblId;
    private SimpleStringProperty tblTeacherName;
    private SimpleStringProperty tblTeacherEmail;
    private SimpleStringProperty tblCourseName;
    private SimpleStringProperty tblAttendance;
    private SimpleStringProperty tblMarks;

    public Course(String id, String name, String teacherID) {
        this.id = id;
        this.name = name;
        this.teacherID = teacherID;
    }

    public Course(String id, String name, String teacher, String attendance, String marks, String teacherEmail) {
        this.tblId = new SimpleStringProperty(id);
        this.tblTeacherName = new SimpleStringProperty(teacher);
        this.tblTeacherEmail = new SimpleStringProperty(teacherEmail);
        this.tblCourseName = new SimpleStringProperty(name);
        this.tblAttendance = new SimpleStringProperty(attendance);
        this.tblMarks = new SimpleStringProperty(marks);
    }

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

    public String getTeacher() {
        return teacherID;
    }

    public void setTeacher(String teacherID) {
        this.teacherID = teacherID;
    }

    /*
     * @return the tblId
     */
    public String getTblId() {
        return tblId.get();
    }

    public void setTblId(String id) {
        this.tblId.set(id);
    }

    public String getTblTeacherName() {
        return tblTeacherName.get();
    }

    public void setTblTeacherName(String name) {
        this.tblTeacherName.set(name);
    }

    public String getTblTeacherEmail() {
        return tblTeacherEmail.get();
    }

    public void setTblTeacherEmail(String email) {
        this.tblTeacherEmail.set(email);
    }

    public String getTblCourseName() {
        return tblCourseName.get();
    }

    public void setTblCourseName(String name) {
        this.tblCourseName.set(name);
    }

    public String getTblAttendance() {
        return tblAttendance.get();
    }

    public void setTblAttendance(String attendance) {
        this.tblAttendance.set(attendance);
    }

    public String getTblMarks() {
        return tblMarks.get();
    }

    public void setTblMarks(String marks) {
        this.tblMarks.set(marks);
    }

}
