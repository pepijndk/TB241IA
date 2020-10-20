package com.example.myapplication;

import java.util.Objects;

public class Gebruiker {
    private int id;
    private String naam;
    private String email;
    private int leeftijd;
    private int geslacht;
    private String adres;
    private String bio;


    public Gebruiker(int id, String naam, String email, int leeftijd, int geslacht, String adres, String bio) {
        this.id = id;
        this.naam = naam;
        this.email = email;
        this.leeftijd = leeftijd;
        this.geslacht = geslacht;
        this.adres = adres;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public int getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(int geslacht) {
        this.geslacht = geslacht;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.adres = bio;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", email='" + email + '\'' +
                ", leeftijd=" + leeftijd +
                ", geslacht=" + geslacht +
                ", adres='" + adres + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gebruiker gebruiker = (Gebruiker) o;
        return getId() == gebruiker.getId() &&
                getLeeftijd() == gebruiker.getLeeftijd() &&
                getGeslacht() == gebruiker.getGeslacht() &&
                getNaam().equals(gebruiker.getNaam()) &&
                getEmail().equals(gebruiker.getEmail()) &&
                getAdres().equals(gebruiker.getAdres()) &&
                getBio().equals(gebruiker.getBio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNaam(), getEmail(), getLeeftijd(), getGeslacht(), getAdres(), getBio());
    }
}
