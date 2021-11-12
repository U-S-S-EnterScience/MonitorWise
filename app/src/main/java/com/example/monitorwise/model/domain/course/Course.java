package com.example.monitorwise.model.domain.course;

import com.example.monitorwise.model.domain.discipline.Discipline;

import java.util.List;

public class Course {

    private String name;
    private String turn;
    private List<Discipline> disciplines;

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, String turn, List<Discipline> disciplines) {
        this.name = name;
        this.turn = turn;
        this.disciplines = disciplines;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}
