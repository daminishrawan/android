package com.example.project.Entity;

import java.io.Serializable;

public class students implements Serializable {
    private int student_id;
    private int classroom_id;
    private int user_id;
    private int parent_id;

    public students() {
    }

    public students(int student_id, int classroom_id, int user_id, int parent_id) {
        this.student_id = student_id;
        this.classroom_id = classroom_id;
        this.user_id = user_id;
        this.parent_id = parent_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "classrooms{" +
                "student_id=" + student_id +
                ", classroom_id=" + classroom_id +
                ", user_id=" + user_id +
                ", parent_id=" + parent_id +
                '}';
    }
}
