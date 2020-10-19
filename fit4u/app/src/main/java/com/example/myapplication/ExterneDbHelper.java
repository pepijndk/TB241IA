package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExterneDbHelper {
    private class DbTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... parameters) {
            return sendHttpRequest(parameters[0]);
        }
    }

    private String url;
    private String dbName;
    private String username;
    private String password;

    public ExterneDbHelper(String url, String dbName, String username, String password) {
        this.url = url;
        this.dbName = dbName;
        this.username = username;
        this.password = password;
    }

    // Public functions
    /* TODO:  (tenminste, miss nog meer)
    // kijk ff met welke tables je moet joinen. Bijv bij getFavouriteTrainers niet de id's returnen maar de gegevens van de trainers met die id's!!

    getTrainerProfile(int id) profiel van gebruiker met id
    getUser(int id) gegevens gebruiker
    getUpcomingTrainingsOfTrainer(int id) alle sessies van trainer in de toekomst (join met trainingslot want beschrijving en datum moet ook meekomen)
    getFavouriteTrainers(int id) alle favorieten trainers van gebruiker met id
    getUpcomingTrainingsOfSporter(int id) alle komende trainingen van sporter met id
    saveData(String naam, Gender, locatie etc etc (alle dingen van een gebruiker)) sla de aangepaste gegevens van het profiel op
    saveAanvraag(int trainingslot_id, tijd, sporter_id)

    als je de functie wil testen, roep hem dan aan in MainActivity.java onder de buttonClicked en druk op de knop in emulator.
    output wordt geprint in console

     */

    public JSONObject getPassword(String emailadress) {
        JSONArray arr = rawQuery("SELECT * FROM gebruiker WHERE Email = '" + emailadress + "'"); // SELECT * FROM GEBRUIKER where gebruiker.Email = 'pepijn@gmail.com'
        JSONObject obj = null;
        try {
            obj = arr.getJSONObject(0); // .getString("Wachtwoord");
        } catch (JSONException e) {
            return null;
        }
        // return "ww123"
        return obj;
    }

    public JSONArray saveData(int Gebruiker_ID,Character Naam,Character Email,Character Wachtwoord,int Leeftijd,int Geslacht_id, Character Adres,Character Profielfoto,Character Bio,int Geslacht_Geslacht_id) {
        return rawQuery("INSERT INTO `gebruiker` ( " + Gebruiker_ID + " , " + Naam + " , " + Email + " , " + Wachtwoord + " , " + Leeftijd + ", " + Geslacht_id + " , " + Adres + " , " + Profielfoto + " , " + Bio + " , " + Geslacht_Geslacht_id + " ) ");
    }

    public JSONArray saveAanvraag(int Aanvraag_id,int Sporter_id,int Trainingslot_id) {
        return rawQuery("INSERT INTO `aanvraag` ( " + Aanvraag_id + ", " + Sporter_id + ", " + Trainingslot_id + ") ");
    }

    // todo
    public Gebruiker getUser(int gebruikerId) throws JSONException {
        JSONArray arr = rawQuery("SELECT * FROM gebruiker WHERE Gebruiker_id = " + gebruikerId);
        JSONObject obj= (JSONObject) arr.get(0);

        int id = (int) obj.getInt("Gebruiker_ID");
        String naam = (String) obj.get("Naam");
        String email= (String) obj.get("Email");
        int leeftijd = (int) obj.getInt("Leeftijd");
        int geslacht = (int) obj.getInt("Geslacht_id");
        String adres = (String) obj.get("Adres");
        String bio = "";
        if (!obj.isNull("bio")) bio = (String) obj.get("Bio");
        return new Gebruiker(id, naam, email, leeftijd, geslacht, adres, bio);
    }

    public JSONArray getTrainerProfile(int trainerId) {
        return rawQuery("SELECT * FROM trainer WHERE Trainer_id = " + trainerId);
    }


    public Trainer getTrainerProfileWithIndex(int index) throws JSONException {
        JSONArray arr =  rawQuery("SELECT * FROM trainer, gebruiker WHERE trainer.Gebruiker_id = gebruiker.Gebruiker_ID");

        JSONObject obj= (JSONObject) arr.get(index);

        int id = (int) obj.getInt("Gebruiker_ID");
        String naam = (String) obj.get("Naam");
        String email= (String) obj.get("Email");
        int leeftijd = (int) obj.getInt("Leeftijd");
        int geslacht = (int) obj.getInt("Geslacht_id");
        String adres = (String) obj.get("Adres");
        String bio = "";
        if (!obj.isNull("bio")) bio = (String) obj.get("Bio");
        int idTrainer = (int) obj.getInt("Trainer_id");
        int uurloon = (int) obj.getInt("Uurloon");
        return new Trainer(id, naam, email, leeftijd, geslacht, adres, bio, idTrainer, uurloon);
    }


    public ArrayList<Trainer> getFavouriteTrainers(int gebruikerID) throws JSONException {
        JSONObject sporter = (JSONObject) rawQuery( "Select * FROM gebruiker, sporter WHERE sporter.Gebruiker_id = " + gebruikerID).get(0);

        String sporterId = (String) sporter.get("Sporter_id");
        Log.d("Sporter id",sporterId);


        JSONArray arr =  rawQuery( "Select * FROM favorietetrainer, trainer, gebruiker " +
                "WHERE trainer.Trainer_id = favorietetrainer.Trainer_id " +
                "AND gebruiker.Gebruiker_ID = trainer.Gebruiker_id " +
                "AND favorietetrainer.Sporter_id = " + sporterId);

        ArrayList<Trainer> res = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj= (JSONObject) arr.get(i);

            int id = (int) obj.getInt("Gebruiker_ID");
            String naam = (String) obj.get("Naam");
            String email= (String) obj.get("Email");
            int leeftijd = (int) obj.getInt("Leeftijd");
            int geslacht = (int) obj.getInt("Geslacht_id");
            String adres = (String) obj.get("Adres");
            String bio = "";
            if (!obj.isNull("bio")) bio = (String) obj.get("Bio");
            int idTrainer = (int) obj.getInt("Trainer_id");
            int uurloon = (int) obj.getInt("Uurloon");
            res.add(new Trainer(id, naam, email, leeftijd, geslacht, adres, bio, idTrainer, uurloon));
        }
        return res;

    }

    public void updateData(int id, String naam, String bio, String city, String age) throws JSONException { //naam bio city, age
        String bioNew = "-";
        if (bio.length() > 0) bioNew = bio;
        String q = "UPDATE gebruiker SET Naam = '" + naam + "', Bio = '" + bioNew + "', Adres = '" +  city + "', Leeftijd = '" + age + "' WHERE Gebruiker_ID = " + id;
        Log.d("q", q);
        rawQuery(q);
    }

    public ArrayList<Trainer> getNearbyTrainers() throws JSONException {
        JSONArray arr =  rawQuery("SELECT * FROM trainer, gebruiker WHERE trainer.Gebruiker_id = gebruiker.Gebruiker_ID");

        ArrayList<Trainer> res = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj= (JSONObject) arr.get(i);

            int id = (int) obj.getInt("Gebruiker_ID");
            String naam = (String) obj.get("Naam");
            String email= (String) obj.get("Email");
            int leeftijd = (int) obj.getInt("Leeftijd");
            int geslacht = (int) obj.getInt("Geslacht_id");
            String adres = (String) obj.get("Adres");
            String bio = "";
            if (!obj.isNull("bio")) bio = (String) obj.get("Bio");
            int idTrainer = (int) obj.getInt("Trainer_id");
            int uurloon = (int) obj.getInt("Uurloon");
            res.add(new Trainer(id, naam, email, leeftijd, geslacht, adres, bio, idTrainer, uurloon));
        }
    return res;
    }

    public ArrayList<Timeslot> getUpcomingTrainingsOfSporter(int gebruikerID) throws JSONException {
        JSONObject sporter = (JSONObject) rawQuery( "Select * FROM gebruiker, sporter WHERE sporter.Gebruiker_id = " + gebruikerID).get(0);

        String sporterId = (String) sporter.get("Sporter_id");
        Log.d("Sporter id",sporterId);

        JSONArray arr = rawQuery("SELECT * FROM aanvraag, trainingslot where aanvraag.Trainingslot_id = trainingslot.Trainingslot_id AND Sporter_id = " + sporterId);

        ArrayList<Timeslot> res = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj= (JSONObject) arr.get(i);

            int id = (int) obj.getInt("Trainingslot_id");
            String beschrijving = (String) obj.get("Beschrijving");
            String datum = (String) obj.get("Datum");
            int duur = (int) obj.getInt("Duur");

            res.add(new Timeslot(id, beschrijving, datum, duur));
        }
        return res;
    }

    public ArrayList<Timeslot> getUpcomingTrainingsOfTrainer(int trainerId) throws JSONException {
        JSONArray arr = rawQuery("SELECT * FROM Trainingslot WHERE Trainer_id = " + trainerId);

        ArrayList<Timeslot> res = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj= (JSONObject) arr.get(i);

            int id = (int) obj.getInt("Trainingslot_id");
            String beschrijving = (String) obj.get("Beschrijving");
            String datum = (String) obj.get("Datum");
            int duur = (int) obj.getInt("Duur");

            res.add(new Timeslot(id, beschrijving, datum, duur));
        }
        return res;
    }
    // Private functions, do not touch
    private String sendHttpRequest(String url) {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();

            return readHttpResponse(connection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    private String readHttpResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line+"\n");
                Log.d("Response: ", "> " + line); //
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return response.toString();
    }

    private String queryDB(String sql) {
        sql = sql.trim();
        String urlString = url + "?user=" + username + "&pass=" + password + "&db=" + dbName + "&sql=" + sql;
        Log.d("url: ", urlString);
        try {
            String response = new DbTask().execute(urlString).get();
            if (response.startsWith("Error: ")) {
                throw new SQLException(response);
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray rawQuery(String sql) {
        try {
            String result = queryDB(sql);
            return new JSONArray(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
}
