package com.example.myapplication;

import java.util.Objects;

public class Timeslot {
    private int id;
    private String datum;
    private String beschrijving;
    private int Duration;

    public Timeslot(int id, String datum, String beschrijving, int duration) {
        this.id = id;
        this.datum = datum;
        this.beschrijving = beschrijving;
        Duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    @Override
    public String toString() {
        return "Timeslot{" +
                "id=" + id +
                ", datum='" + datum + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", Duration=" + Duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timeslot timeslot = (Timeslot) o;
        return getId() == timeslot.getId() &&
                getDuration() == timeslot.getDuration() &&
                getDatum().equals(timeslot.getDatum()) &&
                getBeschrijving().equals(timeslot.getBeschrijving());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDatum(), getBeschrijving(), getDuration());
    }
}
