package com.example.monitorwise.model.domain.discipline;


import com.example.monitorwise.model.domain.date.Date;
import com.example.monitorwise.model.domain.hour.Hour;


public class Discipline {
    private Date date;
    private Hour hour;
    private String idMonitor;
    private String local;
    private String name;

    public Discipline(String name) {
        this.name = name;
    }

    public Discipline(Date date, Hour hour, String idMonitor, String local, String name) {
        this.date = date;
        this.hour = hour;
        this.idMonitor = idMonitor;
        this.local = local;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public String getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(String idMonitor) {
        this.idMonitor = idMonitor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
