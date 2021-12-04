package com.example.monitorwise.model.domain.date;

import java.util.List;

/**
 * created by Lucas Mosca on 25/11/2021.
 */
public class Date {
    private List<String> days;

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public Date(List<String> days) {
        this.days = days;
    }
}
