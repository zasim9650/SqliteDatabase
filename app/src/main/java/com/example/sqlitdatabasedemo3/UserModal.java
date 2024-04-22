package com.example.sqlitdatabasedemo3;

import java.io.Serializable;

public class UserModal implements Serializable {
    private String name;
    private String email;
    private String gender;

    public UserModal(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
    public UserModal(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

