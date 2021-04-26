package com.example.sqlite.models;

public class DataModel {
    private int id;
    private String name;
    private String mail;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getNumber() {
        return number;
    }
}
