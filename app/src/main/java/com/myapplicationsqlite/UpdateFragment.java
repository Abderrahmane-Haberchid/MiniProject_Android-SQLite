package com.myapplicationsqlite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateFragment extends Fragment {

    EditText searchField, nameField, emailField, passwordFiled;
    TextView headerTitle, backBtn;
    Button searchBtn, updateBtn;
    Client client;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);

        searchField = rootView.findViewById(R.id.editTextTextEmailAddress);
        searchBtn = rootView.findViewById(R.id.searchButton);
        nameField = rootView.findViewById(R.id.etNameUpdate);
        emailField = rootView.findViewById(R.id.etEmailUpdate);
        passwordFiled = rootView.findViewById(R.id.etPasswordUpdate);
        updateBtn = rootView.findViewById(R.id.updateBtn);
        backBtn = rootView.findViewById(R.id.backToAffichage);

        DatabaseHandler db = new DatabaseHandler(getActivity());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, AffichageFragment.class, null)
                        .commit();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchField.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "Please enter an email", Toast.LENGTH_LONG).show();
                else {
                    client  = db.findByEmail(searchField.getText().toString());
                    if (client != null) {
                        nameField.setText(client.getName());
                        emailField.setText(client.getEmail());
                        passwordFiled.setText(client.getEmail());
                    }
                    else
                        Toast.makeText(getActivity(), "Client not found !", Toast.LENGTH_LONG).show();
                }

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailField.getText().toString().equals("")) {
                    db.updateClient(nameField.getText().toString(), emailField.getText().toString(), passwordFiled.getText().toString(), searchField.getText().toString());
                    Toast.makeText(getActivity(), "Client updated successfully", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, AffichageFragment.class, null);
                }
                else
                    Toast.makeText(getActivity(), "Please enter an email", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}