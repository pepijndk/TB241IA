package com.example.myapplication;

public class Trainer extends Gebruiker{
    private int idTrainer;
    private int uurloon;

    public Trainer(int id, String naam, String email, int leeftijd, int geslacht, String adres, int idTrainer, int uurloon) {
        super(id, naam, email, leeftijd, geslacht, adres);
        this.idTrainer = idTrainer;
        this.uurloon = uurloon;
    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int id) {
        this.idTrainer = id;
    }

    public int getUurloon() {
        return uurloon;
    }

    public void setUurloon(int uurloon) {
        this.uurloon = uurloon;
    }
}
