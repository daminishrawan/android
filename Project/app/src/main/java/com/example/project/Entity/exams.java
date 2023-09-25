package com.example.project.Entity;

import java.io.Serializable;
import java.util.Date;

public class exams implements Serializable {

    private int exam_id;
    private int student_id;
    private int marks;
    private String subjects;
    private Date exam_date;

    public exams() {
    }

    public exams(int exam_id, int student_id, int marks, String subjects, Date exam_date) {
        this.exam_id = exam_id;
        this.student_id = student_id;
        this.marks = marks;
        this.subjects = subjects;
        this.exam_date = exam_date;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Date getExam_date() {
        return exam_date;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }

    @Override
    public String toString() {
        return "exams{" +
                "exam_id=" + exam_id +
                ", student_id=" + student_id +
                ", marks=" + marks +
                ", subjects='" + subjects + '\'' +
                ", exam_date=" + exam_date +
                '}';
    }
}
