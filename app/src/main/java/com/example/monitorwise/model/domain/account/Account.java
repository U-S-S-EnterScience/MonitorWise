package com.example.monitorwise.model.domain.account;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Account {
    private String id;
    private String email;
    private String validateKey;
    private String period;
    private String course;

    public Account(String id, String email, String validateKey, String period, String course) {
        this.id = id;
        this.email = email;
        this.validateKey = validateKey;
        this.period = period;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivateKey() {
        return validateKey;
    }

    public void setActivateKey(String activateKey) {
        this.validateKey = activateKey;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void save() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(getId()).setValue(this);
    }
}
