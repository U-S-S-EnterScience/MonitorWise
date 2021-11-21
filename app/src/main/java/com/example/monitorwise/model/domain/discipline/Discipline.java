package com.example.monitorwise.model.domain.discipline;


import java.util.List;


public class Discipline {
    public String name;

    public Discipline() {
    }

    public Discipline(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
