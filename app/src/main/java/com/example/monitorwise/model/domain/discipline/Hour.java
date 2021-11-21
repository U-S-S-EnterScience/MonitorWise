package com.example.monitorwise.model.domain.discipline;

public class Hour {
    private String begin;
    private String finish;

    public Hour() {
    }

    public Hour(String begin, String finish) {
        this.begin = begin;
        this.finish = finish;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}
