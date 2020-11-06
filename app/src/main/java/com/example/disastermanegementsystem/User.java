package com.example.disastermanegementsystem;

import java.util.ArrayList;

public class User {
    private String Name;
    private String Phone;
    private String Email;
    private String Password;
    private ArrayList<String> Family;

    public User() {
    }

    public User(String name, String phone, String email, String password, ArrayList<String> arrayList) {
        Name = name;
        Phone = phone;
        Email = email;
        Password = password;
        Family = arrayList;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public ArrayList<String> getFamily() {
        return Family;
    }

    public void setFamily(ArrayList<String> family) {
        Family = family;
    }
}
