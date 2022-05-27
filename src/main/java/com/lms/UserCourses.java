package com.lms;

public class UserCourses {
    private String id;
    private String userId;
    private String courseId;

    public UserCourses(String id, String userId, String courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
    }

    /**
     * setters and getters
     */
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}
