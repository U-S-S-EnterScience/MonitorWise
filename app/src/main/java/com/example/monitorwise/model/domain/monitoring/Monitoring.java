package com.example.monitorwise.model.domain.monitoring;

import com.example.monitorwise.model.domain.discipline.Discipline;
import com.example.monitorwise.model.domain.hour.Hour;
import com.example.monitorwise.model.domain.student.Student;
import com.example.monitorwise.model.domain.user.User;

import java.util.List;

/**
 * created by Lucas Mosca on 30/11/2021.
 */
public class Monitoring {
    private final Discipline discipline;
    private final User monitor;
    private final List<Student> students;
    private final String subject;
    private final Hour hour;

    public Monitoring(Discipline discipline, User monitor, List<Student> students, String subject, Hour hour) {
        this.discipline = discipline;
        this.monitor = monitor;
        this.students = students;
        this.subject = subject;
        this.hour = hour;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public User getMonitor() {
        return monitor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getSubject() {
        return subject;
    }

    public Hour getHour() {
        return hour;
    }
}

