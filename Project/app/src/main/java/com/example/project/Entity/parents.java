package com.example.project.Entity;

import java.io.Serializable;

public class parents implements Serializable {
    private int parent_id;
    private String parent_name;
    private String parent_email;
    private int user_id;

    public parents() {
    }

    public parents(int parent_id, String parent_name, String parent_email, int user_id) {
        this.parent_id = parent_id;
        this.parent_name = parent_name;
        this.parent_email = parent_email;
        this.user_id = user_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_email() {
        return parent_email;
    }

    public void setParent_email(String parent_email) {
        this.parent_email = parent_email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "parents{" +
                "parent_id=" + parent_id +
                ", parent_name='" + parent_name + '\'' +
                ", parent_email='" + parent_email + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
