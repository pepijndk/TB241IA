package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginScreen extends AppCompatActivity {

    private EditText invoerEmailadress;
    private EditText invoerPassword;
    private Button invoerLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        getActionBar().setTitle("Login");

        invoerEmailadress = findViewById(R.id.etEmailadress);
        invoerPassword = findViewById(R.id.etPassword);
        invoerLogin = findViewById(R.id.etLogin);

        invoerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputEmailadress = invoerEmailadress.getText().toString();
                String inputPassword = invoerPassword.getText().toString();

                ExterneDbHelper dbHandler = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");

                JSONObject user = dbHandler.getPassword(inputEmailadress);
                if (user == null) {
                    Toast.makeText(LoginScreen.this, "User not in database", Toast.LENGTH_SHORT).show();
                    return;
                }

                String passwdDb = null;
                String idDb = null;


                try {
                    idDb = user.getString("Gebruiker_ID");
                    passwdDb = user.getString("Wachtwoord");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginScreen.this, "User not in database", Toast.LENGTH_SHORT).show();
                }

                if (inputPassword.equals(passwdDb)) {
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    User.setId(Integer.parseInt(idDb));
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginScreen.this, "Incorrect credentials, try again", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }
}