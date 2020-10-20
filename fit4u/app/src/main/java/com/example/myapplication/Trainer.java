package com.example.myapplication;

import java.util.Objects;

public class Trainer extends Gebruiker{
    private int idTrainer;
    private int uurloon;

    public Trainer(int id, String naam, String email, int leeftijd, int geslacht, String adres, String bio,int idTrainer, int uurloon) {
        super(id, naam, email, leeftijd, geslacht, adres, bio);
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

    @Override
    public String toString() {
        return "Trainer{" +
                "idTrainer=" + idTrainer +
                ", uurloon=" + uurloon +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return getIdTrainer() == trainer.getIdTrainer() &&
                getUurloon() == trainer.getUurloon();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTrainer(), getUurloon());
    }
}
