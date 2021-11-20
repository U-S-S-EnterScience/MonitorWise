package com.example.monitorwise.model.domain.discipline;

public class Discipline {

    private String name;
    private String date;
    private String local;

    public Discipline(String name, String date, String local, String hour) {
        this.name = name;
        this.date = date;
        this.local = local;
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    private String hour;


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
