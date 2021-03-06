package com.example.myapplication.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ExterneDbHelper;
import com.example.myapplication.Gebruiker;
import com.example.myapplication.R;
import com.example.myapplication.User;

import org.json.JSONArray;
import org.json.JSONException;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private Gebruiker gebruiker;
    private Button savebutton;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        ExterneDbHelper dbHelper = new ExterneDbHelper("http://10.0.2.2", "fit4udb2", "admin", "admin");
        try {
             gebruiker = dbHelper.getUser(User.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView email = (TextView) root.findViewById(R.id.textMail);
        email.setText(gebruiker.getEmail(), TextView.BufferType.EDITABLE);

        EditText name = (EditText)root.findViewById(R.id.inputName);
        name.setText(gebruiker.getNaam(), TextView.BufferType.EDITABLE);

        EditText inputBio = (EditText)root.findViewById(R.id.inputBio);
        inputBio.setText(gebruiker.getBio(), TextView.BufferType.EDITABLE);

        EditText inputCity = (EditText)root.findViewById(R.id.inputCity);
        inputCity.setText(gebruiker.getAdres(), TextView.BufferType.EDITABLE);

        EditText inputAge = (EditText)root.findViewById(R.id.inputAge);
        inputAge.setText(Integer.toString(gebruiker.getLeeftijd()), TextView.BufferType.EDITABLE);


        savebutton = root.findViewById(R.id.button3);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // naam bio city, age
                try {
                    dbHelper.updateData(User.getId(), name.getText().toString(), inputBio.getText().toString(), inputCity.getText().toString(), inputAge.getText().toString());
                    Toast.makeText(getContext(), "Data updated", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    return root;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }


}