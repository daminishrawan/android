package com.example.project.Entity;

import java.io.Serializable;

public class classrooms implements Serializable {

    private int classroom_id;
    private String classroom_name;
    private int class_teacher_id;

    public classrooms() {
    }

    public classrooms(int classroom_id, String classroom_name, int class_teacher_id) {
        this.classroom_id = classroom_id;
        this.classroom_name = classroom_name;
        this.class_teacher_id = class_teacher_id;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    public String getClassroom_name() {
        return classroom_name;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }

    public int getClass_teacher_id() {
        return class_teacher_id;
    }

    public void setClass_teacher_id(int class_teacher_id) {
        this.class_teacher_id = class_teacher_id;
    }

    @Override
    public String toString() {
        return "classrooms{" +
                "classroom_id=" + classroom_id +
                ", classroom_name='" + classroom_name + '\'' +
                ", class_teacher_id=" + class_teacher_id +
                '}';
    }
}
