package com.example.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExterneDbHelperTest {
    ExterneDbHelper dbHandler;
    ExterneDbHelper dbHandlerMocked;

    @Before
    public void init() {
        dbHandler = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        dbHandlerMocked = Mockito.spy(dbHandler);
    }

    @Test
    public void getUser() throws JSONException {
        JSONArray dbValue = null;
        try {
            dbValue = new JSONArray("[{\"Gebruiker_ID\":\"1000\",\"Naam\":\"Job vd Hoe\",\"Email\":\"job@gmail.com\",\"Wachtwoord\":\"ww123\",\"Leeftijd\":\"19\",\"Geslacht_id\":\"1\",\"Adres\":\"Jacoba v Beijerlaan 89\",\"Profielfoto\":null,\"Bio\":\"Test2\",\"Geslacht_Geslacht_id\":\"1\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Mockito.doReturn(dbValue).when(dbHandlerMocked).rawQuery("SELECT * FROM gebruiker WHERE Gebruiker_id = 1000");

        Gebruiker goal = new Gebruiker(1000, "Job vd Hoe", "job@gmail.com", 19, 1, "Jacoba v Beijerlaan 89", "Test2");

        Assert.assertTrue(dbHandlerMocked.getUser(1000).equals(goal));
    }

    @Test
    public void getFavouriteTrainers() throws JSONException {
        JSONArray dbValue = null;
        try {
            dbValue = new JSONArray("[{\"Favoriet_id\":\"1\",\"Sporter_id\":\"5000\",\"Trainer_id\":\"6001\",\"Uurloon\":\"13\",\"Gebruiker_id\":\"1002\",\"KVK_nummer\":null,\"Geverifieerd\":null,\"Gemiddelde review\":\"3.6\",\"Gebruiker_ID\":\"1002\",\"Naam\":\"Isaac Hall\",\"Email\":\"isaac@gmail.com\",\"Wachtwoord\":\"ww125\",\"Leeftijd\":\"21\",\"Geslacht_id\":\"1\",\"Adres\":\"E du perronlaan 36\",\"Profielfoto\":null,\"Bio\":null,\"Geslacht_Geslacht_id\":\"1\"},{\"Favoriet_id\":\"4\",\"Sporter_id\":\"5000\",\"Trainer_id\":\"6003\",\"Uurloon\":\"10\",\"Gebruiker_id\":\"1004\",\"KVK_nummer\":null,\"Geverifieerd\":null,\"Gemiddelde review\":\"4.2\",\"Gebruiker_ID\":\"1004\",\"Naam\":\"Paul Post\",\"Email\":\"paulpost@hotmail.com\",\"Wachtwoord\":\"Post0456.\",\"Leeftijd\":\"33\",\"Geslacht_id\":\"1\",\"Adres\":\"Fluwelensingel 124a\",\"Profielfoto\":null,\"Bio\":null,\"Geslacht_Geslacht_id\":\"1\"},{\"Favoriet_id\":\"5\",\"Sporter_id\":\"5000\",\"Trainer_id\":\"6002\",\"Uurloon\":\"13\",\"Gebruiker_id\":\"1003\",\"KVK_nummer\":null,\"Geverifieerd\":null,\"Gemiddelde review\":\"4.8\",\"Gebruiker_ID\":\"1003\",\"Naam\":\"Jasper ter Horst\",\"Email\":\"jasperterhorst@gmail\",\"Wachtwoord\":\"pass234\",\"Leeftijd\":\"19\",\"Geslacht_id\":\"1\",\"Adres\":\"Dwarsstraat 23\",\"Profielfoto\":null,\"Bio\":\"Ik ben op zoek naar een personal trainer, maar ben door mijn werk niet consistent op dezelfde momenten beschikbaar om te trainen.\",\"Geslacht_Geslacht_id\":\"1\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray sporter_id = new JSONArray("[{\"Sporter_id\":\"5000\"}]");


        Mockito.doReturn(sporter_id).when(dbHandlerMocked).rawQuery("Select * FROM gebruiker, sporter WHERE sporter.Gebruiker_id = 1000");
        Mockito.doReturn(dbValue).when(dbHandlerMocked).rawQuery("Select * FROM favorietetrainer, trainer, gebruiker WHERE trainer.Trainer_id = favorietetrainer.Trainer_id AND gebruiker.Gebruiker_ID = trainer.Gebruiker_id AND favorietetrainer.Sporter_id = 5000");


        ArrayList<Trainer> goal = new ArrayList<Trainer>();
        goal.add(new Trainer(1002, "Isaac Hall", "isaac@gmail.com", 21, 1, "E du perronlaan 36", "", 6001, 13));
        goal.add(new Trainer(1004, "Paul Post", "paulpost@hotmail.com", 33, 1, "Fluwelensingel 124a", "", 6003, 10));
        goal.add(new Trainer(1003, "Jasper ter Horst", "jasperterhorst@gmail", 19, 1, "Dwarsstraat 23", "", 6002, 13));

        ArrayList<Trainer> trainers = dbHandlerMocked.getFavouriteTrainers(1000);
        Assert.assertTrue(trainers.size() == goal.size());

        for (int i = 0; i < trainers.size(); i++) {
            Assert.assertEquals(trainers.get(i).toString(), goal.get(i).toString());
        }

    }

    @Test
    public void getNearbyTrainers() throws JSONException {
        JSONArray dbValue = null;
        try {
            dbValue = new JSONArray("[{\"Favoriet_id\":\"4\",\"Sporter_id\":\"5000\",\"Trainer_id\":\"6003\",\"Uurloon\":\"12\",\"Gebruiker_id\":\"1004\",\"KVK_nummer\":null,\"Geverifieerd\":null,\"Gemiddelde review\":\"4.2\",\"Gebruiker_ID\":\"1004\",\"Naam\":\"Bert Bakker\",\"Email\":\"bert@bakker.com\",\"Wachtwoord\":\"Post0456.\",\"Leeftijd\":\"33\",\"Geslacht_id\":\"1\",\"Adres\":\"Bakkerstraat 1\",\"Profielfoto\":null,\"Bio\":null,\"Geslacht_Geslacht_id\":\"1\"},{\"Favoriet_id\":\"5\",\"Sporter_id\":\"5000\",\"Trainer_id\":\"6002\",\"Uurloon\":\"13\",\"Gebruiker_id\":\"1003\",\"KVK_nummer\":null,\"Geverifieerd\":null,\"Gemiddelde review\":\"4.8\",\"Gebruiker_ID\":\"1003\",\"Naam\":\"Jasper ter Horst\",\"Email\":\"jasperterhorst@gmail\",\"Wachtwoord\":\"pass234\",\"Leeftijd\":\"19\",\"Geslacht_id\":\"1\",\"Adres\":\"Dwarsstraat 23\",\"Profielfoto\":null,\"Bio\":\"Ik ben op zoek naar een personal trainer, maar ben door mijn werk niet consistent op dezelfde momenten beschikbaar om te trainen.\",\"Geslacht_Geslacht_id\":\"1\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Mockito.doReturn(dbValue).when(dbHandlerMocked).rawQuery("SELECT * FROM trainer, gebruiker WHERE trainer.Gebruiker_id = gebruiker.Gebruiker_ID");


        ArrayList<Trainer> goal = new ArrayList<Trainer>();
        goal.add(new Trainer(1004, "Bert Bakker", "bert@bakker.com", 33, 1, "Bakkerstraat 1", "", 6003, 12));
        goal.add(new Trainer(1003, "Jasper ter Horst", "jasperterhorst@gmail", 19, 1, "Dwarsstraat 23", "", 6002, 13));

        ArrayList<Trainer> trainers = dbHandlerMocked.getNearbyTrainers();
        Assert.assertTrue(goal.size() == trainers.size());

        for (int i = 0; i < trainers.size(); i++) {
            Assert.assertEquals(trainers.get(i).toString(), goal.get(i).toString());
        }

    }

    @Test
    public void getUpcomingTrainingsOfSporter() throws JSONException {
        JSONArray dbValue = null;
        try {
            dbValue = new JSONArray("[{\"Aanvraag_id\":\"2\",\"Sporter_id\":\"5000\",\"Trainingslot_id\":\"2\",\"Tijdstip_aanvraag\":\"2020-10-18 12:22:31.487000\",\"Trainer_id\":\"6001\",\"Datum\":\"2020-10-22 13:45:00.094000\",\"Duur\":\"2\",\"Beschrijving\":\"football\"},{\"Aanvraag_id\":\"3\",\"Sporter_id\":\"5000\",\"Trainingslot_id\":\"7\",\"Tijdstip_aanvraag\":\"2020-10-18 12:21:31.687000\",\"Trainer_id\":\"6002\",\"Datum\":\"2020-10-27 18:45:00.094000\",\"Duur\":\"2\",\"Beschrijving\":\"frontal cortex\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray sporter_id = new JSONArray("[{\"Sporter_id\":\"5000\"}]");


        Mockito.doReturn(sporter_id).when(dbHandlerMocked).rawQuery("Select * FROM gebruiker, sporter WHERE sporter.Gebruiker_id = 1000");
        Mockito.doReturn(dbValue).when(dbHandlerMocked).rawQuery("SELECT * FROM aanvraag, trainingslot where aanvraag.Trainingslot_id = trainingslot.Trainingslot_id AND Sporter_id = 5000");


        ArrayList<Timeslot> goal = new ArrayList<Timeslot>();
        goal.add(new Timeslot(2, "2020-10-22 13:45:00.094000", "football" , 2));
        goal.add(new Timeslot(7, "2020-10-27 18:45:00.094000", "frontal cortex" , 2));

        ArrayList<Timeslot> timeslots = dbHandlerMocked.getUpcomingTrainingsOfSporter(1000);
        Assert.assertTrue(timeslots.size() == goal.size());

        for (int i = 0; i < timeslots.size(); i++) {
            Assert.assertEquals(timeslots.get(i).toString(), goal.get(i).toString());
        }
    }

    @Test
    public void getUpcomingTrainingsOfTrainer() throws JSONException {
        JSONArray dbValue = null;
        try {
            dbValue = new JSONArray("[{\"Aanvraag_id\":\"2\",\"Sporter_id\":\"5000\",\"Trainingslot_id\":\"2\",\"Tijdstip_aanvraag\":\"2020-10-18 12:22:31.487000\",\"Trainer_id\":\"6001\",\"Datum\":\"2020-10-22 13:45:00.094000\",\"Duur\":\"2\",\"Beschrijving\":\"football\"},{\"Aanvraag_id\":\"3\",\"Sporter_id\":\"5000\",\"Trainingslot_id\":\"7\",\"Tijdstip_aanvraag\":\"2020-10-18 12:21:31.687000\",\"Trainer_id\":\"6002\",\"Datum\":\"2020-10-27 18:45:00.094000\",\"Duur\":\"2\",\"Beschrijving\":\"frontal cortex\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Mockito.doReturn(dbValue).when(dbHandlerMocked).rawQuery("SELECT * FROM Trainingslot WHERE Trainer_id = 6003");


        ArrayList<Timeslot> goal = new ArrayList<Timeslot>();
        goal.add(new Timeslot(2, "2020-10-22 13:45:00.094000", "football" , 2));
        goal.add(new Timeslot(7, "2020-10-27 18:45:00.094000", "frontal cortex" , 2));

        ArrayList<Timeslot> timeslots = dbHandlerMocked.getUpcomingTrainingsOfTrainer(6003);
        Assert.assertTrue(timeslots.size() == goal.size());

        for (int i = 0; i < timeslots.size(); i++) {
            Assert.assertEquals(timeslots.get(i).toString(), goal.get(i).toString());
        }
    }
}