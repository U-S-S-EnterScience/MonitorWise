package com.example.monitorwise.model.domain.user;

import com.example.monitorwise.model.domain.course.Course;
import com.example.monitorwise.model.domain.discipline.Discipline;

/**
 * created by Lucas Mosca on 19/11/2021.
 */
public class User {
    private String fullName;
    private String email;
    private Course course;
    private Discipline discipline;
    private String period;
    private String actionKey;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public User(String fullName, String email, Course course, Discipline discipline, String period, String actionKey) {
        this.fullName = fullName;
        this.email = email;
        this.course = course;
        this.discipline = discipline;
        this.period = period;
        this.actionKey = actionKey;
    }
}
