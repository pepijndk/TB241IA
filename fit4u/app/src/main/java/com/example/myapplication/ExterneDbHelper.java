package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

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



    // todo
    public JSONObject getUser(int id) {
//        rawQuery()
        return null;
    }

    // Returns all trainers
    public JSONArray getNearbyTrainers() {
        return rawQuery("SELECT * FROM trainer, gebruiker WHERE trainer.Gebruiker_id = gebruiker.Gebruiker_ID");
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
