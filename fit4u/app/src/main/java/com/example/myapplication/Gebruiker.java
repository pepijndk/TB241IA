package com.example.myapplication;

public class Gebruiker {
    private int id;
    private String naam;
    private String email;
    private int leeftijd;
    private int geslacht;
    private String adres;


    public Gebruiker(int id, String naam, String email, int leeftijd, int geslacht, String adres) {
        this.id = id;
        this.naam = naam;
        this.email = email;
        this.leeftijd = leeftijd;
        this.geslacht = geslacht;
        this.adres = adres;
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
}
